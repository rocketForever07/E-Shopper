package application.data.service;

import application.constant.RoleIdConstant;
import application.constant.StatusRegisterUserEnum;
import application.data.model.Role;
import application.data.model.User;
import application.data.model.UserRole;
import application.data.repository.RoleRepository;
import application.data.repository.UserRepository;
import application.data.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getListAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        try {
            userRepository.delete(userId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public User findOne(int userId) {
        return userRepository.findOne(userId);
    }

    public StatusRegisterUserEnum registerNewUser(User user){
        try{
            // check existed user
            if(findUserByUsername(user.getUserName()) != null){
                return StatusRegisterUserEnum.Existed_Username;
            }

            if(findUserByEmail(user.getEmail()) != null){
                return StatusRegisterUserEnum.Existed_Email;
            }

            //hash pass
            user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
            user.setCreadedDate(new Date());

            //save user
            userRepository.save(user);

            //insert new role
            UserRole userRole=new UserRole();
            userRole.setRoleId(RoleIdConstant.Role_User);
            userRole.setUserId(user.getId());

            userRoleRepository.save(userRole);

            return StatusRegisterUserEnum.Success;


        }catch (Exception e){
            return StatusRegisterUserEnum.Error_OnSystem;
        }
    }

    public List<Role> getListRole() {

        return StreamSupport
                .stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    public User findUserByEmail(String email) {
        return StreamSupport
                .stream(userRepository.findByEmail(email).spliterator(), false)
                .findFirst().orElse(null);
    }

    public User findUserByUsername(String username) {
        return StreamSupport
                .stream(userRepository.findByUsername(username).spliterator(), false)
                .findFirst().orElse(null);
    }

    public List<Role> getActiveListRole(int userId) {
        List<UserRole> listUserRoles = StreamSupport
                .stream(userRoleRepository.findRolesOfUser(userId).spliterator(), false).collect(Collectors.toList());

        return getListRole().stream().filter(role -> {
            return (listUserRoles.stream().filter(userRole -> userRole.getRoleId() == role.getId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }



}