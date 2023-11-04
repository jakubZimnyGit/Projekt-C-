package FilesManagement;


import Models.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class File_Management {      //Class that handles all operations on files

    public static void SavePlayer(Player player, String path) throws IOException {
        Files.createDirectories(Paths.get(path));
        BufferedWriter writer;
        File file = new File("./" + path + "/" + player.nickName + ".txt");
        try {

            writer = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, String> entry : player.PlayerStats.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String,String> loadPlayerStats(Player player, String path){
        HashMap<String, String> playerStats = new HashMap<String, String>();
        BufferedReader reader;
        File file = new File("./" + path + "/" + player.nickName + ".txt");

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

