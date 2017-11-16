package deepvip.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPredictionsRepository extends JpaRepository<UserPredictions, Long> {
    UserPredictions findByName(String name);
}