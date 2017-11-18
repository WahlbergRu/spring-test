package deepvip.controller.service;

import deepvip.model.UserPredictionHistory;
import deepvip.repository.UserPredictionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
        userPredictionHistory.setUserPredictionHistoryId(counter.incrementAndGet());
        userPredictionHistoryRepository.save(userPredictionHistory);
    }

    public void update(UserPredictionHistory userPredictionHistory) {
        userPredictionHistoryRepository.save(userPredictionHistory);
    }

    public void deleteById(long id) {
        userPredictionHistoryRepository.delete(id);
    }
}
