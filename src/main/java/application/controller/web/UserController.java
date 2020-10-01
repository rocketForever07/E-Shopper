package application.controller.web;

import application.data.model.User;
import application.data.service.UserService;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.user.ChangePasswordVM;
import application.model.viewmodel.user.UserDetailVM;
import application.model.viewmodel.user.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/detail")
    public String getDetail(Model model,
                            @Valid @ModelAttribute("productname")ProductVM productName){

        UserDetailVM vm=new UserDetailVM();

        UserVM userVM=new UserVM();

        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        User userEntity =userService.findUserByUsername(username);

        if (userEntity != null) {
            userVM.setAddress(userEntity.getAddress());
            userVM.setEmail(userEntity.getEmail());
            userVM.setAvatar(userEntity.getAvatar());
            userVM.setGender(userEntity.getGender());
            userVM.setPhoneNumber(userEntity.getPhoneNumber());
            userVM.setName(userEntity.getName());
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());

        model.addAttribute("user",userVM);
        model.addAttribute("vm",vm);
        return "/user-detail";

    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user")UserVM userVM){
        try{
            String username=SecurityContextHolder.getContext().getAuthentication().getName();

            User userEntity=userService.findUserByUsername(username);

            userEntity.setAddress(userVM.getAddress());
            userEntity.setEmail(userVM.getEmail());
            userEntity.setAvatar(userVM.getAvatar());
            userEntity.setGender(userVM.getGender());
            userEntity.setPhoneNumber(userVM.getPhoneNumber());
            userEntity.setName(userVM.getName());

            userService.updateUser(userEntity);

            return "redirect:/user/detail?updateSuccess";

        }catch (Exception e){

        }
        return "redirect:/user/detail?updateFalse";

    }

    @GetMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("productname")ProductVM productName,Model model){
        ChangePasswordVM changePasswordVM=new ChangePasswordVM();

        changePasswordVM.setLayoutHeaderVM(this.getLayoutHeaderVM());

        model.addAttribute("changePassword",changePasswordVM);
        return "/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("changePassword")ChangePasswordVM password,Model model){

        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity=userService.findUserByUsername(userName);

        if(password.getCurrentPassword() == null ||
                password.getNewPassword() == null ||
                password.getConfirmPassword() == null||
                ! passwordEncoder.matches(password.getCurrentPassword(),userEntity.getPasswordHash())||
                ! password.getNewPassword().equals(password.getConfirmPassword())){
            return "redirect:/user/change-password?fail";
        }
        userEntity.setPassword(password.getNewPassword());
        userEntity.setPasswordHash(passwordEncoder.encode(userEntity.getPassword()));
        userService.updateUser(userEntity);

        return "redirect:/";
    }



}
