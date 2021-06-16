package com.travelbook.app.controller;

import com.travelbook.app.entity.Cities;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.service.CitiesService;
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
 * City Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CitiesController {
    private final CitiesService citiesService;

    /**
     * Get All States of a Country from database
     *
     * @param id City Id
     * @return ResponseEntity<Map < String, Object>> - Returns All States of a Country
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Returns All States of a Country")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/cities/{id}")
    public ResponseEntity<Map<String, Object>> getCitiesById(@PathVariable(value = "id") long id) {
        try {
            Cities cities = citiesService.getStatesById(id);
            return Commons.getMapResponseEntity(cities);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get country by Id from database
     *
     * @param id City Id
     * @return ResponseEntity<Map < String, Object>> - Returns Country by Id from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Returns Country by Id from database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/cities/states/{id}")
    public ResponseEntity<Map<String, Object>> getCountriesById(@PathVariable(value = "id") long id) {
        try {
            List<Cities> cities = citiesService.getSatesByCountryId(id);
            return Commons.getMapResponseEntity(cities);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
