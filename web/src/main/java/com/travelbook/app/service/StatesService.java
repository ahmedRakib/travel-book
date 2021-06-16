package com.travelbook.app.service;

import com.travelbook.app.entity.States;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.StatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * State Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class StatesService {
    private final StatesRepository statesRepository;

    public States getStatesById(long id) {
        Optional<States> states = statesRepository.findById(id);
        if (!states.isPresent()) {
            throw new EntityNotFound("No States found");
        }
        return states.get();
    }

    public List<States> getSatesByCountryId(long id) {
        List<States> states = statesRepository.findAllByCountryId(id);
        if (states.size() == 0) {
            throw new EntityNotFound("No States Found");
        }
        return states;
    }
}
