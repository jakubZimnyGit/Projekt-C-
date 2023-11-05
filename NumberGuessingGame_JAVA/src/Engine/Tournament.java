package Engine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Models.Player;

import java.util.Scanner;

import FilesManagement.File_Management;

public class Tournament extends Multiplayer_engine {

    
    private int rounds;
    protected HashMap<String, Integer> playersScores;

    public Tournament(){
        super.path = "Tournament";
        this.leaderTitle = "[Master]";
    }

    public void numberOfRounds(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.BO3\n2.BO5");
        String response = scanner.nextLine();
        switch (response){
            case "1":
                rounds = 3;
                break;
            case "2":
                rounds = 5;
                break;
        }
        
    }

    public HashMap<String, Integer> SetPlayersTable(Player [] players){
        HashMap<String, Integer> playersScores = new HashMap<String, Integer>();
        for (Player player : players){
            playersScores.put(player.nickName, player.roundsWon);
        }
        return playersScores;
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
    public void Game() throws IOException {
        boolean gameOn = true;
        ArrayList<Player> playersWhoGuessed = new ArrayList<>();
        while (gameOn){
            numberOfRounds();
            SetupGame();
            playersScores = SetPlayersTable(players);
            playersWhoGuessed = new ArrayList<>();
            int round = 1;
            boolean someoneWon = false;
            while (!someoneWon){
                newNumbersToGuess(players);
                System.out.println("\n*******  Round " + (round) + "*******");
                playersWhoGuessed = inGame();
                round++;
                someoneWon = WinnerOfTheRound(playersWhoGuessed);
                displayScore();
            }
            displayPodium(playersWhoGuessed);
            gameOn = GameEnd(playersWhoGuessed);
        }   
    }
    public void displayScore(){
        System.out.print("\n##########   ");
        for (Player player : players){
            System.out.print(player.nickName  + ": "+ player.roundsWon + "   ");
        }
        System.out.print("   ##########\n");
    }

    public void newNumbersToGuess(Player [] players){
        for (Player player : players){
            player.RandomNumber(range);
        }
    }
    public boolean WinnerOfTheRound(ArrayList<Player> playersWhoGuessed) {
        Player winner = playersWhoGuessed.get(0);
        System.out.print(winner.nickName + " wins this round! ");
        winner.roundsWon ++;
        playersScores.replace(winner.nickName, winner.roundsWon);
        return checkWin(winner);
    }
    private boolean checkWin(Player player){
        if (rounds == 3){
            if (player.roundsWon >= 2){
                player.winStreak ++;
                return true;
            }
        }
        else {
            if (player.roundsWon >= 3){
                player.winStreak++;
                return true;
            }
        }
        return false;
    }
    public void updatePlayers(ArrayList<Player> playersAfterGame) throws IOException {
        Player winner = playersAfterGame.get(0);
        player.winStreak++;
        winner.Wins++;
        winner.GamesPlayed++;
        RemoveLeaderNickname(winner);
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
    
    public void displayPodium(ArrayList<Player> playersWhoGuessed){
        try {
            for (int i = 0; i < 3; i++){
                System.out.println(i + 1 + ". Place --->  " + playersWhoGuessed.get(i).nickName);
            }
        } catch (Exception indexOutOfBoundsException) {
            return;
        }
    }
}
