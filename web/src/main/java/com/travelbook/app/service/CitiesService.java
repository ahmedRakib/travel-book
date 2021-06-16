package com.travelbook.app.service;

import com.travelbook.app.entity.Cities;
import com.travelbook.app.entity.States;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * City service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CitiesService {
    private final CitiesRepository citiesRepository;

    public Cities getStatesById(long id) {
        Optional<Cities> cities = citiesRepository.findById(id);
        if (!cities.isPresent()) {
            throw new EntityNotFound("No states found");
        }
        return cities.get();
    }

    public List<Cities> getSatesByCountryId(long id) {
        List<Cities> cities = citiesRepository.findAllByStateId(id);
        if (cities.size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return cities;
    }
}
