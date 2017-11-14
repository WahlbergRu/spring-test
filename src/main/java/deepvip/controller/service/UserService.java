package deepvip.controller.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import deepvip.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deepvip.model.User;

@Service("userService")
@Transactional
public class UserService implements IUserService{

    UserService(){    }

    private static final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
//        int index = users.indexOf(user);
//        users.set(index, user);
    }

    @Override
    public void deleteUserById(long id) {
//        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//            User user = iterator.next();
//            if (user.getId() == id) {
//                iterator.remove();
//            }
//        }
    }

    @Override
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }

}