package com.user.usercrud.service;

import com.user.usercrud.exception.UserAlreadyExistException;
import com.user.usercrud.exception.UserNotFoundException;
import com.user.usercrud.model.User;
import com.user.usercrud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements com.user.usercrud.service.IUserService {

    private final UserRepository userRepository;
    @Override
    public User addUser(User user) {
        if(userExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exist");
        }
        return userRepository.save(user);
    }

    private boolean userExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user,  Long Id) {

        return userRepository.findById(Id).map(st ->{
            st.setFirstName(user.getFirstName());
            st.setLastName(user.getLastName());
            st.setEmail(user.getEmail());
            st.setDepartment(user.getDepartment());
            return userRepository.save(st);
        }).orElseThrow(()->new UserNotFoundException("Sorry, User not found"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Sorry, User not found"));
    }

    @Override
    public void deleteUser(Long Id) {
        if(!userRepository.existsById(Id)){
            throw new UserNotFoundException("Sorry, User not found exception");
        }
        userRepository.deleteById(Id);
        System.out.println("User deleted");
    }
}
