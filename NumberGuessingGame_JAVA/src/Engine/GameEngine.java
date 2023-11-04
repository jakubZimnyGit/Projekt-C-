package Engine;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import FilesManagement.File_Management;
import Models.*;
public class GameEngine {
    protected Player player;
    protected Player[] players;
    protected String path;
    protected int [] range = new int[2];
    private String leaderTitle = "[LEADER]";


    void difficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the difficulty: \n1. Easy\n2. Medium\n3. Hard\n4. Custom");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                this.range[0] = 1;
                this.range[1] = 100;
                break;
            case "2":
                this.range[0] = 1;
                this.range[1] = 10000;
                break;
            case "3":
                this.range[0] = 1;
                this.range[1] = 1000000;
                break;
            case "4":
                System.out.println("pick range: \nMinimum: ");
                int min = Integer.parseInt(scanner.nextLine());
                System.out.println("pick range: \nMaximum: ");
                int max = Integer.parseInt(scanner.nextLine());
                this.range[0] = min;
                this.range[1] = max;

        }
    }
    Player LogIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your nickname: ");
        String nick = scanner.nextLine();
        this.player = new Player();
        player.nickName = nick;
        player.PlayerStats.put("name", nick);
        player.loadSingePlayer(path);
        return player;
    }

    void playerWelcome(){
        Scanner scanner = new Scanner(System.in);
        player.Greetings();
        System.out.println("Your best score is " + this.player.PersonalBest);
    }
    public static void optionChoice(String choice) throws IOException {
        switch (choice) {
            case "1":
                Singleplayer_Engine single = new Singleplayer_Engine();
                single.Game();
                break;
            case "2":
                Multiplayer_engine Multi = new Multiplayer_engine();
                Multi.Game();
                break;
            case "3":
                vsCPU vsProgram= new vsCPU();
                vsProgram.Game();
                break;
            case "4":
                Tournament tournament = new Tournament();
                tournament.Game();
                break;
            case "5":
                System.exit(0);
                break;
        }
    }
    public void SetupGame(){
        playerWelcome();
        difficulty();
        player.RandomNumber(this.range);
    }
    void updatePlayerStats(Player player){
        player.PlayerStats.put("name", player.nickName);
        player.PlayerStats.put("Wins", player.Wins.toString());
        player.PlayerStats.put("Lost", player.Lost.toString());
        player.PlayerStats.put("GamesPlayed", player.GamesPlayed.toString());
        player.PlayerStats.put("winStreak", player.winStreak.toString());
    }

    Boolean GameOn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Continue?: \n1. Yes\n2. No");
        String choice = scanner.nextLine();
        return switch (choice) {
            case "1" -> true;
            default -> false;
        };
    }
    public void updatePlayers(ArrayList<Player> playersAfterGame) throws IOException {
        Player winner = playersAfterGame.get(0);
        player.winStreak++;
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
    public boolean PlayerGuessNumber(){
        System.out.println("Guess the number (" + range[0] + " <---> " + range[1] + ")");
        return player.checkIfWin(player.isCorrect(player.numberToGuess));
    }
    public void GiveLeaderNickname(Player player){
        if (IsLeader(player)){
            return;
        }
        else if (player.winStreak >= 3){
            player.nickName = leaderTitle + player.nickName;
        } 
    }
    public void RemoveLeaderNickname(Player player){
        if (IsLeader(player)) {
            player.nickName = player.nickName.substring(8,player.nickName.length());
        }
    }
    public boolean IsLeader(Player player){  
        try {
            System.out.println(player.nickName.substring(8,player.nickName.length()));
            String substring = player.nickName.substring(0, 8);
           if (substring.equals(leaderTitle)){
            return true;
        } 
        } catch (Exception StringIndexOutOfBoundsException) {
            return false;
        }
        return false;

    }
}
