package Engine;

import FilesManagement.File_Management;
import Models.Player;

import java.io.IOException;
import java.util.*;

public class Multiplayer_engine extends GameEngine {

    
    public Multiplayer_engine() {
        super.path = "Multiplayer";
    }

    public void GetPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?: ");
        this.players = new Player[Integer.parseInt(scanner.nextLine())];
        for (int i = 0; i < players.length; i++){
            players[i] = LogIn(i);
            GiveLeaderNickname(players[i]);
        }
    }

    Player LogIn(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter nickname for player number " + (i+1));
        String nick = scanner.nextLine();
        this.player = new Player();
        player.nickName = nick;
        player.PlayerStats.put("name", nick);
        player.loadMultiplayer(path);
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
        int chosen = rdm.nextInt(0,players.length);
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
    
    @Override
    public void updatePlayers(ArrayList<Player> playersAfterGame) throws IOException {
        Player winner = playersAfterGame.get(0);
        winner.winStreak++;
        winner.Wins++;
        winner.GamesPlayed++;
        GiveLeaderNickname(player);
        for (int i = 1; i < playersAfterGame.size(); i++){
            Player player = playersAfterGame.get(i);
            player.Lost++;
            player.winStreak = 0;
            player.GamesPlayed++;
            RemoveLeaderNickname(player);
        }
        for (Player player : playersAfterGame){
            updatePlayerStats(player);
            File_Management.SavePlayer(player, this.path);
        }
    }
    
    public ArrayList<Player> inGame(){
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
        return playersWhoGuessed;
    }

    public void Game() throws IOException {
        boolean gameOn = true;
        while(gameOn){
            SetupGame();
            ArrayList<Player> playersWhoGuessed = inGame();
          gameOn = GameEnd(playersWhoGuessed);
        }
    }
    public boolean GameEnd(ArrayList<Player> playersWhoGuessed) throws IOException {
        System.out.println(playersWhoGuessed.get(0).nickName + " is a winner!");
        updatePlayers(playersWhoGuessed);
        return GameOn();
    }
    


}
