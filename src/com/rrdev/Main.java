package com.rrdev;

import static com.rrdev.Singleton.getInstance;

public class Main {

    public static void main(String[] args) {
        getInstance().wagon = new RollerCoasterWagon(1, 5);
        getInstance().passengersWaiting.add(new Passenger(0, 4, 5));
        //Passenger p1 = new Passenger(1, 3, 4);
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
