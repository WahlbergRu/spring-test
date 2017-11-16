package deepvip.controller.service;

import deepvip.model.ApplicationUser;

import java.util.List;

public interface IUserService {

    ApplicationUser findById(long id);

    ApplicationUser findByName(String name);

    void saveUser(ApplicationUser applicationUser);

    void updateUser(ApplicationUser applicationUser);

    void deleteUserById(long id);

    List<ApplicationUser> findAllUsers();

    boolean isUserExist(ApplicationUser applicationUser);
}
