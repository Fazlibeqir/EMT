package com.example.library.web;


import com.example.library.model.Country;
import com.example.library.model.dto.CountryDto;
import com.example.library.service.ContryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class ContryRestController {
    private final ContryService countryService;

    public ContryRestController(ContryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("")
    public List<Country> getAllCountries()
    {
        return countryService.listAllContries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id)
    {
        return countryService.findById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto)
    {
        return countryService.create(countryDto.getName(), countryDto.getContinent())
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id)
    {
        return countryService.delete(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody CountryDto countryDto)
    {
        return countryService.update(id, countryDto.getName(), countryDto.getContinent())
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
