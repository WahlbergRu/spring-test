package deepvip.repository;

import deepvip.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByName(String name);
    ApplicationUser findByLogin(String login);
}