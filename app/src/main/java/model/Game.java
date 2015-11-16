package model;

/**
 * Created by Iris on 14/11/2015.
 */
public class Game {
    private int gameId;
    private String name;
    private String status;

    public Game(int gameId, String name, String status) {
        this.gameId = gameId;
        this.name = name;
        this.status = status;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



