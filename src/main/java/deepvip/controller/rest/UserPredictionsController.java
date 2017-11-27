package deepvip.controller.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import deepvip.UserPredictionPostModel;
import deepvip.controller.exceptions.RestResponseEntityExceptionHandler;
import deepvip.controller.service.UserPredictionHistoryService;
import deepvip.controller.service.UserPredictionService;
import deepvip.controller.service.UserPredictionTableService;
import deepvip.controller.service.UserService;
import deepvip.model.ApplicationUser;
import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionHistory;
import deepvip.model.UserPredictionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/prediction")
public class UserPredictionsController {

    @Autowired
    UserPredictionService userPredictionService;

    @Autowired
    UserPredictionHistoryService userPredictionHistoryService;

    @Autowired
    UserPredictionTableService userPredictionTableService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;


    public UserPredictionsController(UserPredictionService userPredictionService,
                                     UserService userService,
                                     RestResponseEntityExceptionHandler restResponseEntityExceptionHandler,
                                     UserPredictionHistoryService userPredictionHistoryService,
                                     UserPredictionTableService userPredictionTableService
    ) {
        this.userPredictionService = userPredictionService;
        this.restResponseEntityExceptionHandler = restResponseEntityExceptionHandler;
    }

    //-------------------Retrieve All UserPrediction by ID --------------------------------------------------------

//    @RequestParam(value="userId", required=false) String userId
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserPrediction>>
    UserPrediction(WebRequest webRequest) {

//      TODO: написать декоратор для юзера
        ApplicationUser applicationUser = userService.findByJWT(webRequest);

//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Expiration: " + claims.getExpiration());

//        System.out.println(applicationUser.toString());

        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        return new ResponseEntity<List<UserPrediction>>(userPredictions, HttpStatus.OK);
    }



    //-------------------Create a UserPrediction--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> userPredictionCreate(
            @RequestBody UserPredictionPostModel json,
            WebRequest webRequest
    ) {

//        @RequestBody UserPrediction userPrediction,
//        @RequestBody UserPredictionHistory userPredictionHistory,
        ApplicationUser applicationUser = userService.findByJWT(webRequest);

        UserPrediction userPrediction = json.getUserPrediction();
        userPrediction.setApplicationUser(applicationUser);

        try {
            userPredictionService.save(userPrediction);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

        UserPredictionHistory userPredictionHistory = json
                .getUserPredictionHistory()
                .setUserPrediction(userPrediction);

        try {
            userPredictionHistoryService.save(userPredictionHistory);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

        try {
            UserPredictionTable userPredictionTable = this.userPredictionTableService.parseCSV();
            userPredictionTable.setUserPrediction(userPrediction);
            userPredictionTableService.save(userPredictionTable);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

        try {
            return new ResponseEntity<Object>(userPrediction, new HttpHeaders(), HttpStatus.CREATED);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

    }


    //-------------------Retrieve UserPredictionHistory by ID --------------------------------------------------------
    @RequestMapping(value = "/{userPredictionId}", method = RequestMethod.DELETE)
    public ResponseEntity<UserPrediction>
        DeleteUserPredictionById(
            @PathVariable("userPredictionId") long userPredictionId,
            WebRequest webRequest) {

        userPredictionService.deleteById(userPredictionId);
        return new ResponseEntity<UserPrediction>(HttpStatus.OK);
    }


    //-------------------Retrieve All UserPrediction by ID --------------------------------------------------------

    @RequestMapping(value = "/{userPredictionId}", method = RequestMethod.GET)
    public ResponseEntity<UserPrediction>
    UserPredictionById(@PathVariable("userPredictionId") long userPredictionId, WebRequest webRequest) {
        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        UserPrediction userPrediction = userPredictions.stream().filter(userPred -> userPred.getId() == userPredictionId).findAny().get();
        return new ResponseEntity<UserPrediction>(userPrediction, HttpStatus.OK);
    }

    //-------------------Retrieve UserPrediction by ID --------------------------------------------------------
    @RequestMapping(value = "/{userPredictionId}/table", method = RequestMethod.GET)
    public ResponseEntity<UserPredictionTable>
    UserPredictionTableById(@PathVariable("userPredictionId") long userPredictionId, WebRequest webRequest) {
        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        UserPrediction userPrediction = userPredictions.stream().filter(userPred -> userPred.getId() == userPredictionId).findAny().get();
        UserPredictionTable userPredictionTable = userPredictionTableService.findByUserPrediction(userPrediction);
        return new ResponseEntity<UserPredictionTable>(userPredictionTable, HttpStatus.OK);
    }

    //-------------------Retrieve UserPrediction by ID --------------------------------------------------------
    @RequestMapping(value = "/{userPredictionId}/history", method = RequestMethod.GET)
    public ResponseEntity<List<UserPredictionHistory>>
    UserPredictionHistoryById(@PathVariable("userPredictionId") long userPredictionId, WebRequest webRequest) {
        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        UserPrediction userPrediction = userPredictions.stream().filter(userPred -> userPred.getId() == userPredictionId).findAny().get();
        List<UserPredictionHistory> userPredictionHistories = userPredictionHistoryService.findByUserPrediction(userPrediction);
        return new ResponseEntity<List<UserPredictionHistory>>(userPredictionHistories, HttpStatus.OK);
    }

    //-------------------Retrieve UserPrediction by ID --------------------------------------------------------
    @RequestMapping(value = "/{userPredictionId}/history", method = RequestMethod.POST)
    public ResponseEntity<UserPredictionHistory>
    CreateUserPredictionHistoryById(@PathVariable("userPredictionId") long userPredictionId,
                              @RequestBody UserPredictionHistory userPredictionHistory,
                              WebRequest webRequest) {
        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        userPredictionHistory.setUserPrediction(userPredictionService.findById(userPredictionId));
        userPredictionHistoryService.save(userPredictionHistory);
        return new ResponseEntity<UserPredictionHistory>(userPredictionHistory, HttpStatus.OK);
    }

    //-------------------Retrieve UserPredictionHistory by ID --------------------------------------------------------
    @RequestMapping(value = "/{userPredictionId}/history/{userPredictionHistoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<UserPredictionHistory>
    DeleteUserPredictionHistoryById(
            @PathVariable("userPredictionId") long userPredictionId,
            @PathVariable("userPredictionHistoryId") long userPredictionHistoryId,
            WebRequest webRequest) {

        userPredictionHistoryService.deleteById(userPredictionHistoryId);
        return new ResponseEntity<UserPredictionHistory>(HttpStatus.OK);
    }




}
