package com.rrdev;

import com.rrdev.util.DateUtil;

import java.util.Date;
import java.util.concurrent.Semaphore;

import static com.rrdev.Singleton.getInstance;
import static com.rrdev.Singleton.accessWagon;
import static com.rrdev.Singleton.travelWagon;
import static com.rrdev.util.DateUtil.formatOnPattern;

public class RollerCoasterWagon extends Thread {

    private int availableSeats;
    private int timeOfTravel;
    private boolean keepTraveling = true;

    RollerCoasterWagon(int availableSeats, int timeOfTravel){
        this.availableSeats = availableSeats;
        this.timeOfTravel = timeOfTravel;
        accessWagon = new Semaphore(availableSeats);
        this.start();
    }

    int getAvailableSeats() {
        return availableSeats;
    }

    public void destroyWagon(boolean keepTraveling) {
        this.keepTraveling = keepTraveling;
    }

    @Override
    public void run(){
        int count;
        System.out.println("before loop travel");
        while (keepTraveling){
            count = this.timeOfTravel;
            try {
                travelWagon.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wagon start travel "+ formatOnPattern(new Date()));
            while(count-- > 0){
                DateUtil.skippSecond();
                System.out.println("Wagon time: "+count);
            }

            for (Passenger p : getInstance().passengersOnWagon) {
                p.finishTravel();
            }
            System.out.println("Wagon finished travel " + formatOnPattern(new Date()));
        }


    }


    void startTravel() {
        travelWagon.release();
    }
}
