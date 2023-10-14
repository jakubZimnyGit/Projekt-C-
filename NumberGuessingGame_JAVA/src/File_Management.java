import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class File_Management {      //Class that handles all operations on files


    static void SaveData(Player player) {       // Saves player's nickname and his score to files
        SavePlayer(player);
        SaveScore(player);
    }
    static Player GetData(Player player) {      // function looks in files for the nickname given by the player, if there is one
        Player newPlayer = new Player(player.nickName); // function returns player object with score that was saved before for this player
        String[] Players = GetPlayers();        //array of player's nicknames
        String[] Scores = GetScores();          //array of player's scores
        if (Players.length > 0) {
            for (int i = 0; i < Players.length; i++) {
                if (Players[i].equals(player.nickName)) {   // if player's nickname was found in files function creates new player object and sets score to
                    newPlayer.PersonalBest = Integer.parseInt(Scores[i]); //the value that was saved for this player
                    return newPlayer;
                }
            }
        }
        return newPlayer;       // if it's not found function returns player object with score 0
    }

    static void SaveScore(Player player){       // saving player's score to file
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("Scores.txt",true));
            writer.append(player.PersonalBest.toString()).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void SavePlayer(Player player) {     //saving player's nickname to file
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("Players.txt",true));
            writer.append(player.nickName).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String[] GetScores() {   // reads players scores from the file puts it in list of strings
        BufferedReader reader;
        List<String> dane = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("Scores.txt"));
            String line = reader.readLine();
            while (line != null){
                dane.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            String [] strings = new String[dane.size()];
            dane.toArray(strings);
            return strings;
        }
        String [] strings = new String[dane.size()];
        dane.toArray(strings);      //List is turned into an array
        return strings;             //function returns array of string (scores of players)
    }

    static String[] GetPlayers() {      //same as above but with nicknames of players
        BufferedReader reader;
        List<String> dane = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("Players.txt"));
            String line = reader.readLine();
            while (line != null){
                dane.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            String [] strings = new String[dane.size()];
            dane.toArray(strings);
            return strings;
        }
        String [] strings = new String[dane.size()];
        dane.toArray(strings);
        return strings;
    }

    static void ChangeScore(Player player, Integer score){      // function is supposed to create new file with changed context (score value for one player)
        String [] scores = GetScores();             // to do that, it takes arrays of players and scores
        String [] players = GetPlayers();

        for (int i = 0; i < scores.length; i++){            // then iterates through players nicknames array and checks if nickname on "i" index is the same
            if (Objects.equals(players[i],player.nickName)){    // as player's nickname
                scores[i] = score.toString();       // if it is, function changes value of scores[i] to player's new best score
            }                                       // so that we can save it to file later
                                                    // the point is to find the right index of score that we have to change even if there are the same 2 scores
        }                                           // we know for sure that there won't be the same 2 players nicknames

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("Scores.txt",false));     //Now we just need to overwrite the file with new data
            for (int i = 0; i < scores.length; i++) {
                writer.append(scores[i]).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

