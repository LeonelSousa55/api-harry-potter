package com.dextra.api.harry.potter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dextra.api.harry.potter.dto.CharacterDTO;
import com.dextra.api.harry.potter.entities.Character;
import com.dextra.api.harry.potter.repositories.CharacterRepository;
import com.dextra.api.harry.potter.services.exceptions.ResourceNotFoundException;



@Service
public class CharacterService {

	@Autowired
	private CharacterRepository repository;

	@Transactional(readOnly = true)
	public Page<CharacterDTO> findAllPaged(PageRequest pageRequest) {

		Page<Character> list = repository.findAll(pageRequest);
		return list.map(x -> new CharacterDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CharacterDTO findById(Long id) {

		Optional<Character> obj = repository.findById(id);
		Character entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CharacterDTO(entity);
	}
}
