package com.rrdev;

import com.rrdev.listener.TravelStateListener;
import com.rrdev.util.DateUtil;

import java.util.Date;

public class RollerCoasterWagon extends Thread {

    private int availableSeats;
    private int timeOfTravel;
    private boolean keepTraveling = true;

    private TravelStateListener travelListener;

    public RollerCoasterWagon(int availableSeats, int timeOfTravel){
        this.availableSeats = availableSeats;
        this.timeOfTravel = timeOfTravel;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setKeepTraveling(boolean keepTraveling) {
        this.keepTraveling = keepTraveling;
    }

    public void setTravelListener(TravelStateListener travelListener) {
        this.travelListener = travelListener;
    }

    @Override
    public void run(){

        long timeOfTravelInMillis;
        long currentTime;
        int count;

        while (keepTraveling){
            timeOfTravelInMillis = timeOfTravel*1000;
            currentTime = System.currentTimeMillis();
            count = this.timeOfTravel;

            travelListener.startTravel();
            while(System.currentTimeMillis() < (currentTime+timeOfTravelInMillis)){
                long currentTimeSecond = System.currentTimeMillis();
                while(System.currentTimeMillis()<(currentTimeSecond+1000)){}
                count--;
                System.out.println("LAP: "+count+" onTime:"+DateUtil.formatOnPattern(new Date()));
            }
            travelListener.finishedTravel();
        }


    }



}
