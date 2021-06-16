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
public enum CampaignsStatus {
    /**
     *  DID_NOT_START -> Campaigns did not start
     *  RUNNING -> Campaigns is Running
     *  FINISHED -> Campaigns is Finished
     *  CANCELLED -> Campaigns is Cancelled
     */
    DID_NOT_START(1), RUNNING(2), FINISHED(3), CANCELLED(4);
    public int id;
    CampaignsStatus(int id){
        this.id = id;
    }
}
