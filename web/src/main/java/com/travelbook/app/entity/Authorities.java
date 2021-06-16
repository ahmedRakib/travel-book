package com.travelbook.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Authorities Entity
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Entity
@NoArgsConstructor
@Table(name = "authorities")
@Getter
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "username")
    private String username;
}
