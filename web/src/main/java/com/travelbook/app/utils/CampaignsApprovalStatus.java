package com.travelbook.app.utils;

import lombok.Getter;

/**
 * Campaigns Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */

@Getter
public enum CampaignsApprovalStatus {
    /**
     * PENDING -> Campaigns Pending Approval by Admin
     * APPROVED -> Campaigns Approved by Admin
     * REJECTED -> Campaigns Rejected by Admin
     */
    PENDING(1), APPROVED(2), REJECTED(3);
    public int id;
    CampaignsApprovalStatus(int id) {
        this.id = id;
    }
}
