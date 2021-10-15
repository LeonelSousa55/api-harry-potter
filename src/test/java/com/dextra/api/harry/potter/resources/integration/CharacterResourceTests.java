package com.dextra.api.harry.potter.resources.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.dextra.api.harry.potter.Application;
import com.dextra.api.harry.potter.entities.Character;
import com.dextra.api.harry.potter.utils.PaginatedResponse;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CharacterResourceTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPaginatedCharacters() {

		assertEquals(201, addValidCharacter().getStatusCodeValue());

		String url = "http://localhost:" + port + "/characters";

		ParameterizedTypeReference<PaginatedResponse<Object>> responseType = new ParameterizedTypeReference<PaginatedResponse<Object>>() {
		};

		ResponseEntity<PaginatedResponse<Object>> result = restTemplate.exchange(url, HttpMethod.GET,
				getRequestEntity(), responseType);

		List<Object> employeeList = result.getBody().getContent();

		assertTrue(!employeeList.isEmpty());
	}

	@Test
	public void testFindExistentCharacterById() {

		ResponseEntity<Character> insertResponse = addValidCharacter();
		assertEquals(201, insertResponse.getStatusCodeValue());

		Character createdCharacter = insertResponse.getBody();

		String url = "http://localhost:" + port + "/characters/" + createdCharacter.getId();

		ResponseEntity<Character> findResponse = this.restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(),
				Character.class);

		Character foundCharacter = findResponse.getBody();

		Boolean isExpectedCharacterEqualsResponse = Objects.equals(foundCharacter, createdCharacter);

		assertTrue(isExpectedCharacterEqualsResponse);
	}

	@Test
	public void testFindNonExistentCharacterById() {

		String url = "http://localhost:" + port + "/characters/0";

		ResponseEntity<Character> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(),
				Character.class);

		assertEquals(404, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testAddCharacter() {

		assertEquals(201, addValidCharacter().getStatusCodeValue());
	}

	@Test
	public void testUpdateExistentCharacter() {

		ResponseEntity<Character> insertResponse = addValidCharacter();
		assertEquals(201, insertResponse.getStatusCodeValue());

		Character characterToUpdate = insertResponse.getBody();
		characterToUpdate.setName("Harry Potter Novo");

		String url = "http://localhost:" + port + "/characters/" + characterToUpdate.getId();

		ResponseEntity<Character> updateResponse = this.restTemplate.exchange(url, HttpMethod.PUT,
				getRequestEntity(characterToUpdate), Character.class);

		Character updatedCharacter = updateResponse.getBody();

		Boolean isExpectedCharacterEqualsResponse = Objects.equals(updatedCharacter, characterToUpdate);

		assertTrue(isExpectedCharacterEqualsResponse);
	}

	@Test
	public void testUpdateNonExistentCharacter() {

		Character characterToUpdate = new Character();
		characterToUpdate.setId(0L);
		characterToUpdate.setName("Harry Potter Novo");

		String url = "http://localhost:" + port + "/characters/" + characterToUpdate.getId();

		ResponseEntity<Character> updateResponse = this.restTemplate.exchange(url, HttpMethod.PUT,
				getRequestEntity(characterToUpdate), Character.class);

		Character updatedCharacter = updateResponse.getBody();

		Boolean isExpectedCharacterEqualsResponse = Objects.equals(updatedCharacter, characterToUpdate);

		assertTrue(!isExpectedCharacterEqualsResponse);
	}

	@Test
	public void testDeleteExistentCharacter() {

		ResponseEntity<Character> insertResponse = addValidCharacter();
		assertEquals(201, insertResponse.getStatusCodeValue());

		Character createdCharacter = insertResponse.getBody();

		String url = "http://localhost:" + port + "/characters/" + createdCharacter.getId();

		ResponseEntity<Character> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, getRequestEntity(),
				Character.class);

		assertEquals(204, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testDeleteNonExistentCharacter() {

		String url = "http://localhost:" + port + "/characters/0";

		ResponseEntity<Character> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, getRequestEntity(),
				Character.class);

		assertEquals(404, responseEntity.getStatusCodeValue());
	}

	public ResponseEntity<Character> addValidCharacter() {

		String url = "http://localhost:" + port + "/characters";

		Character character = new Character(null, "Harry Potter", "student",
				"Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");

		ResponseEntity<Character> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
				getRequestEntity(character), Character.class);

		return responseEntity;
	}

	private HttpEntity<Object> getRequestEntity() {

		return new HttpEntity<Object>(getCustomHeaders());
	}

	private HttpEntity<Object> getRequestEntity(Object body) {

		return new HttpEntity<Object>(body, getCustomHeaders());
	}

	private HttpHeaders getCustomHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
}
