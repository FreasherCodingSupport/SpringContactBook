package com.mycompany.controller;

import com.mycompany.command.LoginCommand;
import com.mycompany.command.UserCommand;
import com.mycompany.domain.User;
import com.mycompany.exception.UserBlockedException;
import com.mycompany.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = {"/","/index"})
    public String index(Model m){
        m.addAttribute("command",new LoginCommand());
        return "index";//jsp   
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command")LoginCommand cmd,Model m,HttpSession session){
        try{
            User loggedInUser = userService.login(cmd.getUsername(),cmd.getPassword());
            if(loggedInUser == null){
              //failed 
              //add error message and go back to login_form.
              m.addAttribute("err","Login failed! Enter valid credentials.");
              return "index";//jsp 
              }else{
                //success
                //check the role and redirect to a appropriate dashboard
                  if(loggedInUser.getRole().equals(UserService.ROLE_ADMIN)){
                      addUserInSession(loggedInUser,session);
                      return "redirect:admin/dashboard"; 
                  }else if(loggedInUser.getRole().equals(UserService.ROLE_USER)){
                       addUserInSession(loggedInUser,session);
                      return "redirect:user/dashboard";  
                  }else{
                     //add error message and go back to login_form.
                     m.addAttribute("err","Invalid User Role");
                     return "index";//jsp
                      
                  }
            }
        } catch(UserBlockedException ex){
         //add error message and go back to login_form.
            m.addAttribute("err",ex.getMessage());
            return "index";//jsp(login form)
        }  
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index?act=lo";
    }

    @RequestMapping(value = "/user/dashboard")
    public String userDashboard(){
        return "dashboard_user";//jsp
    }

     @RequestMapping(value = "/admin/dashboard")
    public String addminDashboard(){
        return "dashboard_admin";//jsp
    }
    
    @RequestMapping(value = "/admin/users")
    public String getUserList(Model m){
        m.addAttribute("userList",userService.getUserList());
        return "users";//jsp
    }
    
    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model m){
        UserCommand cmd = new UserCommand();
        m.addAttribute("command",cmd);     
        return "reg_form";//jsp
    }
    
    @RequestMapping(value = "/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd,Model m){ 
        try{
        User user = cmd.getUser();
        user.setRole(UserService.ROLE_USER);
        user.setStatus(UserService.LOGIN_ACTIVE);
        userService.register(user);
        return "redirect:index?act=reg";//login page
        }catch(DuplicateKeyException e){
            e.printStackTrace();
            m.addAttribute("err","Username is already registered! Please select another username.");
            return "reg_form"; //jsp   
        }
    }
    
    private void addUserInSession(User u,HttpSession session){
        session.setAttribute("user",u);
        session.setAttribute("userId",u.getUserId());
        session.setAttribute("role",u.getRole());
    }
    
    @RequestMapping(value = "/admin/change_status")
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer status){
       try{
           userService.changeLoginStatus(userId, status);
           return "SUCCESS: status changed!";
       }catch(Exception e){
           e.printStackTrace();
          return "ERROR: Unable to change Status!";
       }
    }
    
    
    @RequestMapping(value = "/check_avail")
    @ResponseBody
    public String checkAvailability(@RequestParam String username){
       if(userService.isUserNameExist(username)){
           return "User with this Name is aleady registred. Choose new Name.";
       }else{
           return "ya! You can choose this one";
       }
       
    }
        
 }

