package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class GetHeroStats{
    private static Validator validator;

    public Hero initHero(String name, String heroClass, int level, int exp, int attack, int defence, int hp, int x, int y){
       Hero hero = new Hero();

       //extra checks

        hero.setName(name);
        hero.setHeroClass(heroClass);
        hero.setLevel(level);
        hero.setExp(exp);
        hero.setAttack(attack);
        hero.setDefence(defence);//do checks and error handling
        hero.setHp(hp);
        hero.setX(x);
        hero.setY(y);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        validate(hero);

        return(hero);
    }

    public static void validate(Hero hero) {


        Set<ConstraintViolation<Hero>> cvs = validator.validate(hero);
        if(cvs.size() > 0){
            for (ConstraintViolation<Hero> cv : cvs) {
                System.out.println(cv.getPropertyPath() + ": " + cv.getMessage());
            }
            System.exit(1);
        }else{
            System.out.println("\nYour Input is Valid!\n"+
                    "Enjoy Your Game!!\n");
        }

    }
}