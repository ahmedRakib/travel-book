package com.travelbook.app.controller;

import com.travelbook.app.entity.CampaignsImages;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.exception.UnAuthorizedAccess;
import com.travelbook.app.service.CampaignsImagesService;
import com.travelbook.app.service.CampaignsService;
import com.travelbook.app.utils.Commons;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Campaigns Images Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CampaignsImagesController {
    private final CampaignsService campaignsService;
    private final CampaignsImagesService campaignsImagesService;

    /**
     * Delete the campaigns image by id from database
     *
     * @param id Must Required Campaign Image Id
     * @return ResponseEntity<Map<String, Object>> - HttpStatus.OK is when campaigns image is deleted
     * @throws EntityNotFound     If No Data Found
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Delete Campaigns Image by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 401, message = "Unauthorized to operate is operation"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @DeleteMapping("/campaigns-image/{id}")
    public ResponseEntity<Map<String, Object>> deleteCampaignsImageById(@PathVariable(value = "id") long id) {
        try {
            CampaignsImages campaignsImages = campaignsImagesService.getById(id);
            campaignsService.getCampaignsById(campaignsImages.getCampaignsId());
            campaignsImagesService.deleteCampaignsImageById(id);
            return Commons.getMapResponseEntity("Resource Deleted");
        } catch (EntityNotFound | UnAuthorizedAccess exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
