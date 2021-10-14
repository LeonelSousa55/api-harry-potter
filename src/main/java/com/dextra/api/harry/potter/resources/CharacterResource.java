package com.dextra.api.harry.potter.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dextra.api.harry.potter.dto.CharacterDTO;
import com.dextra.api.harry.potter.services.CharacterService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/characters", produces = "application/json", consumes = "application/json")
public class CharacterResource {

	@Autowired
	private CharacterService service;

	@GetMapping
	@ApiOperation(value = "Search all characters")
	public ResponseEntity<Page<CharacterDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(required = false) String house) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<CharacterDTO> list = service.findAllPaged(pageRequest, house);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Find one character by id")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Entity not found") })
	public ResponseEntity<CharacterDTO> findById(@PathVariable Long id) {

		CharacterDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@ApiOperation(value = "Creating a new character")
	@ApiResponses(value = { @ApiResponse(code = 422, message = "House with id: {HOUSE_ID} not found."),
			@ApiResponse(code = 400, message = "Validation Failed") })
	public ResponseEntity<CharacterDTO> insert(@Valid @RequestBody CharacterDTO dto) {

		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Updating one character")
	@ApiResponses(value = { @ApiResponse(code = 422, message = "House with id: {HOUSE_ID} not found."),
			@ApiResponse(code = 400, message = "Validation Failed"),
			@ApiResponse(code = 404, message = "Id not found {CHARACTER_ID}") })
	public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @Valid @RequestBody CharacterDTO dto) {

		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleting one character")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Id not found {CHARACTER_ID}") })
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
