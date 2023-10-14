
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;


public class Main {

    static void giereczka(){
        Player player = new Player("");
        Engine gameEngine = new Engine();
        gameEngine.mainMenu();
    }

    public static void main(String[] args) throws FileNotFoundException {
        giereczka();
    }
}
