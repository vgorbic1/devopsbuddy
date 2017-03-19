package com.gorbich.devopsbuddy.test.integration;

import com.gorbich.devopsbuddy.backend.persistence.domain.backend.Role;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.User;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.gorbich.devopsbuddy.backend.service.UserService;
import com.gorbich.devopsbuddy.enums.PlansEnum;
import com.gorbich.devopsbuddy.enums.RolesEnum;
import com.gorbich.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }
}
