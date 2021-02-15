package com.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.entity.Person;
import com.rest.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RestprojectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonService mockPersonService;


	@Test
	void should_createPerson() throws Exception {

		Person person = new Person();
		person.setId(1);
		person.setAge("21");
		person.setFavourite_colour("Blue");
		person.setFirst_name("Kunal");
		person.setLast_name("Kher");

		String accessToken = getAccessToken();

		Mockito.when(mockPersonService.createPerson(person)).thenReturn(person);

		mockMvc.perform(post("/api/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization","Bearer "+ accessToken)
				.content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Kunal"));


	}

	@Test
	void shouldFailed_APICallWithoutToken() throws Exception {
		mockMvc.perform(get("/api/persons")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());

	}

	@Test
	void test_getPerson() throws Exception {

		Person person = new Person();
		person.setId(1);
		person.setAge("21");
		person.setFavourite_colour("Blue");
		person.setFirst_name("Kunal");
		person.setLast_name("Kher");

		String accessToken = getAccessToken();

		Mockito.when(mockPersonService.getPersonById(Mockito.eq(1))).thenReturn(person);


		mockMvc.perform(get("/api/persons/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization","Bearer "+ accessToken))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Kunal"));

	}

	@Test
	void test_updatePerson() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setAge("21");
		person.setFavourite_colour("Blue");
		person.setFirst_name("Kunal");
		person.setLast_name("Kher");

		String accessToken = getAccessToken();
		Mockito.when(mockPersonService.updatePerson(Mockito.any(Person.class),Mockito.eq(1))).thenReturn(person);

		mockMvc.perform(put("/api/persons/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(person))
				.header("Authorization","Bearer "+ accessToken))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Kunal"));

	}

	@Test
	void test_deletePerson() throws Exception {

		String accessToken = getAccessToken();
		Mockito.doNothing().when(mockPersonService).deletePerson(Mockito.eq(1));

		mockMvc.perform(delete("/api/persons/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization","Bearer "+ accessToken))
				.andExpect(status().isNoContent());
	}

	private String getAccessToken() throws Exception {
		String clientID = "client";
		String secret = "secret";
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "client_credentials");
		params.add("client_id", clientID);
		params.add("client_secret", secret);

		ResultActions result
				= mockMvc.perform(post("/oauth/token")
				.params(params)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		java.lang.String access_token = jsonParser.parseMap(resultString).get("access_token").toString();
		return access_token;
	}

}
