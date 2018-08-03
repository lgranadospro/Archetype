package es.profile.parameter;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import es.profile.parameter.domain.Parameter;
import es.profile.parameter.domain.User;

public class SpringBootRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";

	/* GET */
	private static void listAllParameters() {
		System.out.println("Testing listAllParameters API-----------");

		RestTemplate restTemplate = new RestTemplate();
		Parameter[] parametersArray = restTemplate.getForObject(REST_SERVICE_URI + "/parameter/", Parameter[].class);
		Collection<Parameter> parameterList = Arrays.asList(parametersArray);

		if (parameterList != null) {
			for (Parameter u : parameterList) {
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
		Parameter parameter = restTemplate.getForObject(REST_SERVICE_URI + "/parameter/1", Parameter.class);
		System.out.println(parameter);
	}

	/* POST */
	private static void createParameter() {
		System.out.println("Testing create Parameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		Parameter param = new Parameter();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/parameter/", param, Parameter.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateParameter() {
		System.out.println("Testing update Parameter API----------");
		RestTemplate restTemplate = new RestTemplate();
		Parameter param = new Parameter();
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
		listAllParameters();
		getParameter();
		createParameter();
		listAllParameters();
		updateParameter();
		listAllParameters();
		deleteParameter();
		listAllParameters();
		deleteAllParameters();
		listAllParameters();
	}
}