package deepvip.controller.service;

import deepvip.model.ApplicationUser;
import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionHistory;
import deepvip.repository.UserPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLData;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service("userPredictionService")
@Transactional
public class UserPredictionService {

    @Autowired
    private UserPredictionRepository userPredictionRepository;

    UserPredictionService(UserPredictionRepository userPredictionRepository){
        this.userPredictionRepository = userPredictionRepository;
    }

    private static final AtomicLong counter = new AtomicLong();

    public List<UserPrediction> findAll() {
        return userPredictionRepository.findAll();
    }

    public List<UserPrediction> findByUserId(ApplicationUser applicationUser) {
        return userPredictionRepository.findByApplicationUser(applicationUser);
    }

    public UserPrediction findById(long id) {
        return userPredictionRepository.findOne(id);
    }

    public void save(UserPrediction userPrediction) {
        userPredictionRepository.save(userPrediction);
    }

    public void update(UserPrediction userPrediction) {
        userPredictionRepository.save(userPrediction);
    }

    public void deleteById(long id) {
        userPredictionRepository.delete(id);
    }


//    TODO: дописать фильтры
    public UserPrediction filtredUserPrediction(UserPrediction userPrediction){
        return userPrediction;
    }

}
