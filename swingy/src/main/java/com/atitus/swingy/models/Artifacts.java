package com.atitus.swingy.models;
//add lombok annotations
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Artifacts{
    private String name;
    private int attack;
    private int defence;
    private int hp;

}