package com.gorbich.devopsbuddy;

import com.gorbich.devopsbuddy.backend.persistence.domain.backend.Role;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.User;
import com.gorbich.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.gorbich.devopsbuddy.backend.service.UserService;
import com.gorbich.devopsbuddy.enums.PlansEnum;
import com.gorbich.devopsbuddy.enums.RolesEnum;
import com.gorbich.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String username = "proUser";
		String email = "proUser@devopsbuddy.com";
		
		User user = UserUtils.createBasicUser(username, email);
		
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.PRO)));
		
		LOG.debug("Creating user with username {}", user.getUsername());
		
		userService.createUser(user, PlansEnum.PRO, userRoles);
		
		LOG.info("User {} created", user.getUsername());
	}
}
