package com.rrdev;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Singleton {

    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return uniqueInstance;
    }

    public static Semaphore accessWagon;
    public static Semaphore waitingFull = new Semaphore(0);
    public static Semaphore travelWagon = new Semaphore(0);

    public RollerCoasterWagon wagon;
    public int passengersWaitingFull = 0;
    public List<Passenger> passengersOnWagon = new ArrayList<>();
    public List<Passenger> passengersWaiting = new ArrayList<>();

}
