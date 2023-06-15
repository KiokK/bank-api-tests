package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.User;
import by.vadzimmatsiushonak.bank.api.model.entity.auth.Role;
import by.vadzimmatsiushonak.bank.api.model.entity.base.UserStatus;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_BLOCKED_USER;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_USER;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_USER_ADMIN;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_LOGIN_BLOCKED_USER;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_LOGIN_USER;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_LOGIN_USER_ADMIN;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_PASSWORD;

public class UserTestBuilder {

    public static final int COUNT = 3;

    public static User newUserAdmin() {
        User admin = new User();
        admin.setId(TEST_ID_USER_ADMIN);
        admin.setPassword(TEST_PASSWORD);
        admin.setLogin(TEST_LOGIN_USER_ADMIN);
        admin.setStatus(UserStatus.ACTIVE);
        admin.setRole(Role.ADMIN);
        return admin;
    }

    public static User newTechnicalUser() {
        User vadzim = new User();
        vadzim.setId(TEST_ID_USER);
        vadzim.setPassword(TEST_PASSWORD);
        vadzim.setLogin(TEST_LOGIN_USER);
        vadzim.setStatus(UserStatus.ACTIVE);
        vadzim.setRole(Role.TECHNICAL_USER);
        return vadzim;
    }

    public static User newBlockedUser() {
        User andrey = new User();
        andrey.setId(TEST_ID_BLOCKED_USER);
        andrey.setPassword(TEST_PASSWORD);
        andrey.setLogin(TEST_LOGIN_BLOCKED_USER);
        andrey.setStatus(UserStatus.BLOCKED);
        andrey.setRole(Role.TECHNICAL_USER);
        return andrey;
    }

}
