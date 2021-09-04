package com.example.webapp.config;

import com.example.webapp.models.Role;
import com.example.webapp.models.User;
import com.example.webapp.service.RoleService;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user = new User();
        user.setEmail("anna@gmail.ru");
        user.setPassword("anna");
        user.setName("Anna");
        user.setSurname("Tsypaleva");
        user.setAge((byte) 35);
        user.addRole(role1);

        User user1 = new User();
        user1.setEmail("irina@outlook.com");
        user1.setPassword("irina");
        user1.setName("Irina");
        user1.setSurname("Petrova");
        user1.setAge((byte) 36);
        user1.addRole(role2);

        User user2 = new User();
        user2.setEmail("olga@mail.ru");
        user2.setPassword("olga");
        user2.setName("Olga");
        user2.setSurname("Sorokina");
        user2.setAge((byte) 37);
        user2.addRole(role1);
        user2.addRole(role2);

        User user3 = new User();
        user3.setEmail("kira@mail.ru");
        user3.setPassword("kira");
        user3.setName("Kira");
        user3.setSurname("Kremkova");
        user3.setAge((byte) 38);
//        user3.addRole(role1);
        user3.addRole(role2);

        userService.save(user);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }
}