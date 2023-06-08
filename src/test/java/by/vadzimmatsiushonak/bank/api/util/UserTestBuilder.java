package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.User;
import by.vadzimmatsiushonak.bank.api.model.entity.auth.Role;
import by.vadzimmatsiushonak.bank.api.model.entity.base.UserStatus;

public class UserTestBuilder {

    public static User ID1_ADMIN() {
        User admin = new User();
        admin.setId(1L);
        admin.setPassword("$2a$10$60pFUU0NmgZAGr8GvJwX3eM/3wW0RR7rRr2b519WFzijNjSr/TeNi");//pass
        admin.setLogin("admin");
        admin.setStatus(UserStatus.ACTIVE);
        admin.setRole(Role.ADMIN);
        return admin;
    }

    public static User ID2_VADZIM() {
        User vadzim = new User();
        vadzim.setId(2L);
        vadzim.setPassword("$2a$10$60pFUU0NmgZAGr8GvJwX3eM/3wW0RR7rRr2b519WFzijNjSr/TeNi");//pass
        vadzim.setLogin("Vadzim");
        vadzim.setStatus(UserStatus.ACTIVE);
        vadzim.setRole(Role.TECHNICAL_USER);
        return vadzim;
    }

    public static User ID3_NO_ACTIVE() {
        User andrey = new User();
        andrey.setId(3L);
        andrey.setPassword("$2a$10$60pFUU0NmgZAGr8GvJwX3eM/3wW0RR7rRr2b519WFzijNjSr/TeNi");//pass
        andrey.setLogin("Andrey");
        andrey.setStatus(UserStatus.BLOCKED);
        andrey.setRole(Role.TECHNICAL_USER);
        return andrey;
    }

}
