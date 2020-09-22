package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

public class SaveData {
    String name;
    String heroClass;
    int level = 0;
    int exp = 0;
    int attack = 0;
    int defence = 0;
    int hp = 0;

    public void save(Hero hero){
        this.name = hero.getName();
        this.heroClass = hero.getHeroClass();
        this.level = hero.getLevel();
        this.exp = hero.getExp();
        this.attack = hero.getAttack();
        this.defence = hero.getDefence();
        this.hp = hero.getHp();

        try {
            FileWriter fw = new FileWriter("hero.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(heroClass + " " + name + " " + level + " " + exp + " " + attack + " " + defence + " " + hp);
            pw.close();
        } catch (Exception e) {
            System.out.println("Could not write to file.");
        }
    }

    public ArrayList<Hero> readHeroData(){
        ArrayList<Hero> heros = new ArrayList<Hero>();
        String line;
        String name;
        String heroClass;
        int level = 0;
        int exp = 0;
        int attack = 0;
        int defence = 0;
        int hp = 0;

        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader("hero.txt"));
            line = br.readLine();

            if(line == null){
                System.out.println("Fields missing in hero file");
                System.exit(1);
            }

            while ((line != null)) {
                line = line.trim();
                line = line.replaceAll("\\s+", " ");
                String[] values = line.split(" ");
                if (values.length != 7) {
                    System.out.println("Fields missing in hero file");
                    System.exit(1);
                }else{
                    Hero hero = new Hero();
                heroClass = values[0];
                name = values[1];
                level =  Integer.parseInt(values[2]);
                exp =  Integer.parseInt(values[3]);
                attack =  Integer.parseInt(values[4]);
                defence =  Integer.parseInt(values[5]);
                hp =  Integer.parseInt(values[6]);

                hero.setHeroClass(heroClass);
                hero.setName(name);
                hero.setLevel(level);
                hero.setExp(exp);
                hero.setAttack(attack);
                hero.setDefence(defence);
                hero.setHp(hp);

                heros.add(hero);
                }
                line = br.readLine();
            }

        }catch (Exception e) {
            System.out.println("couldnt read from file");
            //System.exit(1);
        }
        return(heros);
    }

}