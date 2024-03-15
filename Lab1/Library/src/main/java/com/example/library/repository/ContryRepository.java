package com.example.library.repository;

import com.example.library.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContryRepository extends JpaRepository<Country,Long> {
}
