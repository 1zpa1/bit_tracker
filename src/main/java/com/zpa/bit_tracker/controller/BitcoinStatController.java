package com.zpa.bit_tracker.controller;

import com.zpa.bit_tracker.model.BitcoinStat;
import com.zpa.bit_tracker.service.BitcoinStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bitcoinStat")
public class BitcoinStatController {
    @Autowired
    private BitcoinStatService bitcoinStatService;
    @GetMapping("/showBitcoinStat")
    public BitcoinStat showBitcoinStat() {
        return bitcoinStatService.showBitcoinStat();
    }
}
