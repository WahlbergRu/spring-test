package deepvip.controller.service;

import deepvip.model.UserPredictionHistory;
import deepvip.repository.UserPredictionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userPredictionHistoryService")
@Transactional
public class UserPredictionHistoryService {

    @Autowired
    private UserPredictionHistoryRepository userPredictionHistoryRepository;

    UserPredictionHistoryService(UserPredictionHistoryRepository userPredictionHistoryRepository){
        this.userPredictionHistoryRepository = userPredictionHistoryRepository;
    }

    private static final AtomicLong counter = new AtomicLong();

    public List<UserPredictionHistory> findAll() {
        return userPredictionHistoryRepository.findAll();
    }

    public UserPredictionHistory findById(long id) {
        return userPredictionHistoryRepository.findOne(id);
    }

    public void save(UserPredictionHistory userPredictionHistory) {
        userPredictionHistoryRepository.save(userPredictionHistory);
    }

    public void update(UserPredictionHistory userPredictionHistory) {
        userPredictionHistoryRepository.save(userPredictionHistory);
    }

    public void deleteById(long id) {
        userPredictionHistoryRepository.delete(id);
    }
}
