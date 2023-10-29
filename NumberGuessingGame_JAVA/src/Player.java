public class Player {       //Player class

    public String nickName;     // Player's properties
    public Integer PersonalBest;
    public Player(String nickName){     // function that allows us to create object of type Player
        this.nickName = nickName;
        this.PersonalBest = 0;
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