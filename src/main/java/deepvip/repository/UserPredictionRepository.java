package deepvip.repository;

import deepvip.model.ApplicationUser;
import deepvip.model.UserPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {
//    UserPrediction findByName(String name);
    List<UserPrediction> findByApplicationUser(ApplicationUser applicationUser);
}