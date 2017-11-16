package deepvip.controller.service;

import deepvip.model.UserPredictionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserPredictionsService {

    @Autowired
    private UserPredictionsRepository userPredictionsRepository;

    UserPredictionsService(UserPredictionsRepository userPredictionsRepository){
        this.userPredictionsRepository = userPredictionsRepository;
    }

}
