package deepvip.repository;

import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPredictionHistoryRepository extends JpaRepository<UserPredictionHistory, Long> {

    /**
     * Some methods
     */

    List<UserPredictionHistory> findByUserPrediction(UserPrediction userPrediction);

}
