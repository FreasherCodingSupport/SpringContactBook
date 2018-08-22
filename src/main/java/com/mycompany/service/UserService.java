package com.mycompany.service;

import com.mycompany.exception.UserBlockedException;
import com.mycompany.domain.User;
import java.util.List;

public interface UserService {
    public static final Integer LOGIN_ACTIVE=1;
    public static final Integer LOGIN_BLOCKED=2;
    
    public static final Integer ROLE_ADMIN=1;
    public static final Integer ROLE_USER=2;
    
    /**
     * The method handle the user registration task.
     * @param u 
     */
    public void register(User u);
    /**
     * the method handle login operation(authentication) using given credentials,
     * it return object when failed.
     * @param username
     * @param password
     * @return 
     * @throws  com.mycompany.exception.UserBlockedException when user account is blocked.
     */
    public User login(String username, String password)throws UserBlockedException;
    
    /**
     * call this method to get list of registered users.
     * @return 
     */
    public List<User> getUserList();
    
    /**
     * This method changes user login status for details passed as argument.
     * @param userId
     * @param staus 
     */
    public void changeLoginStatus(Integer userId,Integer staus);
    
    /**
     * check user_name availability.
     * @param username
     * @return 
     */
    public Boolean isUserNameExist(String username);
}
