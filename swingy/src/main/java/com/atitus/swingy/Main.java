package com.atitus.swingy;

import com.atitus.swingy.models.*;
import com.atitus.swingy.views.*;
import com.atitus.swingy.controllers.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ConsoleViews console = new ConsoleViews();

        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))) {
            System.out.println("Please run program with either console or gui and an argument");
            System.exit(1);
        }


        if (args[0].equals("console"))
            console.StartView();
        else if (args[0].equals("gui"))
            new SelectHero();
    }
}
