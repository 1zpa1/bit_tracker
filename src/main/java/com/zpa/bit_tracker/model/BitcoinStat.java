package com.zpa.bit_tracker.model;

import lombok.Data;

@Data
public class BitcoinStat {

    double rate;
    double networkDifficulty;
    double blockReward;
    double blockProbability;

}
