package deepvip.controller.service;

import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionTable;
import deepvip.repository.UserPredictionTableRepository;
import deepvip.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.rmi.CORBA.Util;

@Service("UserPredictionTableService")
@Transactional
public class UserPredictionTableService {

    @Autowired
    private UserPredictionTableRepository userPredictionTableRepository;

    UserPredictionTableService(UserPredictionTableRepository userPredictionTableRepository){
        this.userPredictionTableRepository = userPredictionTableRepository;
    }

    public void save(UserPredictionTable userPredictionTable) {
        this.userPredictionTableRepository.save(userPredictionTable);
    }

    public UserPredictionTable parseCSV(){
        return new CSVReader().CSVReader();
    }

    public UserPredictionTable findByUserPrediction(UserPrediction userPrediction) {
        return userPredictionTableRepository.findByUserPrediction(userPrediction);
    }



}
