package com.gorbich.devopsbuddy.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gorbich.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.Role;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.User;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.gorbich.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.gorbich.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.gorbich.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.gorbich.devopsbuddy.enums.PlansEnum;
import com.gorbich.devopsbuddy.enums.RolesEnum;
import com.gorbich.devopsbuddy.utils.UserUtils;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoriesIntegrationTest {
	
	@Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Rule
    public TestName testName = new TestName(); 
    
    @org.junit.Before
    public void init() {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role userRole  = createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);
        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void createNewUser() throws Exception {
    	String username = testName.getMethodName();
    	String email = testName.getMethodName() + "@devopsbuddy.com";
    	
    	User basicUser = createUser(username, email);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }
    
    @Test
    public void testDeleteUser() throws Exception {
    	
    	String username = testName.getMethodName();
    	String email = testName.getMethodName() + "@devopsbuddy.com";
    	
        User basicUser = createUser(username, email);
        
        userRepository.delete(basicUser.getId());
    }    

    //-----------------> Private methods

    private Plan createPlan(PlansEnum plansEnum) {
        return new Plan(plansEnum);
    }

    private Role createRole(RolesEnum rolesEnum) {
        return new Role(rolesEnum);
    }
    
    private User createUser(String username, String email) {
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser(username, email);
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);
        return basicUser;
    }
}

