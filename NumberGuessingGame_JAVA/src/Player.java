import java.util.HashMap;
public class Player {       //Player class

    public String nickName;     // Player's properties
    public Integer PersonalBest;
    public Integer Wins;
    public Integer Lost;
    public Integer GamesPlayed;
    public Double WinRate;
    public HashMap<String, String> PlayerStats;

    public Player(String nickName){     // function that allows us to create object of type Player
        this.nickName = nickName;
        this.PersonalBest = 0;
        this.Wins = 0;
        this.Lost = 0;
        this.GamesPlayed = 0;
        this.WinRate = 0.0;
        PlayerStats  = new HashMap<>();
        PlayerStats.put("name", this.nickName);
    }
    public void Greetings()
    {
        System.out.println("Hello!\nI am " + nickName + ".");
    }
}


/*
    hashmap = {nick: String
                personalBest: 0
                liczbaWygranych: 0
                liczbaPrzegranych: 0
                    }

 */