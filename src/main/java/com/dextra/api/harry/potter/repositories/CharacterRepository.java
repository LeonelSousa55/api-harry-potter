package com.dextra.api.harry.potter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dextra.api.harry.potter.entities.Character;


@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{

}
