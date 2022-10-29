package com.example.SpringDateMappingObj.Service.Impl;

import com.example.SpringDateMappingObj.DTO.Game.AddGameDto;
import com.example.SpringDateMappingObj.DTO.Game.EditGameDto;
import com.example.SpringDateMappingObj.Repository.GameRepository;
import com.example.SpringDateMappingObj.Service.GameService;
import com.example.SpringDateMappingObj.Service.DetailGameFields;
import com.example.SpringDateMappingObj.entities.Game;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addGame(AddGameDto addGameDto) {
        ModelMapper modelMapper = new ModelMapper();
        Game mapGame = modelMapper.map(addGameDto, Game.class);

       return this.gameRepository.save(mapGame);
    }

    @Override
    public int editGame(EditGameDto editGameDto) {
        return this.gameRepository.updatePriceAndSize(editGameDto.getId() ,editGameDto.getPrice(), editGameDto.getSize());
    }

    @Override
    @Transactional
    public int deleteGame(int id) {
        return this.gameRepository.deleteGameById(id);
    }

    @Override
    public List<Game> selectAllGames() {
        return this.gameRepository.selectTitleAndPrice();
    }

    @Override
    public List<DetailGameFields> selectGameDetailsFromInput(String title) {
        return this.gameRepository.selectDetailsAtGameIsThatTitle(title);
    }
}
