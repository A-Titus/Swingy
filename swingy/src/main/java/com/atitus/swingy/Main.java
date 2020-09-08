package com.atitus.swingy;

import com.atitus.swingy.models.*;
import com.atitus.swingy.views.*;

public class Main {
    public static void main(String[] args) {
        Hero barbarian = new Hero();
        Hero archer = new Hero();
        Hero wizzard = new Hero();

        barbarian.setName("barbarian");
        System.out.println(barbarian.getName());
        new SelectHero();
//        Greeter greeter = new Greeter();
//        System.out.println(greeter.sayHello());
    }
}
