package com.zpa.bit_tracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpa.bit_tracker.model.BitcoinStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
public class BitcoinStatService {

    private static final double INITIAL_BLOCK_REWARD = 50.0;
    private static final int HALVING_INTERVAL = 210000;
    private static final String COINAPI_API_URL = "https://rest.coinapi.io/v1/exchangerate";
    private static final String API_KEY = "EAA753CB-0485-40CC-AA71-B3353E4B39C2"; // Замените на ваш API-ключ CoinAPI

    @Autowired
    private RestTemplate restTemplate;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    private ObjectMapper objectMapper;

    public double getBitcoinRate() {
        String url = COINAPI_API_URL + "/BTC/USD";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CoinAPI-Key", API_KEY);
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(url)).headers(headers).build();
        BitcoinRateResponse response = restTemplate.exchange(requestEntity, BitcoinRateResponse.class).getBody();
        if (response != null) {
            return response.getRate();
        }
        return 0.0;
    }

    private static class BitcoinRateResponse {
        private double rate;

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }

    public String getLatestBlockHash() {
        String latestBlockUrl = "https://api.blockcypher.com/v1/btc/main";
        String response = restTemplate.getForObject(latestBlockUrl, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("hash").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("checkstyle:HiddenField")
    public double getNetworkDifficulty() {
        double result = 0;
        try {
            URL url = new URL("https://api.blockchain.info/stats");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String json = response.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);

                JsonNode difficultyNode = jsonNode.get("difficulty");
                if (difficultyNode != null && !difficultyNode.isNull()) {
                    double networkDifficulty = difficultyNode.asDouble();
                    result = networkDifficulty;
                } else {
                    System.out.println("Ответ от API не содержит поле \"difficulty\"");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getBlockReward() {
        String latestBlockUrl = "https://api.blockcypher.com/v1/btc/main";
        String response = restTemplate.getForObject(latestBlockUrl, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            int blockHeight = jsonNode.get("height").asInt();
            double blockReward = calculateBlockReward(blockHeight);
            return blockReward;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
    private double calculateBlockReward(int blockHeight) {
        return INITIAL_BLOCK_REWARD / Math.pow(2, (blockHeight / HALVING_INTERVAL));
    }


    public double getBlockProbability() {
        double averageBlockTime = 10; // в минутах
        double networkDifficulty = getNetworkDifficulty();

        return averageBlockTime / networkDifficulty;
    }

    public BitcoinStat showBitcoinStat() {
        BitcoinStat bitcoinStat = new BitcoinStat();
        bitcoinStat.setRate(getBitcoinRate());
        bitcoinStat.setBlockReward(getBlockReward());
        bitcoinStat.setBlockProbability(getBlockProbability());
        bitcoinStat.setNetworkDifficulty(getNetworkDifficulty());
        return bitcoinStat;
    }

}
