package com.example.library.service.impl;

import com.example.library.model.Country;
import com.example.library.model.exceptions.InvalidCountryId;
import com.example.library.repository.ContryRepository;
import com.example.library.service.ContryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements ContryService {
    private final ContryRepository contryRepository;

    public CountryServiceImpl(ContryRepository contryRepository) {
        this.contryRepository = contryRepository;
    }

    @Override
    public List<Country> listAllContries() {
        return this.contryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(this.contryRepository.findById(id).orElseThrow(InvalidCountryId::new));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(this.contryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = findById(id).orElseThrow(InvalidCountryId::new);
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(this.contryRepository.save(country));
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country = findById(id).orElseThrow(InvalidCountryId::new);
        this.contryRepository.delete(country);
        return Optional.of(country);
    }
}
