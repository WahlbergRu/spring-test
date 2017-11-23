package deepvip.controller.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import deepvip.controller.exceptions.RestResponseEntityExceptionHandler;
import deepvip.controller.service.UserPredictionHistoryService;
import deepvip.controller.service.UserPredictionService;
import deepvip.controller.service.UserService;
import deepvip.model.ApplicationUser;
import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionHistory;
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
    @Qualifier("userService")
    UserService userService;

    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;


    public UserPredictionsController(UserPredictionService userPredictionService,
                                     UserService userService,
                                     RestResponseEntityExceptionHandler restResponseEntityExceptionHandler,
                                     UserPredictionHistoryService userPredictionHistoryService
    ) {
        this.userPredictionService = userPredictionService;
        this.restResponseEntityExceptionHandler = restResponseEntityExceptionHandler;
    }

    //-------------------Retrieve All UserPrediction by ID --------------------------------------------------------

//    @RequestParam(value="userId", required=false) String userId
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserPrediction>>
    filtredList(WebRequest webRequest) {

//      TODO: написать декоратор для юзера
        ApplicationUser applicationUser = userService.findByJWT(webRequest);

//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Expiration: " + claims.getExpiration());

//        System.out.println(applicationUser.toString());

        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        return new ResponseEntity<List<UserPrediction>>(userPredictions, HttpStatus.OK);
    }

    //-------------------Retrieve All UserPrediction by ID --------------------------------------------------------

//    @RequestParam(value="userId", required=false) String userId
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<UserPrediction>>
    filtredList(@PathVariable("userPredictionId") long userPredictionId, WebRequest webRequest) {
        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        List<UserPrediction> userPredictions = userPredictionService.findByUserId(applicationUser);
        return new ResponseEntity<List<UserPrediction>>(userPredictions, HttpStatus.OK);
    }

    //-------------------Create a UserPrediction--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> userPredictionCreate(
            @RequestBody ObjectNode json,
            WebRequest webRequest
    ) {

        System.out.println(Integer.parseInt(json.get("filterHumanSpecificity").toString()));

//        @RequestBody UserPrediction userPrediction,
//        @RequestBody UserPredictionHistory userPredictionHistory,

        UserPredictionHistory userPredictionHistory = new UserPredictionHistory()
                .setFilterHumanSpecificity(Integer.parseInt(json.get("filterHumanSpecificity").toString()))
                .setFilterHumanSensitivity(Integer.parseInt(json.get("filterHumanSensitivity").toString()))
                .setPredictionConfidence(  Integer.parseInt(json.get("predictionConfidence").toString()))
                .setPredictionSensitivity( Integer.parseInt(json.get("predictionSensitivity").toString()))
                .setPredictionSpecificity( Integer.parseInt(json.get("predictionSpecificity").toString()));

        try {
            userPredictionHistoryService.save(userPredictionHistory);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

        Set<UserPredictionHistory> userPredictionHistorySet= new HashSet<UserPredictionHistory>();
        userPredictionHistorySet.add(userPredictionHistory);

        UserPrediction userPrediction = new UserPrediction()
            .setDescription(json.get("description").toString())
            .setViralProtein(json.get("viralProtein").toString())
            .setTitle(json.get("title").toString())
            .setHumanInteraction(json.get("humanInteraction").toString())
            .setStatus(json.get("status").toString())
            .setSendResult(json.get("sendResult").toString())
            .setPublicPrediction(json.get("publicPrediction").toString())
            .setUserPredictionHistory(userPredictionHistorySet);

        try {
            userPredictionService.save(userPrediction);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

        Set<UserPrediction> userPredictionSet = new HashSet<UserPrediction>();
        userPredictionSet.add(userPrediction);

        ApplicationUser applicationUser = userService.findByJWT(webRequest);
        applicationUser.setUserPrediction(userPredictionSet);

        try {
            userService.updateUser(applicationUser);
            return new ResponseEntity<Object>(userPrediction, new HttpHeaders(), HttpStatus.CREATED);

        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

    }





}
