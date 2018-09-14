package com.rrdev;

import com.rrdev.listener.TravelStateListener;
import com.rrdev.util.DateUtil;

import java.util.Date;

public class RollerCoasterWagon extends Thread {

    private int availableSeats;
    private int timeOfTravel;

    public static int TRAVEL_STATE_START = 0;
    public static int TRAVEL_STATE_END = 1;
    private TravelStateListener travelListener;

    public RollerCoasterWagon(int availableSeats, int timeOfTravel){
        this.availableSeats = availableSeats;
        this.timeOfTravel = timeOfTravel;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setTravelListener(TravelStateListener travelListener) {
        this.travelListener = travelListener;
    }

    @Override
    public void run(){
        long timeOfTravelInMillis = timeOfTravel*1000;
        long currentTime = System.currentTimeMillis();
        int count = this.timeOfTravel;

        travelListener.travelState(TRAVEL_STATE_START);
        while(System.currentTimeMillis() < (currentTime+timeOfTravelInMillis)){
            long currentTimeSecond = System.currentTimeMillis();
            while(System.currentTimeMillis()<(currentTimeSecond+1000)){}
            count--;
            System.out.println("LAP: "+count+" onTime:"+DateUtil.formatOnPattern(new Date(), DateUtil.DATE_PATTERN));
        }
        travelListener.travelState(TRAVEL_STATE_END);
    }



}
