package Engine;

import FilesManagement.File_Management;
import Models.Bot;
import Models.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class vsCPU extends Multiplayer_engine {
    private final String path = "VsCpuStats";
    private Bot CPU;
    public vsCPU(){
        this.players = new Player[2];
        Bot CPU = new Bot();
        this.CPU = CPU;
        this.player = LogIn();
        players[0] = this.player;
        players[1] = CPU;
    }
    @Override
    Player LogIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your nickname: ");
        String nick = scanner.nextLine();
        this.player = new Player();
        player.nickName = nick;
        player.PlayerStats.put("name", nick);
        player.loadMultiplayer(path);
        return player;
    }
    @Override
    public void SetupGame(){
        difficulty();
        players[0].RandomNumber(this.range);
        players[1].getNumberForBot();
        this.CPU.setRange(range);
    }
    public void Game() throws IOException {
        boolean gameOn = true;
        while(gameOn){
            this.SetupGame();
            ArrayList<Player> playersWhoGuessed = new ArrayList<>();
            while(playersWhoGuessed.size() != players.length) {
                for (Player currentPlayer : players) {
                    this.player = currentPlayer;
                    if (!playersWhoGuessed.contains(currentPlayer)) {
                        if (this.PlayerGuessNumber()) {
                            playersWhoGuessed.add(currentPlayer);
                        }
                    }
                }
            }
            System.out.println(playersWhoGuessed.get(0).nickName + " is a winner!");
            updatePlayers(playersWhoGuessed);
            gameOn = GameOn();
        }
    }
    public void updatePlayers(ArrayList<Player> playersAfterGame) throws IOException {
        Player winner = playersAfterGame.get(0);
        winner.Wins++;
        winner.GamesPlayed++;
        for (int i = 1; i < playersAfterGame.size(); i++){
            Player player = playersAfterGame.get(i);
            player.Lost++;
            player.GamesPlayed++;
        }
        for (Player player : playersAfterGame){
            updatePlayerStats(player);
            File_Management.SavePlayer(player, this.path);
        }
    }

}
