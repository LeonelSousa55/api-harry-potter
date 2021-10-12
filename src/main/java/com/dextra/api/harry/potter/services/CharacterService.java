package com.dextra.api.harry.potter.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dextra.api.harry.potter.dto.CharacterDTO;
import com.dextra.api.harry.potter.entities.Character;
import com.dextra.api.harry.potter.repositories.CharacterRepository;



@Service
public class CharacterService {

	@Autowired
	private CharacterRepository repository;

	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll() {

		List<Character> list = repository.findAll();

		return list.stream().map(x -> new CharacterDTO(x)).collect(Collectors.toList());
	}
}
