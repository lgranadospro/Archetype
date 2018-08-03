package es.profile.budget;

import java.net.URI;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import es.profile.budget.domain.Budget;

public class SpringBootRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";

	/* GET */
	private static void listAllBudgets() {
		System.out.println("Testing listAllParameters API-----------");

		RestTemplate restTemplate = new RestTemplate();
		Budget[] parametersArray = restTemplate.getForObject(REST_SERVICE_URI + "/parameter/", Budget[].class);
		Collection<Budget> parameterList = Arrays.asList(parametersArray);

		if (parameterList != null) {
			for (Budget u : parameterList) {
				System.out.println("Parameter : " + u);
			}
		} else {
			System.out.println("No parameter exist----------");
		}
	}

	/* GET */
	private static void getParameter() {
		System.out.println("Testing getParameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		Budget parameter = restTemplate.getForObject(REST_SERVICE_URI + "/parameter/1", Budget.class);
		System.out.println(parameter);
	}

	/* POST */
	private static void createParameter() {
		System.out.println("Testing create Parameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		Budget param = new Budget();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/parameter/", param, Budget.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateParameter() {
		System.out.println("Testing update Parameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		Budget param = new Budget();
		restTemplate.put(REST_SERVICE_URI + "/parameter/1", param);
		System.out.println(param);
	}

	/* DELETE */
	private static void deleteParameter() {
		System.out.println("Testing delete Parameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/parameter/3");
	}

	/* DELETE */
	private static void deleteAllParameters() {
		System.out.println("Testing all delete Parameters API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/parameter/");
	}

	public static void main(String args[]) {
		listAllBudgets();
		getParameter();
		createParameter();
		listAllBudgets();
		updateParameter();
		listAllBudgets();
		deleteParameter();
		listAllBudgets();
		deleteAllParameters();
		listAllBudgets();
	}
}