package com.rrdev;

import com.rrdev.listener.PassengerHandleWagonListener;
import com.rrdev.util.DateUtil;

import java.util.Date;

public class Passenger extends Thread{

    private int id;
    private int timeBoarding;
    private int timeLanding;
    private boolean keepComingBack = true;

    private PassengerHandleWagonListener listener;

    public Passenger(int id, int timeBoarding, int timeLanding){
        this.id = id;
        this.timeBoarding = timeBoarding;
        this.timeLanding = timeLanding;
    }

    public void getInTheWagon(){
        long timeBoardingInMillis;
        long currentTime;
        int count;
        timeBoardingInMillis = timeBoarding*1000;
        currentTime = System.currentTimeMillis();
        count = timeBoarding;

        while(System.currentTimeMillis() < (currentTime+timeBoardingInMillis)){
            long currentTimeSecond = System.currentTimeMillis();
            while(System.currentTimeMillis()<(currentTimeSecond+1000)){}
            count--;
            System.out.println("LAP: "+count+" onTime:"+ DateUtil.formatOnPattern(new Date(), DateUtil.DATE_PATTERN));
        }
        listener.enterTheWagon(id);
    }

    public void leaveWagon(){
        long timeLeaveInMillis;
        long currentTime;
        int count;
        timeLeaveInMillis = timeLanding*1000;
        currentTime = System.currentTimeMillis();
        count = timeBoarding;

        while(System.currentTimeMillis() < (currentTime+timeLeaveInMillis)){
            long currentTimeSecond = System.currentTimeMillis();
            while(System.currentTimeMillis()<(currentTimeSecond+1000)){}
            count--;
            System.out.println("LAP: "+count+" onTime:"+ DateUtil.formatOnPattern(new Date(), DateUtil.DATE_PATTERN));
        }
        listener.leavesTheWagon(id);
    }

    @Override
    public void run(){
        while (keepComingBack){
            getInTheWagon();
            leaveWagon();
        }
    }

    public void setListener(PassengerHandleWagonListener listener) {
        this.listener = listener;
    }
}
