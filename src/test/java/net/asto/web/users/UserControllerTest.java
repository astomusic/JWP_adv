package net.asto.web.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import net.asto.dao.users.UserDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	private UserDao userDao;
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(userController).alwaysExpect(status().isMovedTemporarily()).build();
	}

	@Test
	public void creatWhenValid() throws Exception {
		this.mockMvc
				.perform(post("/users").param("email", "test@as.com").param("password", "1234").param("passwordConfirm", "1234"))
				.andDo(print())
				.andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void creatWhenInvalid() throws Exception {
		this.mockMvc
				.perform(post("/users").param("email", "test").param("password", "1234").param("passwordConfirm", "1234"))
				.andDo(print())
				.andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/"));
	}
}
