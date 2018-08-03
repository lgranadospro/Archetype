package es.profile.budget;

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
import org.mockito.InjectMocks;
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

import es.profile.budget.domain.Budget;
import es.profile.budget.service.BudgetService;
import es.profile.budget.web.controller.BudgetController;

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
	@InjectMocks
	private BudgetController controller;
	/** The budget service. */
	@MockBean
	private BudgetService paramService;

	/**
	 * List all budgets.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void listAllParameters() throws Exception {
		given(paramService.getAll()).willReturn(populateDummyParameters());

		this.mvc.perform(get("/budgets").headers(generateHeaders("GET"))).andExpect(status().isOk());
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getParameter() throws Exception {
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		this.mvc.perform(get("/budgets/{id}", 1).headers(generateHeaders("GET"))).andExpect(status().isOk())
				.andExpect(content().json(new ObjectMapper().writeValueAsString(createParamMock())));
	}

	
	@Test
	public void findParameter() throws Exception {
		given(paramService.findByName(Mockito.anyString())).willReturn(populateDummyParameters());
		
		this.mvc.perform(get("/budget/name")
				.param("value","32")
				.headers(generateHeaders("GET"))).
		andExpect(status().isOk());
		
	}
	
	/**
	 * Creates the budget.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void createParameter() throws Exception {
		paramService.create(createParamMock());

		this.mvc.perform(post("/budgets").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(createParamMock())).headers(generateHeaders("POST")))
				.andExpect(status().isCreated());
	}
	

	/**
	 * Update budget.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void updateParameter() throws Exception {
		Budget oldParam = createParamMock();
		Budget newParam = createParamMock();
		newParam.setId("12345");
		newParam.setPrize("newApplication");
		
		given(paramService.get(Mockito.anyString())).willReturn(oldParam);
		paramService.update(newParam,oldParam.getId());
		

		this.mvc.perform(put("/budgets/"+oldParam.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(newParam)).headers(generateHeaders("PUT")))
				.andExpect(status().isNoContent());
	}

	/**
	 * Delete budget.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void deleteParameter() throws Exception {
		given(paramService.get(Mockito.anyString())).willReturn(createParamMock());

		this.mvc.perform(delete("/budgets/{id}", 1).headers(generateHeaders("DELETE")))
				.andExpect(status().isNoContent());
	}

	/**
	 * Delete all budgets.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void deleteAllParameters() throws Exception {
		this.mvc.perform(delete("/budgets").headers(generateHeaders("DELETE"))).andExpect(status().isNoContent());
	}

	/**
	 * Creates the budget mock.
	 *
	 * @return the budget
	 */
	private Budget createParamMock() {
		Budget u = new Budget();
		u.setObjective(25);
		u.setId("123456789123456");
		u.setName("Name");
		u.setPrize("25000");
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

	private static List<Budget> populateDummyParameters() {
		List<Budget> params = new ArrayList<Budget>();
		params.add(new Budget());
		params.add(new Budget());
		params.add(new Budget());
		params.add(new Budget());
		return params;
	}
}