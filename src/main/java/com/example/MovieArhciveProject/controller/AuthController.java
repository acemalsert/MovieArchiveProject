package com.example.MovieArhciveProject.controller;

import com.example.MovieArhciveProject.models.UserEntity;
import com.example.MovieArhciveProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService){
        this.userService  = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user",userEntity);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute ("user") UserEntity user, BindingResult result, Model model){

        // Checks username is used before to register
        UserEntity userServiceByUserName  = userService.findByUsername(user.getUsername());
        if(userServiceByUserName != null && userServiceByUserName.getUsername() !=null && !userServiceByUserName.getUsername().isEmpty()){
            result.rejectValue("username","This user name is already being used!");
        }

        // Checks if the email is used before to register
        UserEntity userEntityByEmail = userService.findByEmail(user.getEmail());
        if(userEntityByEmail != null && userEntityByEmail.getEmail() !=null && !userEntityByEmail.getEmail().isEmpty()){
            result.rejectValue("email","This email is already being used in the movie archive system!");
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String loginPage(){
        return
                "login";
    }
}
