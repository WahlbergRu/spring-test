package deepvip.controller.service;

import deepvip.model.User;

import java.util.List;

public interface IUserService {

    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
