package es.profile.phone;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.profile.phone.domain.User;
import es.profile.phone.service.UserService;
import es.profile.phone.web.controller.UserController;

/**
 * The Class ApplicationWebIntegrationTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class ApplicationWebIntegrationTests {

	/** The mvc. */
	@Autowired
	private MockMvc mvc;

	/** The user service. */
	@MockBean
	private UserService userService;

	/**
	 * List all users.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void listAllUsers() throws Exception {
		given(userService.findAllUsers()).willReturn(populateDummyUsers());

		this.mvc.perform(get("/api/user/").headers(generateHeaders("GET")))
		.andExpect(status().isOk());
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 * @throws Exception the exception
	 */
	@Test
	public void getUser() throws Exception {
		given(userService.findById(Mockito.anyLong())).willReturn(createUserMock());

		this.mvc.perform(get("/api/user/{id}", 1).headers(generateHeaders("GET")))
		.andExpect(status().isOk())
		.andExpect(content().json(new ObjectMapper().writeValueAsString(createUserMock())));
	}

	/**
	 * Creates the user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void createUser() throws Exception {
		given(userService.isUserExist(Mockito.any(Long.class))).willReturn(Boolean.FALSE);

		this.mvc.perform(post("/api/user/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(createUserMock()))
				.headers(generateHeaders("POST")))
		.andExpect(status().isCreated());
	}

	/**
	 * Update user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUser() throws Exception {
		User mockUser = createUserMock();
		given(userService.findById(Mockito.anyLong())).willReturn(createUserMock());

		mockUser.setId(mockUser.getId());
		mockUser.setAge(50);
		mockUser.setName("New Name");
		mockUser.setSalary(50000);

		this.mvc.perform(put("/api/user/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(mockUser)).headers(generateHeaders("PUT")))
		.andExpect(status().isNoContent());
	}

	/**
	 * Delete user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUser() throws Exception {
		given(userService.findById(Mockito.anyLong())).willReturn(createUserMock());

		this.mvc.perform(delete("/api/user/{id}", 1).headers(generateHeaders("DELETE")))
		.andExpect(status().isNoContent());
	}

	/**
	 * Delete all users.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteAllUsers() throws Exception {
		given(userService.findById(Mockito.anyLong())).willReturn(createUserMock());

		this.mvc.perform(delete("/api/user/").headers(generateHeaders("DELETE")))
		.andExpect(status().isNoContent());
	}

	/**
	 * Creates the user mock.
	 *
	 * @return the user
	 */
	private User createUserMock() {
		User u = new User();
		u.setAge(25);
		u.setId(1);
		u.setName("Name");
		u.setSalary(25000);
		return u;
	}

	/**
	 * Generate headers.
	 *
	 * @param method the method
	 * @return the http headers
	 */
	private HttpHeaders generateHeaders(String method) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Request-Method", method);
		headers.add("Origin", "localhost");
		return headers;
	}
	
	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1L, "Sam", 30, 70000));
		users.add(new User(2L, "Tom", 40, 50000));
		users.add(new User(3L, "Jerome", 45, 30000));
		users.add(new User(3L, "Silvia", 50, 40000));
		return users;
	}
}