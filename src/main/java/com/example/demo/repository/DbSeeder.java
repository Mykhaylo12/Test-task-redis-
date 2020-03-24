package com.example.demo.repository;

import com.example.demo.model.FreeGame;
import com.example.demo.model.GamesTypes;
import com.example.demo.model.GrossingGame;
import com.example.demo.model.PaidGame;
import com.example.demo.service.FreeGameService;
import com.example.demo.service.GrossingGameService;
import com.example.demo.service.PaidGameService;
import lombok.extern.java.Log;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
public class DbSeeder {
    private static final String TOP_100_PAID_GAMES = "https://rss.itunes.apple.com/api/v1/ua/"
            + "ios-apps/top-paid/games/100/explicit.json";
    private static final String TOP_100_FREE_GAMES = "https://rss.itunes.apple.com/api/v1/ua/"
            + "ios-apps/top-free/games/100/explicit.json";
    private static final String TOP_100_GROSSING_GAMES = "https://rss.itunes.apple.com/api/v1/ua/"
            + "ios-apps/top-grossing/all/100/explicit.json";

    private final PaidGameService paidGameService;
    private final FreeGameService freeGameService;
    private final GrossingGameService grossingGameService;

    public DbSeeder(PaidGameService paidGameService, FreeGameService freeGameService,
                    GrossingGameService grossingGameService) {
        this.paidGameService = paidGameService;
        this.freeGameService = freeGameService;
        this.grossingGameService = grossingGameService;
    }

    @Scheduled(fixedRate = 3600000)
    public void run() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestForPaidGame = HttpRequest.newBuilder()
                .uri(URI.create(TOP_100_PAID_GAMES)).build();
        String responseBodyForPaidGame = client.sendAsync(requestForPaidGame,
                HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
        parseFromJsonToGame(GamesTypes.PAID, responseBodyForPaidGame);

        HttpRequest requestForFreeGame = HttpRequest.newBuilder()
                .uri(URI.create(TOP_100_FREE_GAMES)).build();
        String responseBodyForFreeGame = client.sendAsync(requestForFreeGame,
                HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).join();
        parseFromJsonToGame(GamesTypes.FREE, responseBodyForFreeGame);

        HttpRequest requestForGrossingGame = HttpRequest.newBuilder()
                .uri(URI.create(TOP_100_GROSSING_GAMES)).build();
        String responseBodyForGrossingGame = client.sendAsync(requestForGrossingGame,
                HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).join();
        parseFromJsonToGame(GamesTypes.GROSSING, responseBodyForGrossingGame);

        log.info("Information in DB is updated");
    }

    private void parseFromJsonToGame(GamesTypes type, String responseBody) {
        JSONObject obj = new JSONObject(responseBody);
        JSONObject res = obj.getJSONObject("feed");
        JSONArray resultsArr = res.getJSONArray("results");
        for (int i = 0; i < resultsArr.length(); i++) {
            JSONObject album = resultsArr.getJSONObject(i);
            String id = album.getString("id");
            String name = album.getString("name");
            String url = album.getString("url");
            String date = album.getString("releaseDate");
            String artistName = album.getString("artistName");
            switch (type) {
                case PAID:
                    PaidGame paidGame = new PaidGame();
                    paidGame.setName(name);
                    paidGame.setId(id);
                    paidGame.setUrl(url);
                    paidGame.setReleaseDate(date);
                    paidGame.setArtistName(artistName);
                    paidGameService.update(paidGame);
                    break;
                case FREE:
                    FreeGame freeGame = new FreeGame();
                    freeGame.setName(name);
                    freeGame.setId(id);
                    freeGame.setUrl(url);
                    freeGame.setReleaseDate(date);
                    freeGame.setArtistName(artistName);
                    freeGameService.update(freeGame);
                    break;
                case GROSSING:
                    GrossingGame grossingGame = new GrossingGame();
                    grossingGame.setName(name);
                    grossingGame.setId(id);
                    grossingGame.setUrl(url);
                    grossingGame.setReleaseDate(date);
                    grossingGame.setArtistName(artistName);
                    grossingGameService.update(grossingGame);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong type:" + type);
            }
        }
    }
}
