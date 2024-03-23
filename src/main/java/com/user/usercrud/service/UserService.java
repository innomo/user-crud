package com.user.usercrud.service;

import com.user.usercrud.exception.UserAlreadyExistException;
import com.user.usercrud.exception.UserNotFoundException;
import com.user.usercrud.model.User;
import com.user.usercrud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public User updateUser(User user, Long userId) {

        return userRepository.findById(userId).map(st ->{
            st.setFirstName(st.getFirstName());
            st.setLastname(st.getLastname());
            st.setEmail(st.getEmail());
            st.setDepartment(st.getDepartment());
            return userRepository.save(st);
        }).orElseThrow(()->new UserNotFoundException("Sorry User not found"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Sorry, User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("Sorry, User not found exception");
        }
        System.out.println("User deleted");
    }
}
