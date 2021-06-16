package com.travelbook.app.controller;

import com.travelbook.app.entity.States;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.service.StatesService;
import com.travelbook.app.utils.Commons;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * States Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StatesController {
    private final StatesService statesService;

    /**
     * Get All States of a Country from database
     *
     * @param id State Id
     * @return ResponseEntity<Map < String, Object>> - Returns All States of a Country
     * @throws EntityNotFound     If No Data Found
     */
    @ApiOperation(value = "Returns All States of a Country")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/states/{id}")
    public ResponseEntity<Map<String, Object>> getStatesById(@PathVariable(value = "id") long id) {
        try {
            States state = statesService.getStatesById(id);
            return Commons.getMapResponseEntity(state);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get states by country Id from database
     *
     * @param id State Id
     * @return ResponseEntity<Map < String, Object>> - Returns states by country Id from database
     * @throws EntityNotFound     If No Data Found
     */
    @ApiOperation(value = "Returns Country by Id from database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/states/countries/{id}")
    public ResponseEntity<Map<String, Object>> getCountriesById(@PathVariable(value = "id") long id) {
        try {
            List<States> states = statesService.getSatesByCountryId(id);
            return Commons.getMapResponseEntity(states);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
