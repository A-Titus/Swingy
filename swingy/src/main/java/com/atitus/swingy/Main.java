package com.atitus.swingy;

import com.atitus.swingy.models.*;
import com.atitus.swingy.views.*;
import com.atitus.swingy.controllers.*;

public class Main {
    public static void main(String[] args) {
        Hero barbarian = new Hero();
        Hero archer = new Hero();
        Hero wizzard = new Hero();

        barbarian.setName("barbarian");
       // System.out.println(barbarian.getName());
        new SelectHero();

       // CreateMap map = new CreateMap();
        //int test = map.getCenter(1);
        //System.out.println(test);
    }
}
