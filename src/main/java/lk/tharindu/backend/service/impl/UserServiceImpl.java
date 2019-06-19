package lk.tharindu.backend.service.impl;

import lk.tharindu.backend.exception.BodyContentNotValidException;
import lk.tharindu.backend.exception.CustomDataIntergrityVoilationException;
import lk.tharindu.backend.exception.DataNotFoundException;
import lk.tharindu.backend.model.User;
import lk.tharindu.backend.repository.UserRepository;
import lk.tharindu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {

        if (user.getPassword().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty password");
        }

        if (user.getEmail().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty email");
        }

        //encrypt password and save it in db
        user.setPassword("{bcrypt}"+bCryptPasswordEncoder.encode(user.getPassword()));

        //check whether email is already exist.
        Optional<User> optional= userRepository.findByEmail(user.getEmail());
        if(optional.isPresent()){
            throw new CustomDataIntergrityVoilationException("Email Already Exists");
        }else {
            return userRepository.save(user);
        }
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User fetchUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new DataNotFoundException("User does not exist");
        }
    }

    // find user by id
    @Override
    public Optional<User> findById(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return userRepository.findById(id);
        }else {
            throw new DataNotFoundException("User does not exist");
        }

    }

    // delete user by id
    @Override
    public  void deteteById(Integer id){
        userRepository.deleteById(id);
    }




}
