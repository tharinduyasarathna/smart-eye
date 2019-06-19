package lk.tharindu.backend.service;

import lk.tharindu.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    List<User> fetchAllUser();
    User fetchUser(User user);
    Optional<User> findById(Integer id);
    void deteteById(Integer id);

}
