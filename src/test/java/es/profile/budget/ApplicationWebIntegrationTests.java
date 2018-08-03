package es.profile.parameter;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.profile.parameter.domain.Parameter;
import es.profile.parameter.service.ParameterService;

/**
 * The Class ApplicationWebIntegrationTests.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest
public class ApplicationWebIntegrationTests {

	/** The mvc. */
	@Autowired
	private MockMvc mvc;

	/** The parameter service. */
	@MockBean
	private ParameterService paramService;

	/**
	 * List all parameters.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void listAllParameters() throws Exception {
		given(paramService.getAll()).willReturn(populateDummyParameters());

		this.mvc.perform(get("/parameters").headers(generateHeaders("GET"))).andExpect(status().isOk());
	}

	/**
	 * Gets the parameter.
	 *
	 * @return the parameter
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getParameter() throws Exception {
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		this.mvc.perform(get("/parameters/{id}", 1).headers(generateHeaders("GET"))).andExpect(status().isOk())
				.andExpect(content().json(new ObjectMapper().writeValueAsString(createParamMock())));
	}

	
	@Test
	public void findParameter() throws Exception {
		given(paramService.findByValue(Mockito.anyLong())).willReturn(populateDummyParameters());
		
		this.mvc.perform(get("/parameters/value")
				.param("value","32")
				.headers(generateHeaders("GET"))).
		andExpect(status().isOk());
		
	}
	
	/**
	 * Creates the parameter.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createParameter() throws Exception {
		given(paramService.isParamExist(Mockito.anyString())).willReturn(Boolean.FALSE);

		this.mvc.perform(post("/parameters").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(createParamMock())).headers(generateHeaders("POST")))
				.andExpect(status().isCreated());
	}

	/**
	 * Update parameter.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updateParameter() throws Exception {
		Parameter mockParam = createParamMock();
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		mockParam.setId(mockParam.getId());
		mockParam.setValue(50);
		mockParam.setApplication("New Name");
		mockParam.setInitial("50000");

		this.mvc.perform(put("/parameters").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(mockParam)).headers(generateHeaders("PUT")))
				.andExpect(status().isNoContent());
	}

	/**
	 * Delete parameter.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void deleteParameter() throws Exception {
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		this.mvc.perform(delete("/parameters/{id}", 1).headers(generateHeaders("DELETE")))
				.andExpect(status().isNoContent());
	}

	/**
	 * Delete all parameters.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void deleteAllParameters() throws Exception {
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		this.mvc.perform(delete("/parameters").headers(generateHeaders("DELETE"))).andExpect(status().isNoContent());
	}

	/**
	 * Creates the parameter mock.
	 *
	 * @return the parameter
	 */
	private Parameter createParamMock() {
		Parameter u = new Parameter();
		u.setValue(25);
		u.setId("123456789123456");
		u.setInitial("Name");
		u.setApplication("25000");
		return u;
	}

	/**
	 * Generate headers.
	 *
	 * @param method
	 *            the method
	 * @return the http headers
	 */
	private HttpHeaders generateHeaders(String method) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Request-Method", method);
		headers.add("Origin", "localhost");
		return headers;
	}

	private static List<Parameter> populateDummyParameters() {
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter());
		params.add(new Parameter());
		params.add(new Parameter());
		params.add(new Parameter());
		return params;
	}
}