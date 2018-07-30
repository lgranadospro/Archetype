package es.profile.phone;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import es.profile.phone.domain.User;

public class SpringBootRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";

	/* GET */
	private static void listAllUsers() {
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		User[] usersArray = restTemplate.getForObject(REST_SERVICE_URI + "/user/", User[].class);
		Collection<User> userList = Arrays.asList(usersArray);

		if (userList != null) {
			for (User u : userList) {
				System.out.println("User : " + u);
			}
		} else {
			System.out.println("No user exist----------");
		}
	}

	/* GET */
	private static void getUser() {
		System.out.println("Testing getUser API----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
		System.out.println(user);
	}

	/* POST */
	private static void createUser() {
		System.out.println("Testing create User API----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User(0, "Sarah", 51, 134);
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateUser() {
		System.out.println("Testing update User API----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User(1, "Tomy", 33, 70000);
		restTemplate.put(REST_SERVICE_URI + "/user/1", user);
		System.out.println(user);
	}

	/* DELETE */
	private static void deleteUser() {
		System.out.println("Testing delete User API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/3");
	}

	/* DELETE */
	private static void deleteAllUsers() {
		System.out.println("Testing all delete Users API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}

	public static void main(String args[]) {
		listAllUsers();
		getUser();
		createUser();
		listAllUsers();
		updateUser();
		listAllUsers();
		deleteUser();
		listAllUsers();
		deleteAllUsers();
		listAllUsers();
	}
}