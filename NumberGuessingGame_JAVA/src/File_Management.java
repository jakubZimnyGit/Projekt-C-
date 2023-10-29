import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class File_Management {      //Class that handles all operations on files

    static void SavePlayer(HashMap<String,String> playerStats, Player player) {     //saving player's nickname to file
        BufferedWriter writer;
        File file = new File("./Players/" + player.nickName + ".txt");
        try {

            // create new BufferedWriter for the output file
            writer = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<String, String> entry : playerStats.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static HashMap<String,String> loadPlayerStats(Player player){
        HashMap<String, String> playerStats = new HashMap<String, String>();
        BufferedReader reader;
        File file = new File("./Players/" + player.nickName + ".txt");

        if (file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(file));

                String line = null;

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(":");

                    String first = parts[0].trim();
                    String second = parts[1].trim();

                    if (!first.isEmpty() && !second.isEmpty()) {
                        playerStats.put(first, second);
                    }
                }
                reader.close();
                return playerStats;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
