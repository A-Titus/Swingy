package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleViews {
   // ConsoleViews console = new ConsoleViews();
    GetHeroStats heroStats = new GetHeroStats();
    Hero hero = new Hero();
    CreateMap map = new CreateMap();
    SaveData save = new SaveData();
    CheckContact contact = new CheckContact();
    ArtifactFactory artifactFact = new ArtifactFactory();
    CreateEnemies newEnemies = new CreateEnemies();
    ArrayList<Hero> heros = new ArrayList<Hero>();
    ArrayList<Enemies> enemies = new ArrayList<Enemies>();
    String[] heroNames;
    int[][] newMap;
    int option = 0;
    Scanner scanner;

    public void StartView(){
        String heroClass = "";
        String name = null;
        int level = 0;
        int exp = 0;
        int attack = 0;
        int defence = 0;
        int hp = 0;
        int x;
        int y;
        int lvl = 1;
        int maxX = 0;
        int maxY = 0;
        String result = null;
        Enemies enemyToRemove = new Enemies();
        int enemyStats = 0;


        scanner = new Scanner(System.in);


        System.out.print("\n**********Swingy**********\n" +
                "Do you want to create a new Hero or select a previously used hero?\n" +
                "       1. Create new player\n" +
                "       2. Select previous player\n"+
                "       3. Exit\n");
        checkOption();

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

            checkOption();

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
                         "       Hp:      " + hp + "\n" +
                         "\n");

            x = map.getCenter(level);//get center coordinates
            y = map.getCenter(level);

          hero = heroStats.initHero(name,heroClass,level,exp,attack,defence,hp,x,y);


          newMap = map.createConsoleMap(level);
            enemies = newEnemies.create(hero.getLevel());
            newMap = map.createConsoleMap(level);

            newMap[hero.getX()][hero.getY()] = 1;
            for(Enemies enemy : enemies)
            {
                newMap[enemy.getX()][enemy.getY()] = 7;
            }
            int check = 1;
            for(int i = 0; i < map.getMapSize(level); i++)
            {
                for(int j = 0; j < map.getMapSize(level); j++)
                {
                    System.out.printf("%2d ", newMap[i][j]);
                }
                System.out.println();
            }


            while(check != 0){

                result = contact.battle(hero, enemies);
                newMap[hero.getX()][hero.getY()] = 0;

                moveHero(hero);
                newMap[hero.getX()][hero.getY()] = 1;


                for(int i = 0; i < map.getMapSize(level); i++)
                {
                    for(int j = 0; j < map.getMapSize(level); j++)
                    {
                        System.out.printf("%2d ", newMap[i][j]);
                    }
                    System.out.println();
                }

                int mapSize = map.getMapSize(hero.getLevel());
                maxX = mapSize -1 ;
                maxY = mapSize - 1;

                //check for win
                if(map.checkBorderCoordinates(level, hero.getX(), hero.getY(), maxX, maxY) == true){
                    System.out.println("congrats you've won the game");
                    save.save(hero);
                    System.out.println("Exiting game");
                    System.exit(1);
                    //save data and exit
                }

                //check if enemies are encountered
                if(contact.check(hero, enemies) == true){
                    enemyToRemove = contact.getEnemy(hero, enemies);//get enemy that needs to be removed
                    enemyStats = contact.calcXp(enemyToRemove);
                    System.out.print("\nDo you want to battle?\n"+
                            "1. Yes\n"+
                            "2. No\n");
                    checkOption();
                    if(option == 1){
                        result = contact.battle(hero,enemies);

                        if(result == "won"){
                            System.out.println("congrats you won the battle");

                            enemies = contact.removeEnemy(hero, enemies); //delete enemy from arraylist
                            //level up


                            hero.setExp(hero.getExp() + enemyStats);

                            lvl = contact.levelUp(hero);
                            hero.setLevel(lvl);

                            //get artifact
                            Artifacts chosenArtifact = new Artifacts();
                            chosenArtifact = artifactFact.getArtifact();

                            if(chosenArtifact.getName() == "weapon"){
                                System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                        "1. Keep\n"+
                                        "2. Leave\n");
                                checkOption();
                                if(option == 1){
                                    hero.setAttack(hero.getAttack() + chosenArtifact.getAttack());
                                    System.out.println("You have recieved a weapon artifact your Attack has increased by "+ chosenArtifact.getAttack());
                                }else if(option == 2){
                                    continue;
                                }
                            }else if(chosenArtifact.getName() == "helm"){
                                System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                        "1. Keep\n"+
                                        "2. Leave\n");
                                checkOption();
                                if(option == 1){
                                    hero.setHp(hero.getHp() + chosenArtifact.getHp());
                                    System.out.println("You have recieved a helm artifact your Hp has increased by "+ chosenArtifact.getHp());
                                }else if(option == 2){
                                    continue;
                                }
                            }else if(chosenArtifact.getName() == "armor"){
                                System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                        "1. Keep\n"+
                                        "2. Leave\n");
                                checkOption();
                                if(option == 1){
                                    hero.setDefence(hero.getDefence() + chosenArtifact.getDefence());
                                    System.out.println("You have recieved an armor artifact your Defence has increased by "+ chosenArtifact.getDefence());
                                }else if(option == 2){
                                    continue;
                                }
                            }
                        }else{
                            System.out.println("Sorry you lost the battle");
                            //save.save(hero);
                            System.out.println("Exiting game");
                            System.exit(1);
                            //exit and save data
                        }
                    }else{
                        newMap[hero.getX()][hero.getY()] = 7;
                        continue;
                    }

                }

            }


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
            checkOption();

            if(option > heroNames.length || option <= 0){
                System.out.println("Inavlid option");
                System.out.println("Please Start over");
                StartView();
            }
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
            hero = heroStats.initHero(name,heroClass,level,exp,attack,defence,hp,x,y);
            enemies = newEnemies.create(hero.getLevel());
            newMap = map.createConsoleMap(level);

            newMap[hero.getX()][hero.getY()] = 1;
            for(Enemies enemy : enemies)
            {
                newMap[enemy.getX()][enemy.getY()] = 7;
            }
            int check = 1;
            for(int i = 0; i < map.getMapSize(level); i++)
            {
                for(int j = 0; j < map.getMapSize(level); j++)
                {
                    System.out.printf("%2d ", newMap[i][j]);
                }
                System.out.println();
            }


            while(check != 0){

                result = contact.battle(hero, enemies);
                newMap[hero.getX()][hero.getY()] = 0;

                moveHero(hero);
                newMap[hero.getX()][hero.getY()] = 1;

                for(int i = 0; i < map.getMapSize(level); i++)
                {
                    for(int j = 0; j < map.getMapSize(level); j++)
                    {
                        System.out.printf("%2d ", newMap[i][j]);
                    }
                    System.out.println();
                }

                int mapSize = map.getMapSize(hero.getLevel());
                maxX = mapSize -1 ;
                maxY = mapSize - 1;

                //check for win
                if(map.checkBorderCoordinates(level, hero.getX(), hero.getY(), maxX, maxY) == true){

                    System.out.println("congrats you've won the game");
                    save.save(hero);
                    System.out.println("Exiting game");
                    System.exit(1);
                    //save data and exit
                }

                //check if enemies are encountered
               if(contact.check(hero, enemies) == true){
                   enemyToRemove = contact.getEnemy(hero, enemies);//get enemy that needs to be removed
                   enemyStats = contact.calcXp(enemyToRemove);
                   System.out.println( "enemy to remove :" + enemyToRemove.getName());
                   System.out.print("\nDo you want to battle?\n"+
                           "1. Yes\n"+
                           "2. No\n");
                   checkOption();
                   if(option == 1){
                       result = contact.battle(hero,enemies);

                       if(result == "won"){
                           System.out.println("congrats you won the battle");
                           System.out.println("level up");
                           enemies = contact.removeEnemy(hero, enemies); //delete enemy from arraylist
                           //level up

                           System.out.println("hero xp: " + hero.getExp());
                           System.out.println(enemyStats);
                           hero.setExp(hero.getExp() + enemyStats);
                           System.out.println("hero xp: " + hero.getExp());
                           lvl = contact.levelUp(hero);
                           hero.setLevel(lvl);

                           //get artifact
                           Artifacts chosenArtifact = new Artifacts();
                           chosenArtifact = artifactFact.getArtifact();

                           if(chosenArtifact.getName() == "weapon"){
                               System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                       "1. Keep\n"+
                                       "2. Leave\n");
                               checkOption();
                               if(option == 1){
                                   hero.setAttack(hero.getAttack() + chosenArtifact.getAttack());
                                   System.out.println("You have recieved a weapon artifact your Attack has increased by "+ chosenArtifact.getAttack());
                               }else if(option == 2){
                                   continue;
                               }
                           }else if(chosenArtifact.getName() == "helm"){
                               System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                       "1. Keep\n"+
                                       "2. Leave\n");
                               checkOption();
                               if(option == 1){
                                   hero.setHp(hero.getHp() + chosenArtifact.getHp());
                                   System.out.println("You have recieved a helm artifact your Hp has increased by "+ chosenArtifact.getHp());
                               }else if(option == 2){
                                   continue;
                               }
                           }else if(chosenArtifact.getName() == "armor"){
                           System.out.print("\nYou have picked up a " +chosenArtifact.getName() + "\n"+
                                   "1. Keep\n"+
                                   "2. Leave\n");
                               checkOption();
                           if(option == 1){
                               hero.setDefence(hero.getDefence() + chosenArtifact.getDefence());
                               System.out.println("You have recieved an armor artifact your Defence has increased by "+ chosenArtifact.getDefence());
                           }else if(option == 2){
                               continue;
                           }
                       }
                       }else{
                           System.out.println("Sorry you lost the battle");
                           //save.save(hero);
                           System.out.println("Exiting game");
                           System.exit(1);
                           //exit and save data
                       }
                   }else{
                       newMap[hero.getX()][hero.getY()] = 7;
                       continue;
                   }

               }

               }
                System.out.println("hero x "+hero.getX());
                System.out.println("hero y "+hero.getY());


        }else if (option == 3){
            System.exit(1);
        }else{
            System.out.println("Inavlid option");
            System.out.println("Please Start over");
            StartView();
        }

    }
    public void moveHero(Hero hero){
        scanner = new Scanner(System.in);

        System.out.print("\nEnter a direction\n"+
                "1. North\n"+
                "2. East\n"+
                "3. South\n"+
                "4. West\n"+
                "5. Exit\n");

        try{
            option = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Inavlid option");
            option = 0;
        }

        if(option == 1){
            System.out.println("north");
            hero.setX(hero.getX() - 1);
        }else if(option == 2){
            System.out.println("East");
            hero.setY(hero.getY() + 1);
        }else if(option == 3){
            System.out.println("South");
            hero.setX(hero.getX() + 1);
        }else if(option == 4){
            System.out.println("West");
            hero.setY(hero.getY() - 1);
        }else if(option == 5){
            System.out.println("Exit");
            save.save(hero);
            System.exit(1);
            //save data
        }else{
            System.out.println("Invalid option");
        }
    }

    public void checkOption(){
        try{
            option = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Inavlid option");
            System.out.println("Please Start over");
            StartView();
        }
    }


}
