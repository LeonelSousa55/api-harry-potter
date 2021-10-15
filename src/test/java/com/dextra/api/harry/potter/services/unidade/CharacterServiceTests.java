package com.dextra.api.harry.potter.services.unidade;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dextra.api.harry.potter.dto.CharacterDTO;
import com.dextra.api.harry.potter.entities.Character;
import com.dextra.api.harry.potter.repositories.CharacterRepository;
import com.dextra.api.harry.potter.services.CharacterService;
import com.dextra.api.harry.potter.services.HouseService;
import com.dextra.api.harry.potter.services.exceptions.DataBaseException;
import com.dextra.api.harry.potter.services.exceptions.ResourceNotFoundException;
import com.dextra.api.harry.potter.services.exceptions.ValidationFailException;

@ExtendWith(SpringExtension.class)
public class CharacterServiceTests {

	@InjectMocks
	private CharacterService service;

	@Mock
	private CharacterRepository repository;

	@Mock
	private HouseService houseService;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private String existingHouse;
	private String nonExistingHouse;
	private PageImpl<Character> page;
	private Character character;
	private CharacterDTO characterDTO;

	@BeforeEach
	void setUp() {

		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		existingHouse = "houseExisting";
		nonExistingHouse = "houseNonExisting";
		character = new Character(existingId, "Harry Potter", "student", "Hogwarts School of Witchcraft and Wizardry",
				"1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");
		characterDTO = new CharacterDTO(1L, "Harry Potter", "student", "Hogwarts School of Witchcraft and Wizardry",
				existingHouse, "stag");
		page = new PageImpl<>(List.of(character));

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		Mockito.when(repository.findByHouse((Pageable) ArgumentMatchers.any(), (String) ArgumentMatchers.any()))
				.thenReturn(page);

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(character);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(character));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.getOne(existingId)).thenReturn(character);
		Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(houseService.isValidHouse(existingHouse)).thenReturn(true);
		Mockito.when(houseService.isValidHouse(nonExistingHouse)).thenThrow(ValidationFailException.class);

		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).doNothing().when(repository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).doNothing().when(repository).deleteById(dependentId);
	}

	@Test
	public void insertShouldReturnCharacterDTO() {

		CharacterDTO result = service.insert(characterDTO);

		Assertions.assertNotNull(result);
	}

	@Test
	public void updateShouldReturnCharacterDTOWhenIdExists() {

		CharacterDTO result = service.update(existingId, characterDTO);

		Assertions.assertNotNull(result);
	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdExists() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {

			service.update(nonExistingId, characterDTO);
		});
	}

	@Test
	public void findByIdShouldReturnCharacterDTOWhenIdExists() {

		CharacterDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {

			service.findById(nonExistingId);
		});
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		PageRequest pageable = PageRequest.of(0, 10);

		Page<CharacterDTO> result = service.findAllPaged(pageable, null);

		Assertions.assertNotNull(result);

		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
	}

	@Test
	public void findAllPagedShouldReturnPageFilterByHouse() {

		String house = "1760529f-6d51-4cb1-bcb1-25087fce5bde";

		PageRequest pageable = PageRequest.of(0, 10);

		Page<CharacterDTO> result = service.findAllPaged(pageable, house);

		Assertions.assertNotNull(result);

		Mockito.verify(repository, Mockito.times(1)).findByHouse(pageable, house);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {

		Assertions.assertDoesNotThrow(() -> {

			service.delete(existingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {

			service.delete(nonExistingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldThrowDataBaseExceptionWhenDependentId() {

		Assertions.assertThrows(DataBaseException.class, () -> {

			service.delete(dependentId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
	}
}
