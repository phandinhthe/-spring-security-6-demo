package com.terry.security.demo.springsecurity6demo.user.api;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.service.UserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserController.class, UserControllerTest.TestSecurityConfig.class})
public class UserControllerTest {

	@EnableMethodSecurity
	static class TestSecurityConfig {

	}

	@Autowired
	UserController userController;

	@MockBean
	UserDetailService userDetailService;

	@Test
	@WithMockUser(authorities = "GUEST")
	public void testGetUsers_WhenRequestHasRoleGUEST_ThenThrowAccessDeniedException() {
		Assertions.assertThrows(AccessDeniedException.class, () -> userController.users());
	}

	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testGetUsers_WhenRequestHasRoleADMIN_ThenReturnUsers() {
		Mockito.when(userDetailService.users()).thenReturn(List.of(User.builder().id(1).name("testName").password("123").authorities("ADMIN").build()));
		List<User> users = (List<User>) userController.users();
		Assertions.assertEquals(1, users.get(0).id());
		Assertions.assertEquals("testName", users.get(0).name());
		Assertions.assertEquals(List.of("ADMIN"), users.get(0).authorities());
	}
}
