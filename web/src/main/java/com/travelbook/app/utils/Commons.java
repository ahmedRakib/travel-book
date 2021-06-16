package com.travelbook.app.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Common Functionality
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
public class Commons {
    public static boolean checkCampaignsApprovalStatusExistsById(int id) {
        for (CampaignsApprovalStatus e : CampaignsApprovalStatus.values()) {
            if (e.id == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCampaignsStatusExistsById(int id) {
        for (CampaignsStatus e : CampaignsStatus.values()) {
            if (e.id == id) {
                return true;
            }
        }
        return false;
    }

    public static<T> ResponseEntity<Map<String, Object>> getMapResponseEntityWithPagination(Page<T> pageCampaigns) {
        Map<String, Object> response = new HashMap<>(4);
        response.put("data", pageCampaigns.getContent());
        response.put("currentPage", pageCampaigns.getNumber());
        response.put("totalItems", pageCampaigns.getTotalElements());
        response.put("totalPages", pageCampaigns.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> getMapResponseEntity(Object data) {
        Map<String, Object> response = new HashMap<>(1);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> getMapResponseEntity(Object data, int items, int pageNumber, int pageSize) {
        Map<String, Object> response = new HashMap<>(4);
        response.put("data", data);
        response.put("currentPage", pageNumber);
        response.put("totalItems", items);

        int totalPages;
        if (items % pageSize != 0) {
            totalPages = (items / pageSize) + 1;
        } else {
            totalPages = (items / pageSize);
        }

        response.put("totalPages", totalPages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
