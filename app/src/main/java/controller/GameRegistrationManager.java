package controller;

import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;

import model.GameRegistration;

/**
 * Created by axelreinholdz on 2015-11-17.
 */
public class GameRegistrationManager {

    public GameRegistrationManager() {

    }


    public GameRegistration createGameRegistration(int userId, int gameId) {

        Calendar currentTime = Calendar.getInstance();
        Date startDate = new Date((currentTime.getTime()).getTime());
        Time startTime = new Time((currentTime.getTime()).getTime());
        GameRegistration newGameRegistration = new GameRegistration(userId,gameId,startDate,startTime);

        return newGameRegistration;
    }
}
