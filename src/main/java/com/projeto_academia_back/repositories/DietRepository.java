package com.projeto_academia_back.repositories;

import com.projeto_academia_back.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {}
