package com.zpa.bit_tracker.controller;

import com.zpa.bit_tracker.model.BitcoinStat;
import com.zpa.bit_tracker.service.BitcoinStatService;
import com.zpa.bit_tracker.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/bitcoinStat")
public class BitcoinStatController {
    @Autowired
    private BitcoinStatService bitcoinStatService;

    @Autowired
    private CalculationService calculationService;
    @GetMapping("/showBitcoinStat")
    public BitcoinStat showBitcoinStat() {
        return bitcoinStatService.showBitcoinStat();
    }

    @GetMapping("/showConclusion")
    public String showConclusion(Principal principal) {
        System.out.println(calculationService.calculateProfit(principal));
        return calculationService.conclusion(principal);
    }

}
