package com.zpa.bit_tracker.dto;

import com.zpa.bit_tracker.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String tg;
    private String videoCard;
    private int numberOfVideoCard;
    private double hashRate;
    private double electricityCost;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setTg(entity.getTg());
        model.setVideoCard(entity.getVideoCard());
        model.setNumberOfVideoCard(entity.getNumbersOfVideoCard());
        model.setHashRate(entity.getHashRate());
        model.setElectricityCost(entity.getElectricityCost());
        return model;
    }
}
