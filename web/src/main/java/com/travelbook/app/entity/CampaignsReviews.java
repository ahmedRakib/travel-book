package com.travelbook.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Campaigns Reviews Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaigns_reviews")
public class CampaignsReviews implements Serializable {
    private static final long serialVersionUID = 645645631456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "campaigns_id")
    private long campaignsId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "star")
    private double star;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
}
