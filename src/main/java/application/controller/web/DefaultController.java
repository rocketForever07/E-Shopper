package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.User;
import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){ return "/login";  }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "/register";
    }


    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("403")
    public String error403(){
        return "error/403";
    }

    @PostMapping("/register-user")
    public String registerNewUser(@Valid @ModelAttribute("user") User user){

        StatusRegisterUserEnum status=userService.registerNewUser(user);
        if(status == StatusRegisterUserEnum.Existed_Username ||
            status == StatusRegisterUserEnum.Existed_Email){
            return "redirect:/register?fail";
        }
        return "redirect:/login";
    }

    @GetMapping("/ck-editer")
    public String ckEditer(){
        return "ck-editer";
    }

}
