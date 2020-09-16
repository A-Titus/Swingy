package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;
import java.util.*;
import java.util.Random;

public class CreateEnemies{
    ArrayList<Enemies> enemies = new ArrayList<Enemies>();
    private String classes[]={"witch","dragon","giant"};
    Random r = new Random();


    public ArrayList<Enemies> create(int level) {
        for (int i = 0; i <= 10; i++){
            int index = r.nextInt(classes.length);
            Enemies enemy = new Enemies();
            enemy.setName(classes[index]);
            enemy.setEnemiesClass(classes[index]);
            enemy.setLevel(r.nextInt(level + 3 - (level + 1) + level));
            enemy.setAttack(r.nextInt(level * 1500 - (level * 900) + level * 900));
            enemy.setDefence(r.nextInt(level * 1500 - (level * 900) + level * 900));
            enemy.setHp(r.nextInt(level * 1500 - (level * 900) + level * 900));

            CreateMap map = new CreateMap();
            int mapSize = map.getMapSize(level) - 1;
            int centerX = map.getCenter(level);
            int centerY = map.getCenter(level);

            enemy.setX(r.nextInt(mapSize + 1 - 0) + 0);
            enemy.setY(r.nextInt(mapSize + 1 - 0) + 0);

            if(enemy.getX() == centerX){
                enemy.setX(r.nextInt(mapSize + 1 - 0) + 0);
            }
            if(enemy.getY() == centerY){
                enemy.setY(r.nextInt(mapSize + 1 - 0) + 0);
            }

            enemies.add(enemy);
        }
        return(enemies);
    }
}