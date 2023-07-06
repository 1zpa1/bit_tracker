package com.zpa.bit_tracker.service;

import com.zpa.bit_tracker.dto.UserDTO;
import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CalculationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BitcoinStatService bitcoinStatService;

    public double calculateProfit(Principal principal) {
        String principalName = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(principalName);
        UserDTO userDTO = new UserDTO();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userDTO.setHashRate(user.getHashRate());
            userDTO.setElectricityCost(user.getElectricityCost());
        }
        double hashRate = userDTO.getHashRate();
        double electrycityCost = userDTO.getElectricityCost();
        double profit = (hashRate * bitcoinStatService.getBlockProbability() * bitcoinStatService.getBlockReward()
                * bitcoinStatService.getBitcoinRate() - electrycityCost) * 3600;
        return profit;
    }

    public String conclusion(Principal principal) {
        String principalName = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(principalName);
        UserDTO userDTO = new UserDTO();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userDTO.setElectricityCost(user.getElectricityCost());
        }
        if (calculateProfit(principal) > userDTO.getElectricityCost()) {
            return "Выгодно";
        } else {
            return "Не выгодно";
        }
    }
}
