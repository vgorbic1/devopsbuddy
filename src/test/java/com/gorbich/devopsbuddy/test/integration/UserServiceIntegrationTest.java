package com.gorbich.devopsbuddy.test.integration;

import com.gorbich.devopsbuddy.backend.persistence.domain.backend.Role;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.User;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.gorbich.devopsbuddy.backend.service.UserService;
import com.gorbich.devopsbuddy.enums.PlansEnum;
import com.gorbich.devopsbuddy.enums.RolesEnum;
import com.gorbich.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
    private UserService userService;

    @Test
    public void testCreateNewUser() throws Exception {

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser();
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

    }
}
