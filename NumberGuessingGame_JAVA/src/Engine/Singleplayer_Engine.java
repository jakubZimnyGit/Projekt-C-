package Engine;
import FilesManagement.File_Management;
import Models.Player;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Singleplayer_Engine extends GameEngine{
    private final String path = "Singleplayer";

    public Singleplayer_Engine(){
        this.score = 1;
        this.player = LogIn();
        player.loadSingePlayer(path);
    }
    @Override
    void updatePlayerStats(Player player) {
        player.PlayerStats.put("name", player.nickName);
        player.PlayerStats.put("score", player.PersonalBest.toString());
    }

    public void SetupGame(){
        playerWelcome();
        difficulty();
        player.RandomNumber(this.range);
    }
    public void Game(){
        boolean gameOn = true;
        SetupGame();
        while(gameOn) {
            while (!PlayerGuessNumber()) {
                score++;
            }
            isNewBest();
            gameOn = GameOn();
        }
    }
    public void isNewBest(){
        if(score < player.PersonalBest){
            System.out.println("NEW BEST!!! ---> " + score);
            player.PersonalBest = score;
            updatePlayerStats(player);
            File_Management.SavePlayer(player, path);
        }
        else if(player.PersonalBest == 0) {
            System.out.println("NEW BEST!!! ---> " + score);
            player.PersonalBest = score;
            updatePlayerStats(player);
            File_Management.SavePlayer(player, path);

        }
        else{
            System.out.println("Your score is ---> " + score);
        }
    }
}

