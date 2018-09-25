package com.rrdev;

import static com.rrdev.Singleton.getInstance;

public class Main {

    public static void main(String[] args) {
        getInstance().wagon = new RollerCoasterWagon(2, 5);
        getInstance().passengersWaiting.add(new Passenger(0, 4, 5));
        getInstance().passengersWaiting.add(new Passenger(1, 20, 30));
    }
}
