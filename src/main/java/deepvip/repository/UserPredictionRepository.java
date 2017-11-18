package deepvip.repository;

import deepvip.model.UserPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {
//    UserPrediction findByName(String name);
}