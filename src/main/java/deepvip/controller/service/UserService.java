package deepvip.controller.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import deepvip.model.ApplicationUser;
import deepvip.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    UserService(ApplicationUserRepository applicationUserRepository){
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByLogin(login);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(applicationUser.getLogin(), applicationUser.getPassword(), emptyList());
    }

    private static final AtomicLong counter = new AtomicLong();

    public List<ApplicationUser> findAllUsers() {
        return applicationUserRepository.findAll();
    }

    public ApplicationUser findById(long id) {
        return applicationUserRepository.findOne(id);
    }

    public ApplicationUser findByName(String name) {
        return applicationUserRepository.findByName(name);
    }

    public ApplicationUser findByLogin(String login) {
        return applicationUserRepository.findByLogin(login);
    }

    public void saveUser(ApplicationUser applicationUser) {
        applicationUser.setId(counter.incrementAndGet());
        applicationUserRepository.save(applicationUser);
    }

    public void updateUser(ApplicationUser applicationUser) {
        applicationUserRepository.save(applicationUser);
    }

    public void deleteUserById(long id) {
        applicationUserRepository.delete(id);
    }

    public boolean isUserExist(ApplicationUser applicationUser) {
        return findByLogin(applicationUser.getLogin()) != null;
    }

}