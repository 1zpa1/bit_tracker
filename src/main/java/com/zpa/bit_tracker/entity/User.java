package com.zpa.bit_tracker.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "tg")
    private String tg;
    @Column(name = "video_card")
    private String videoCard;
    @Column(name = "numbers_of_video_card")
    private int numbersOfVideoCard;
    @Column(name = "hash_rate")
    private double hashRate;
    @Column(name = "electricity_cost")
    private double electricityCost;

    /*public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }*/
}
