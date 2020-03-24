package com.example.demo.controller;

import com.example.demo.dto.GameResponseDto;
import com.example.demo.model.FreeGame;
import com.example.demo.model.GrossingGame;
import com.example.demo.model.PaidGame;
import com.example.demo.service.FreeGameService;
import com.example.demo.service.GrossingGameService;
import com.example.demo.service.PaidGameService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ios/games/charts")
public class GameController {

    private final PaidGameService paidGameService;
    private final FreeGameService freeGameService;
    private final GrossingGameService grossingGameService;

    public GameController(PaidGameService paidGameService, FreeGameService freeGameService,
                          GrossingGameService grossingGameService) {
        this.paidGameService = paidGameService;
        this.freeGameService = freeGameService;
        this.grossingGameService = grossingGameService;
    }

    @GetMapping("/paid")
    public List<GameResponseDto> findAllPaidGames(@RequestParam(value = "limit",
            required = false, defaultValue = "100") Integer limit) {
        Iterable<PaidGame> paidGameList
                = paidGameService.getSortedListOfPaidGamesSortedByName(limit);
        return listOfPaidGamesTransformToListOfDtoGames(paidGameList);
    }

    @GetMapping("/free")
    public List<GameResponseDto> findAllFreeGames(@RequestParam(value = "limit",
            required = false, defaultValue = "100") Integer limit) {
        Iterable<FreeGame> freeGameList
                = freeGameService.getSortedListOfFreeGamesSortedByName(limit);
        return listOfFreeGamesTransformToListOfDtoGames(freeGameList);
    }

    @GetMapping("/grossing")
    public List<GameResponseDto> findAllGrossingGames(@RequestParam(value = "limit",
            required = false, defaultValue = "100") Integer limit) {
        Iterable<GrossingGame> grossingGamesList
                = grossingGameService.getSortedListOfGrossingGamesSortedByName(limit);
        return listOfGrossingGamesTransformToListOfDtoGames(grossingGamesList);
    }

    private List<GameResponseDto> listOfPaidGamesTransformToListOfDtoGames(Iterable<PaidGame>
                                                                                   iterable) {
        List<GameResponseDto> gamesResponseDtoList = new ArrayList<>();
        Iterator<PaidGame> gameIterator = iterable.iterator();
        while (gameIterator.hasNext()) {
            GameResponseDto gameResponseDto = new GameResponseDto();
            PaidGame paidGame = gameIterator.next();
            gameResponseDto.setName(paidGame.getName());
            gameResponseDto.setUrl(paidGame.getUrl());
            gameResponseDto.setReleaseDate(paidGame.getReleaseDate());
            gameResponseDto.setArtistName(paidGame.getArtistName());
            gamesResponseDtoList.add(gameResponseDto);
        }
        return gamesResponseDtoList;
    }

    private List<GameResponseDto> listOfFreeGamesTransformToListOfDtoGames(Iterable<FreeGame>
                                                                                   iterable) {
        List<GameResponseDto> gamesResponseDtoList = new ArrayList<>();
        Iterator<FreeGame> gameIterator = iterable.iterator();
        while (gameIterator.hasNext()) {
            GameResponseDto gameResponseDto = new GameResponseDto();
            FreeGame freeGame = gameIterator.next();
            gameResponseDto.setName(freeGame.getName());
            gameResponseDto.setUrl(freeGame.getUrl());
            gameResponseDto.setReleaseDate(freeGame.getReleaseDate());
            gameResponseDto.setArtistName(freeGame.getArtistName());
            gamesResponseDtoList.add(gameResponseDto);
        }
        return gamesResponseDtoList;
    }

    private List<GameResponseDto>
    listOfGrossingGamesTransformToListOfDtoGames(Iterable<GrossingGame> iterable) {
        List<GameResponseDto> gamesResponseDtoList = new ArrayList<>();
        Iterator<GrossingGame> gameIterator = iterable.iterator();
        while (gameIterator.hasNext()) {
            GameResponseDto gameResponseDto = new GameResponseDto();
            GrossingGame grossingGame = gameIterator.next();
            gameResponseDto.setName(grossingGame.getName());
            gameResponseDto.setUrl(grossingGame.getUrl());
            gameResponseDto.setReleaseDate(grossingGame.getReleaseDate());
            gameResponseDto.setArtistName(grossingGame.getArtistName());
            gamesResponseDtoList.add(gameResponseDto);
        }
        return gamesResponseDtoList;
    }
}
