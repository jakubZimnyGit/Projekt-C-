package Engine;
import FilesManagement.File_Management;
import Models.Player;

import java.io.IOException;



public class Singleplayer_Engine extends GameEngine{
    private final String path = "Singleplayer";

    public Singleplayer_Engine(){
        this.player = LogIn();
        player.loadSingePlayer(path);
    }
    @Override
    void updatePlayerStats(Player player) {
        player.PlayerStats.put("name", player.nickName);
        player.PlayerStats.put("score", player.PersonalBest.toString());
    }


    public void Game() throws IOException {
        boolean gameOn = true;
        SetupGame();
        while(gameOn) {
            while (!PlayerGuessNumber()) {
            }
            isNewBest();
            gameOn = GameOn();
            player.score = 1;
        }
    }
    public void isNewBest() throws IOException {
        if(player.score < player.PersonalBest){
            System.out.println("NEW BEST!!! ---> " + player.score);
            player.PersonalBest = player.score;
            updatePlayerStats(player);
            File_Management.SavePlayer(player, path);
        }
        else if(player.PersonalBest == 0) {
            System.out.println("NEW BEST!!! ---> " + player.score);
            player.PersonalBest = player.score;
            updatePlayerStats(player);
            File_Management.SavePlayer(player, path);

        }
        else{
            System.out.println("Your score is ---> " + player.score);
        }
    }
}

