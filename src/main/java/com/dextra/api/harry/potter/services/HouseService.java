package com.dextra.api.harry.potter.services;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dextra.api.harry.potter.entities.House;
import com.dextra.api.harry.potter.entities.ListHouse;
import com.dextra.api.harry.potter.utils.Constant;

@Service
public class HouseService {

	private RestTemplate restTemplate;

	public HouseService() {

		this.restTemplate = new RestTemplate();
	}

	public Boolean isValidHouse(String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("apikey", Constant.HOUSE_API_KEY);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// Making a request to the potterAPI to retrieve the aviable houses.
		ResponseEntity<ListHouse> responseEntity = restTemplate.exchange(Constant.URL_SEARCH_HOUSES, HttpMethod.GET,
				entity, ListHouse.class);

		// Verifying if the request is successful.
		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			ListHouse listHouse = responseEntity.getBody();

			// Verifying if the informed house exists in the response.
			for (House house : listHouse.getHouses()) {

				if (house.getId().equals(id)) {

					return true;
				}
			}
		}

		return false;
	}
}
