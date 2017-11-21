package deepvip.controller.rest;

import deepvip.controller.exceptions.RestResponseEntityExceptionHandler;
import deepvip.controller.service.UserPredictionService;
import deepvip.controller.service.UserService;
import deepvip.model.ApplicationUser;
import deepvip.model.UserPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/prediction")
public class UserPredictionsController {

    @Autowired
    UserPredictionService userPredictionService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;


    public UserPredictionsController(UserPredictionService userPredictionService,
                                     UserService userService,
                                     RestResponseEntityExceptionHandler restResponseEntityExceptionHandler) {
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
    public ResponseEntity<Object> userPrediction(@RequestBody UserPrediction userPrediction, WebRequest webRequest) {

        try {
            userPredictionService.save(userPrediction);
            return new ResponseEntity<Object>(userPrediction, new HttpHeaders(), HttpStatus.CREATED);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

    }





}
