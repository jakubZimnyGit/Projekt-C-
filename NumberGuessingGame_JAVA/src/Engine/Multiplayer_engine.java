package Engine;

import FilesManagement.File_Management;
import Models.Player;

import java.util.*;
import java.util.HashMap;

public class Multiplayer_engine extends GameEngine {
    private final String path = "Multiplayer";
    public Multiplayer_engine() {
    }

    public void GetPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?: ");
        this.players = new Player[Integer.parseInt(scanner.nextLine())];
        for (int i = 0; i < players.length; i++){
            players[i] = LogIn(i);
        }
    }

    Player LogIn(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter nickname for player number " + (i+1));
        String nick = scanner.nextLine();
        this.player = new Player();
        player.nickName = nick;
        player.PlayerStats.put("name", nick);
        player.loadMultiplayer(this.path);
        return player;
    }
    @Override
    public void SetupGame(){
        this.GetPlayers();
        this.difficulty();
        for (Player player : players)
            player.RandomNumber(this.range);
        whoStarts();
    }
    private void whoStarts(){
        Random rdm = new Random();
        int chosen = rdm.nextInt(0,players.length - 1);
        if (chosen != 0){
            Player temp = players[0];
            players[0] = players[chosen];
            players[chosen] = temp;
        }
        System.out.println(players[0].nickName + " guess first!");
    }
    @Override
    public boolean PlayerGuessNumber(){
        System.out.println(this.player.nickName + " Guess the number (" + range[0] + " <---> " + range[1] + ")");
        return player.checkIfWin(player.isCorrect(player.numberToGuess));
    }

    public void updatePlayers(ArrayList<Player> playersAfterGame){
        Player winner = playersAfterGame.get(0);
        winner.Wins++;
        winner.GamesPlayed++;
        winner.WinRate = ((Double.valueOf(winner.Wins) / Double.valueOf(winner.GamesPlayed)) * 100);
        for (int i = 1; i < playersAfterGame.size(); i++){
            Player player = playersAfterGame.get(i);
            player.Lost++;
            player.GamesPlayed++;
            player.WinRate = ((Double.valueOf(winner.Wins) / Double.valueOf(winner.GamesPlayed)) * 100);
        }
        for (Player player : playersAfterGame){
            updatePlayerStats(player);
            File_Management.SavePlayer(player, this.path);
        }
    }
    public void Game(){
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
            System.out.println(playersWhoGuessed.get(0).nickName + "is a winner!");
            updatePlayers(playersWhoGuessed);
            gameOn = GameOn();
        }
    }

}
