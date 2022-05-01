package users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import users.model.Role;
import users.model.User;
import users.service.RoleService;
import users.service.UserService;
import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

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
        user.setFirstName("Anna");
        user.setLastName("Tsypaleva");
        user.setAge((byte) 35);
        user.addRole(role1);

        User user1 = new User();
        user1.setEmail("irina@outlook.com");
        user1.setPassword("irina");
        user1.setFirstName("Irina");
        user1.setLastName("Petrova");
        user1.setAge((byte) 36);
        user1.addRole(role2);

        User user2 = new User();
        user2.setEmail("olga@mail.ru");
        user2.setPassword("olga");
        user2.setFirstName("Olga");
        user2.setLastName("Sorokina");
        user2.setAge((byte) 37);
        user2.addRole(role1);
        user2.addRole(role2);

        User user3 = new User();
        user3.setEmail("kira@mail.ru");
        user3.setPassword("kira");
        user3.setFirstName("Kira");
        user3.setLastName("Kremkova");
        user3.setAge((byte) 38);
//        user3.addRole(role1);
        user3.addRole(role2);

        userService.save(user);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }
}