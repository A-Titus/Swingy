package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;
import java.util.*;

public class CheckContact {

    public boolean check(Hero hero, ArrayList<Enemies> enemies) {
        int heroX = hero.getX();
        int heroY = hero.getY();


        for (Enemies enemy : enemies) {
            if (enemy.getX() == heroX && enemy.getY() == heroY) {
                return true;
            }
        }
        return false;
    }

    public String battle(Hero player, ArrayList<Enemies> enemies) {
        int heroX = player.getX();
        int heroY = player.getY();
        Hero hero = new Hero();

        hero = player;
        System.out.println("in battle method");
        for (Enemies enemy : enemies) {
            if (enemy.getX() == heroX && enemy.getY() == heroY) {

                //
                System.out.println(" enemy attack: "+ enemy.getAttack());
                System.out.println(" enemy defence: "+ enemy.getDefence());
                System.out.println(" enemy hp: "+ enemy.getHp());

                System.out.println(" hero attack: "+ hero.getAttack());
                System.out.println(" hero defence: "+ hero.getDefence());
                System.out.println(" hero hp: "+ hero.getHp());
                //

                while (hero.getHp() > 0 && enemy.getHp() > 0) {
                    System.out.println("in battle method1");
                    if (hero.getAttack() > enemy.getDefence()) {
                        enemy.setHp((int) (enemy.getHp() - (enemy.getHp() * 0.2)));
                        //System.out.println("enemy battle stats: " + enemy.getHp());
                    }else if (enemy.getAttack() > hero.getDefence()) {
                        hero.setHp((int) (hero.getHp() - (hero.getHp() * 0.2)));
                        //System.out.println("hero battle stats: " + hero.getHp());
                    }else if (hero.getDefence() > enemy.getAttack()){
                        enemy.setHp((int) (enemy.getHp() - (enemy.getHp() * 0.2)));
                       // System.out.println("enemy battle stats: " + enemy.getHp());
                    }else if (enemy.getDefence() > hero.getAttack()){
                        hero.setHp((int) (hero.getHp() - (hero.getHp() * 0.2)));
                        //System.out.println("hero battle stats: " + hero.getHp());
                    }
                    if (hero.getHp() <= 0) {
                        System.out.println("You lost the battle");
                        return ("lost");

                    } else if (enemy.getHp() <= 0) {
                        System.out.println("You won the battle");
                        return ("won");
                    }
                }
            }
        }
        return ("error");
    }

    public Enemies getEnemy(Hero hero, ArrayList<Enemies> enemies) {
        int heroX = hero.getX();
        int heroY = hero.getY();
        Enemies e = new Enemies();

        for (Enemies enemy : enemies) {
            if (enemy.getX() == heroX && enemy.getY() == heroY) {
                return enemy;
            }
        }
        return e; //empty enemy for returns sake
    }

    public int calcXp(Enemies enemy) {
        int gainedXp = enemy.getHp() + enemy.getAttack() + enemy.getDefence();
        gainedXp /= 3;
        gainedXp /= 2;
        return (gainedXp);
    }

    public ArrayList<Enemies> removeEnemy(Hero hero, ArrayList<Enemies> enemies) {
        int heroX = hero.getX();
        int heroY = hero.getY();

        ArrayList<Enemies> updated = new ArrayList<Enemies>();
        Enemies enemy = new Enemies();


        updated = enemies;
        Iterator<Enemies> itr = updated.iterator();
        while (itr.hasNext()) {
            enemy = itr.next();
            if (enemy.getX() == heroX && enemy.getY() == heroY) {
                itr.remove();
            }
        }
        return(updated);
    }

    public int levelUp(Hero hero) {
        int xp = hero.getExp();
        int lvl1 = 1000;
        int lvl2 = 2450;
        int lvl3 = 4800;
        int lvl4 = 8050;
        int lvl5 = 12200;
        int lvl6 = 17250;
        int lvl7 = 23200;
        int lvl8 = 30050;
        int lvl9 = 37800;
        int lvl10 = 46450;

        if(xp >=0 && xp < lvl2){
            hero.setLevel(1);
            return(1);
        }else if(xp > lvl1 && xp < lvl3){
            hero.setLevel(2);
            return(2);
        }
        else if(xp > lvl2 && xp < lvl4){
            hero.setLevel(3);
            return(3);
        }else if(xp > lvl3 && xp < lvl5){
            hero.setLevel(4);
            return(4);
        }else if(xp > lvl4 && xp < lvl6){
            hero.setLevel(5);
            return(5);
        }else if(xp > lvl5 && xp < lvl7){
            hero.setLevel(6);
            return(6);
        }else if(xp > lvl6 && xp < lvl8){
            hero.setLevel(7);
            return(7);
        }else if(xp > lvl7 && xp < lvl9){
            hero.setLevel(8);
            return(8);
        }else if(xp > lvl8 && xp < lvl10){
            hero.setLevel(9);
            return(9);
        }else if(xp > lvl9){
            hero.setLevel(10);
            return(10);
        }

    return (1);

    }

}