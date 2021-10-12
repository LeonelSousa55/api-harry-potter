package com.dextra.api.harry.potter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dextra.api.harry.potter.entities.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

	Page<Character> findByHouse(Pageable pageable, String house);
}
