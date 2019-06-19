package lk.tharindu.backend.controller;

import lk.tharindu.backend.model.User;
import lk.tharindu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smarteye")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> fetchAllUser(){
        return userService.fetchAllUser();
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> fetchAllUserById(@PathVariable Integer id){
        User user= new User();
        user.setId(id);

        User user1= userService.fetchUser(user);

        if(user1==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(user1);
        }


    }

    //update existing user ( only user name )
    @RequestMapping(value="/updateUser/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        User tempUser = new User();
        tempUser.setId(id);
        User updatedUser = userService.fetchUser(tempUser);

        if (!userService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        updatedUser.setName(user.getName());
        return ResponseEntity.ok(userService.createUser(updatedUser));
    }

    //delete existing user
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUsers(@PathVariable Integer id){
        if (!userService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        userService.deteteById(id);
        return ResponseEntity.ok().build();
    }
}
