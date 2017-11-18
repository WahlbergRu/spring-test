package deepvip.repository;

import deepvip.model.UserPredictionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPredictionHistoryRepository extends JpaRepository<UserPredictionHistory, Long> {

    /**
     * Some methods
     */

}
