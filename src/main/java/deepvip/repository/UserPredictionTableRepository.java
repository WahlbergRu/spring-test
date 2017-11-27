package deepvip.repository;

import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPredictionTableRepository extends JpaRepository<UserPredictionTable, Long> {
    UserPredictionTable findByUserPrediction(UserPrediction userPrediction);

}
