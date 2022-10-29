package com.example.SpringDateMappingObj.Service;

import com.example.SpringDateMappingObj.DTO.Game.AddGameDto;
import com.example.SpringDateMappingObj.DTO.Game.EditGameDto;
import com.example.SpringDateMappingObj.entities.Game;

import java.util.List;

public interface GameService {

    Game addGame(AddGameDto addGameDto);

    int editGame(EditGameDto editGameDto);

    int deleteGame(int id);

    List<Game> selectAllGames();

    List<DetailGameFields> selectGameDetailsFromInput(String title);
}
