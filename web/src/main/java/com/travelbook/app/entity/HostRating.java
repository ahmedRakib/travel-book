package com.travelbook.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Participants Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "host_rating")
public class HostRating implements Serializable {
    private static final long serialVersionUID = 645645631456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "campaigns_id")
    private long campaignsId;

    @Column(name = "rating")
    @Min(value = 0, message = "Minimum rating has to be 0")
    @Max(value = 5, message = "Maximum rating has to be 5")
    private double rating;

    @Column(name = "host_id")
    private long hostId;

    @Column(name = "user_id")
    private long userId;
}
