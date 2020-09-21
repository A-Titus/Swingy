package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleViews {
   // ConsoleViews console = new ConsoleViews();
    GetHeroStats heroStats = new GetHeroStats();
    Hero hero = new Hero();
    CreateMap map = new CreateMap();
    SaveData save = new SaveData();
    ArrayList<Hero> heros = new ArrayList<Hero>();
    String[] heroNames;

    public void StartView(){
        String heroClass = "";
        String name;
        int level = 0;
        int exp = 0;
        int attack = 0;
        int defence = 0;
        int hp = 0;
        int x;
        int y;

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        String StringOption;

        System.out.print("\n**********Swingy**********\n" +
                "Do you want to create a new Hero or select a previously used hero?\n" +
                "       1. Create new player\n" +
                "       2. Select previous player\n"+
                "       3. Exit\n");

        option = scanner.nextInt();

        if(option == 1){
            System.out.print("\nEnter your hero name\n");

            scanner.nextLine();
            name = scanner.nextLine();
            if(name.equals("")){
                System.out.println("You did not enter a valid name");
                System.out.println("Please Start over");
                StartView();
            }

            System.out.print("\nSelect your hero class\n" +
                    "       1. Barbarian\n" +
                    "       2. Archer\n" +
                    "       3. Wizzard\n");

            option = scanner.nextInt();

            if(option == 1){
                    heroClass = "barbarian";
                    level = 1;
                    exp = 1000;
                    attack = 1000;
                    defence = 1000;
                    hp = 1000;

               // name = StringOption;

            }else if(option == 2){
                    heroClass = "archer";
                    level = 1;
                    exp = 1000;
                    attack = 1150;
                    defence = 1150;
                    hp = 1000;

            }else if(option == 3){
                    heroClass = "wizzard";
                    level = 1;
                    exp = 1000;
                    attack = 1250;
                    defence = 1250;
                    hp = 1000;

            }else{
                System.out.println("Inavlid option");
                System.out.println("Please Start over");
               StartView();
            }


                 System.out.print("\nYour hero stats\n" +
                         "       Class:   " + heroClass + "\n" +
                         "       Name:    " + name + "\n" +
                         "       Level:   " + level + "\n" +
                         "       Exp:     " + exp + "\n" +
                         "       Attack:  " + attack + "\n" +
                         "       Defence: " + defence + "\n" +
                         "       Hp:      " + hp + "\n");

            x = map.getCenter(level);//get center coordinates
            y = map.getCenter(level);

          hero = heroStats.initHero(name,heroClass,level,exp,attack,defence,hp,x,y);

          //create map
          //pass this hero to another function

//          System.out.println(hero.getName());
//            System.out.println(hero.getHeroClass());
//            System.out.println(hero.getLevel());
//            System.out.println(hero.getExp());
//            System.out.println(hero.getAttack());
//            System.out.println(hero.getDefence());
//            System.out.println(hero.getHp());
//            System.out.println(hero.getX());
//            System.out.println(hero.getY());



        }else if(option == 2){
            //read from text file

            heros = save.readHeroData();
            heroNames = new String[heros.size()];
            for(int i = 0; i < heroNames.length; i++) {
                String heroName = i+1 + ". " + heros.get(i).getHeroClass() + ", Name: " + heros.get(i).getName()+ ", Level: " + heros.get(i).getLevel() +", Exp: " + heros.get(i).getExp() + ", Attack: "+ heros.get(i).getAttack() + ", Defence: "+ heros.get(i).getDefence()+ ", Hp: "+ heros.get(i).getHp();
                heroNames[i] = heroName;

                //have option to select all heros
                System.out.println(heroNames[i]);
            }
            //read line if line is index of hero then select that hero
            System.out.print("\nEnter your hero number\n");
            option = scanner.nextInt();


            System.out.println(heroNames.length);
            for (int i = 1; i <= heroNames.length; i++) {
                if(option == i){
                    name = heros.get(i-1).getName();
                    heroClass = heros.get(i-1).getHeroClass();
                    level = heros.get(i-1).getLevel();
                    exp = heros.get(i-1).getExp();
                    attack = heros.get(i-1).getAttack();
                    defence = heros.get(i-1).getDefence();
                    hp = heros.get(i-1).getHp();

                }
            }
            x = map.getCenter(level);
            y = map.getCenter(level);
//            System.out.println(name); //////////////////////////fix init
//            hero = heroStats.initHero(name,heroClass,level,exp,attack,defence,hp,x,y);

        }else if (option == 3){
            System.exit(1);
        }else{
            System.out.println("Inavlid option");
            System.out.println("Please Start over");
            StartView();
        }

    }


}
