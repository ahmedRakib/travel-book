package com.travelbook.app.service;

import com.travelbook.app.entity.Countries;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.CountriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Countries Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CountriesService {
    private final CountriesRepository countriesRepository;

    public List<Countries> getCountries() {
        List<Countries> countries = countriesRepository.findAll();
        if (countries.size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return countries;
    }

    public Countries getCountries(long id) {
        Optional<Countries> country = countriesRepository.findById(id);
        if (!country.isPresent()) {
            throw new EntityNotFound("Country not found");
        }
        return country.get();
    }
}
