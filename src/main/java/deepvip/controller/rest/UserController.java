package deepvip.controller.rest;

import deepvip.controller.exceptions.ExceptionMessage;
import deepvip.controller.exceptions.RestResponseEntityExceptionHandler;
import deepvip.controller.security.SecurityConstants;
import deepvip.model.ApplicationUser;
import deepvip.controller.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;


    public UserController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RestResponseEntityExceptionHandler restResponseEntityExceptionHandler) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restResponseEntityExceptionHandler = restResponseEntityExceptionHandler;
    }

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationUser>> listAllUsers() {
        List<ApplicationUser> applicationUsers = userService.findAllUsers();
        if(applicationUsers.isEmpty()){
            return new ResponseEntity<List<ApplicationUser>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ApplicationUser>>(applicationUsers, HttpStatus.OK);
    }


    //-------------------Retrieve Single ApplicationUser--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationUser> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching ApplicationUser with id " + id);
        ApplicationUser applicationUser = userService.findById(id);
        if (applicationUser == null) {
            System.out.println("ApplicationUser with id " + id + " not found");
            return new ResponseEntity<ApplicationUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ApplicationUser>(applicationUser, HttpStatus.OK);
    }



    //-------------------Create a ApplicationUser--------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody ApplicationUser applicationUser, WebRequest webRequest) {
        //TODO: добавить другой способ обработки реквестов
        if (userService.isUserExist(applicationUser)) {
            System.out.println("A ApplicationUser with name " + applicationUser.getLogin() + " already exist");
            return restResponseEntityExceptionHandler.handleAlreadyExist(
                new ExceptionMessage("A ApplicationUser with name " + applicationUser.getLogin() + " already exist"),
                    webRequest
            );
        }

        if (applicationUser.getPassword() == null){
            return restResponseEntityExceptionHandler.handleBindException(
                    new ExceptionMessage("Haven't password"),
                    webRequest
            );
        }

        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));

        try {
            userService.saveUser(applicationUser);
            return new ResponseEntity<Object>(applicationUser, new HttpHeaders(), HttpStatus.CREATED);
        }
        catch (Exception exception){
            System.out.println(exception);
            return restResponseEntityExceptionHandler.handleBindException(exception, webRequest);
        }

    }


    //------------------- Update a ApplicationUser --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ApplicationUser> updateUser(@PathVariable("id") long id, @RequestBody ApplicationUser applicationUser) {
        System.out.println("Updating ApplicationUser " + id);

        ApplicationUser currentApplicationUser = userService.findById(id);

        if (currentApplicationUser ==null) {
            System.out.println("ApplicationUser with id " + id + " not found");
            return new ResponseEntity<ApplicationUser>(HttpStatus.NOT_FOUND);
        }

        currentApplicationUser.setName(applicationUser.getName());
        currentApplicationUser.setLastName(applicationUser.getLastName());
        currentApplicationUser.setEmail(applicationUser.getEmail());
        currentApplicationUser.setAffiliation(applicationUser.getAffiliation());

        currentApplicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));

        userService.updateUser(currentApplicationUser);
        return new ResponseEntity<ApplicationUser>(currentApplicationUser, HttpStatus.OK);
    }

    //------------------- Delete a ApplicationUser --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting ApplicationUser with id " + id);

        ApplicationUser applicationUser = userService.findById(id);
        if (applicationUser == null) {
            System.out.println("Unable to delete. ApplicationUser with id " + id + " not found");
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<String>("User has deleted", HttpStatus.NO_CONTENT);
    }



}