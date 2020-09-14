package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;
public class GetHeroStats{

    public Hero initHero(String name, String heroClass, int level, int exp, int attack, int defence, int hp){
       Hero hero = new Hero();
        hero.setName(name);
        hero.setHeroClass(heroClass);
        hero.setLevel(level);
        hero.setExp(exp);
        hero.setAttack(attack);
        hero.setDefence(defence);//do checks and error handling
        hero.setHp(hp);
        hero.setX(0);
        hero.setY(0);
        return(hero);
    }
}