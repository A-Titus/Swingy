package com.atitus.swingy.models;
//add lombok annotations
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Enemies{

    private String name;
    private String enemiesClass;
    private int level;
    private int attack;
    private int defence;
    private int hp;
    private int x;
    private int y;

}