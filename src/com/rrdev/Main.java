package com.rrdev;

import com.rrdev.listener.TravelStateListener;

import static com.rrdev.RollerCoasterWagon.TRAVEL_STATE_END;
import static com.rrdev.RollerCoasterWagon.TRAVEL_STATE_START;

public class Main {

    public static void main(String[] args) {
        RollerCoasterWagon rollerCoasterWagon = new RollerCoasterWagon(5, 3);
        rollerCoasterWagon.setTravelListener(new TravelStateListener() {
            @Override
            public void travelState(int state) {
                if (state == TRAVEL_STATE_START)
                    System.out.println("Travel has started");
                else if (state == TRAVEL_STATE_END)
                    System.out.println("Travel has finished");
            }
        });
        rollerCoasterWagon.start();
    }


    /*
    * BarberShop hint
    * 1 customers = 0
    2 mutex = Semaphore(1)
    3 customer = Semaphore(0)
    4 barber = Semaphore(0)
        */


    /*
    * BarberShop Solution
    * 1 mutex.wait()
2 if customers == n+1:
3 mutex.signal()
4 balk()
5 customers += 1
6 mutex.signal()
7
8 customer.signal()
9 barber.wait()
10 getHairCut()
11
12 mutex.wait()
13 customers -= 1
14 mutex.signal()*/

    /*
    * Customer Solution
    * customer.wait()
2 barber.signal()
3 cutHair()*/
}
