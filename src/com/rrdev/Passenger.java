package com.rrdev;

import com.rrdev.util.DateUtil;

import static com.rrdev.Singleton.*;

public class Passenger extends Thread {

    private int id;
    private int timeBoarding;
    private int timeLanding;
    private boolean keepComingBack = true;
    private boolean onTravel = true;
    boolean isOnTheWagon = false;

    public Passenger(int id, int timeBoarding, int timeLanding){
        this.id = id;
        this.timeBoarding = timeBoarding;
        this.timeLanding = timeLanding;
        this.start();
    }

    @Override
    public boolean equals(Object obj) {
        Passenger p = (Passenger) obj;
        return this.id == p.id && this.timeBoarding == p.timeBoarding && this.timeLanding == p.timeLanding;

    }

    public void finishTravel(){
        this.onTravel = false;
    }

    private void verifyWagon(){
        try {
            accessWagon.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getInstance().passengersOnWagon.size() < getInstance().wagon.getAvailableSeats()){
            getInstance().passengersOnWagon.add(this);
            for (int i = 0; i < getInstance().passengersWaiting.size(); i++) {
                if (getInstance().passengersWaiting.get(i).equals(this)){
                    getInstance().passengersWaiting.remove(i);
                }
            }
        }
        /*
        try {
            accessWagon.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    private void getInTheWagon() throws InterruptedException {
        int count = timeBoarding;

        while (count-- > 0){
            DateUtil.skippSecond();
            System.out.println(id+" is boarding: "+count);
        }
        System.out.println("Passenger "+ id +" embarcou!");

        this.isOnTheWagon = true;
        if (getInstance().passengersOnWagon.size() == getInstance().wagon.getAvailableSeats()){
            waitingFull.release(getInstance().passengersWaitingFull);
            getInstance().wagon.startTravel();
        }else {
            getInstance().passengersWaitingFull++;
            waitingFull.acquire();
        }
    }


    private void enjoyTravel(){
        System.out.println(id +" is enjoying the travel");
        while (onTravel){
            System.currentTimeMillis();
        }
        System.out.println(id +" liked the travel");
    }

    private void leaveWagon(){
        int count = timeLanding;

        System.out.println(id +" is leaving the wagon");
        while(count-- > 0){
            DateUtil.skippSecond();
            System.out.println(id+" is leaving: "+count);
        }
        System.out.println(id +" is on the waiting line");


        onTravel = true;
        getInstance().passengersWaiting.add(this); 
        for (int i = 0; i < getInstance().passengersOnWagon.size(); i++) {
            if (getInstance().passengersOnWagon.get(i).equals(this)){
                getInstance().passengersOnWagon.remove(i);
            }
        }
        accessWagon.release(getInstance().wagon.getAvailableSeats());
    }



    @Override
    public void run(){
        while (keepComingBack){

            try {

                verifyWagon();
                getInTheWagon();
                enjoyTravel();
                leaveWagon();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
