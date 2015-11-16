package model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Iris on 14/11/2015.
 */
public class GameRegistration {
    private int userId;
    private int gameId;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;

    public GameRegistration(int userId, int gameId, Date startDate, Time startTime) {
        this.userId = userId;
        this.gameId = gameId;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    
}
