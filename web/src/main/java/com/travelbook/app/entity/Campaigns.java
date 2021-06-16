package com.travelbook.app.entity;

import com.travelbook.app.utils.CampaignsApprovalStatus;
import com.travelbook.app.utils.CampaignsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Campaigns Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaigns")
public class Campaigns implements Serializable {
    private static final long serialVersionUID = 645645631456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "countries_id")
    private Long countriesId;

    @Column(name = "states_id")
    private Long statesId;

    @Column(name = "cities_id")
    private Long citiesId;

    @Column(name = "place")
    private String place;

    @Future(message = "Start Date has to be future")
    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "duration")
    private long duration;

    @Column(name = "budgets")
    private double budgets;

    @Column(name = "campaigns_status_id")
    private int campaignsStatusId = CampaignsStatus.DID_NOT_START.getId();

    @Column(name = "user_id")
    private long userId;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "description", columnDefinition = "Text")
    private String description;

    @Column (name = "participant_number")
    private int participantNumber;

    @Column (name = "remaining_seats")
    private int remainingSeats;

    @Column(name = "campaigns_approval_status_id")
    private int campaignsApprovalStatusId = CampaignsApprovalStatus.PENDING.getId();

    @Column(name = "ratings")
    private double overallRating = -1;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "campaigns_id", referencedColumnName = "id")
    private List<CampaignsImages> campaignsImagesList;
}
