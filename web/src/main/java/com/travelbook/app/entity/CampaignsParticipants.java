package com.travelbook.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Campaigns Participants Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaigns_participants")
public class CampaignsParticipants implements Serializable {
    private static final long serialVersionUID = 645645631456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "campaigns_id")
    private long campaignsId;

    @Column(name = "user_id")
    private long userId;
}
