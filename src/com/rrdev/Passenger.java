package com.rrdev;

import com.rrdev.util.DateUtil;

import static com.rrdev.Singleton.*;

public class Passenger extends Thread {

    private int id;
    private int timeBoarding;
    private int timeLanding;
    private boolean keepComingBack = true;
    private boolean onTravel = true;

    public Passenger(int id, int timeBoarding, int timeLanding){
        this.id = id;
        this.timeBoarding = timeBoarding;
        this.timeLanding = timeLanding;
        this.start();
    }

    @Override
    public String toString() {
        return "Passenger " +id;
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

        for (int i = 0; i < getInstance().passengersWaiting.size(); i++) {
            if (this.id == getInstance().passengersWaiting.get(i).id){
                System.out.println(id + " out of waiting line");
                getInstance().passengersWaiting.remove(i);
            }
        }
    }

    private void getInTheWagon() throws InterruptedException {
        int count = timeBoarding;

        while (count-- > 0){
            DateUtil.skippSecond();
            System.out.println(id+" is boarding: "+count);
        }
        getInstance().passengersOnWagon.add(this);
        System.out.println("Passenger "+ id +" embarcou!");

        if (getInstance().passengersOnWagon.size() == getInstance().wagon.getAvailableSeats()){
            waitingFull.release(passengersWaitingFull);
            getInstance().wagon.startTravel();
        }else {
            passengersWaitingFull++;
            waitingFull.acquire();
        }
    }


    private void enjoyTravel(){
        System.out.println(id +" is enjoying the travel");
        do {
            System.currentTimeMillis();
            System.out.println(id +" is travelling");
        } while (this.onTravel);
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
                System.out.println(id + " out of wagon");
                getInstance().passengersOnWagon.remove(i);
            }
        }
        if (getInstance().passengersOnWagon.isEmpty()){
            passengersWaitingFull = 0;
            accessWagon.release(getInstance().wagon.getAvailableSeats());
        }

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
