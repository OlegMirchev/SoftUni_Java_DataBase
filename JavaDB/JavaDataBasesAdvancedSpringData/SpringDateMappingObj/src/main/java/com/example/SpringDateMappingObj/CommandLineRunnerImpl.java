package com.example.SpringDateMappingObj;

import com.example.SpringDateMappingObj.DTO.Game.EditGameDto;
import com.example.SpringDateMappingObj.DTO.Game.AddGameDto;
import com.example.SpringDateMappingObj.DTO.User.LoginDto;
import com.example.SpringDateMappingObj.DTO.User.RegisterDto;
import com.example.SpringDateMappingObj.Exception.UserNotLoggedException;
import com.example.SpringDateMappingObj.Exception.ValidationException;
import com.example.SpringDateMappingObj.Service.GameService;
import com.example.SpringDateMappingObj.Service.UserService;
import com.example.SpringDateMappingObj.entities.Game;
import com.example.SpringDateMappingObj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private UserService userService;
    private GameService gameService;

    @Autowired
    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");

        String command = input[0];

        while (!command.equals("Stop")) {

            try {
                String printCommand = switch (command) {
                    case "RegisterUser" -> register(input);
                    case "LoginUser" -> login(input);
                    case "Logout" -> logout();
                    case "AddGame" -> add(input);
                    case "EditGame" -> edit(input);
                    case "DeleteGame" -> delete(input);
                    case "AllGames" -> allGames();
                    case "DetailGame" -> details(input);
                    default -> "Unknown command";
                };

                System.out.println(printCommand);

            } catch (ValidationException | UserNotLoggedException ex) {
                System.out.println(ex.getMessage());
            }

            input = scanner.nextLine().split("\\|");
            command = input[0];
        }
    }

    private String details(String[] input) {
        StringBuilder output = new StringBuilder();

        this.gameService.selectGameDetailsFromInput(input[1])
                .forEach(g -> output.append("Title: ").append(g.getTitle()).append(System.lineSeparator())
                        .append("Price: ").append(g.getPrice()).append(System.lineSeparator())
                        .append("Description: ").append(g.getDescription()).append(System.lineSeparator())
                        .append("Release date: ").append(g.getReleaseDate()));

        return output.toString();
    }

    private String allGames() {
        StringBuilder output = new StringBuilder();

        this.gameService.selectAllGames().forEach(g -> output.append(g.getTitle()).append(" ").append(g.getPrice()).append(System.lineSeparator()));

        return output.toString();
    }

    private String delete(String[] input) {
        User loggedUser = this.userService.getLoggedUser();

        if (loggedUser.isAdministrator()) {
            this.gameService.deleteGame(Integer.parseInt(input[1]));

            return "Deleted Overwatch";
        } else {
            return "This User is not Admin";
        }
    }

    private String edit(String[] input) {
        User loggedUser = this.userService.getLoggedUser();
        String[] splitPrice = input[2].split("=");
        double price = Double.parseDouble(splitPrice[1]);
        String[] splitSize = input[3].split("=");
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(price);
        float size = (float) Double.parseDouble(splitSize[1]);
        EditGameDto editGameDto = new EditGameDto(Integer.parseInt(input[1]), bigDecimalPrice, size);

        if (loggedUser.isAdministrator()) {
            this.gameService.editGame(editGameDto);

            return "Edited Overwatch";
        } else {
            return "This User is not Admin";
        }
    }

    private String add(String[] input) {
        User loggedUser = this.userService.getLoggedUser();
        double price = Double.parseDouble(input[2]);
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(price);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(input[7], formatter);

        AddGameDto addGameDto = new AddGameDto(input[1], bigDecimalPrice, (float) Double.parseDouble(input[3]), input[4], input[5], input[6], localDate);

        if (loggedUser.isAdministrator()) {
            Game game = this.gameService.addGame(addGameDto);
            loggedUser.getGames().add(game);

            return "Added Overwatch";
        } else {
            return "This User is not Admin";
        }
    }

    private String logout() {
        User user = this.userService.getLoggedUser();
        this.userService.logout();

        return String.format("User %s successfully logged out", user.getFullName());
    }

    private String login(String[] input) {
        LoginDto loginDto = new LoginDto(input[1], input[2]);
        Optional<User> user = this.userService.login(loginDto);

        if (user.isPresent()) {
            return String.format("Successfully logged in %s", user.get().getFullName());
        } else {
            return "User is already logged";
        }
    }

    private String register(String[] input) {
        RegisterDto registerDto = new RegisterDto(input[1], input[2], input[3], input[4]);
        User user = this.userService.register(registerDto);

        return String.format("%s was registered", user.getFullName());
    }
}
