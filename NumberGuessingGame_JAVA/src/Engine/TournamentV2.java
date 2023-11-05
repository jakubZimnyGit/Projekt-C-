package Engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import FilesManagement.File_Management;
import Models.Player;

public class TournamentV2 extends Tournament {

    private ArrayList<Player> Players;
    private Player [] players;
    private Player [][] players2;
    private int numberOfTeams;
    private int numberOfPlayers;
    private Player WinnerOfTournament;
    private HashMap<String, Integer> playersScores;

    public TournamentV2(){
        super.path = "BigTournament";
    }

    @Override
    public void GetPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?: ");
        numberOfPlayers = scanner.nextInt();
        Players = new ArrayList<>();
        numberOfTeams = numberOfPlayers/2;
        this.players2 = new Player[numberOfTeams][2];
        int counter = 0;
        for (int i = 0; i < numberOfTeams; i++){
            players = new Player[2];
            for (int j = 0; j < 2; j++){
                players[j] = LogIn(counter);
                counter++;
                GiveLeaderNickname(players[j]);
            }
            players2[i] = players;
            
        }
        fillPlayersArray(players2);
        for (Player x : Players){
            System.out.println(x.nickName);
        }
        displayArray(players2);
    }
    @Override
    public void SetupGame(){
        this.GetPlayers();
        this.difficulty(); 
    }
    public void game() throws IOException{
        SetupGame();
        do {
        Player [] winners = new Player[numberOfPlayers/2];
        for (int i = 0; i < numberOfTeams; i++ ){
            ArrayList<Player> playersWhoGuessed = new ArrayList<>();
            this.players = players2[i];
            super.players = players;
            for (Player player : players){
                player.roundsWon = 0;
            }
            super.playersScores = SetPlayersTable(players);
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
            winners[i] = playersWhoGuessed.get(0);
        }
        if (numberOfTeams == 1){
            this.WinnerOfTournament = winners[0];
            break;
        }
        numberOfTeams = numberOfTeams/2;
        Player [][] players3 = new Player[numberOfTeams][2];
        int j = 0;
        for (int g = 0; g < numberOfTeams; g++){
            players = new Player[2];
            for (int i = 0; i < 2; i++){
                players[i] = winners[j];
                j++;
            }
            players3[g] = players;
        }
        displayArray(players3);
        players2 = players3;
        }
        while (numberOfTeams >= 1);
        System.out.println(WinnerOfTournament.nickName + " Wins the tournament!!!");
        updatePlayers(Players);
    }


    private void displayArray(Player [][] array){
        for (Player[] x : array){
            for (Player y : x)
            {
            System.out.print(y.nickName + " ");
            }
            System.out.println();
        }
    }
    private void fillPlayersArray(Player [][] array){
        int i = 0;
        for (Player[] x : array){
            for (Player y : x){
            Players.add(y);
            i++;
            }
        }
    }
    public void updatePlayers(ArrayList<Player> playersAfterGame) throws IOException {
        WinnerOfTournament.winStreak++;
        WinnerOfTournament.Wins++;
        WinnerOfTournament.GamesPlayed++;
        RemoveLeaderNickname(WinnerOfTournament);
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
}
