package com.atitus.swingy.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hero{

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "hero class cannot be null")
    private String heroClass;

    @Min(value = 1, message = "level should not be less than 0")
    @Max(value = 10, message = "level should not be greater than 10")
    private int level;

    private int exp;
    private int attack;
    private int defence;
    private int hp;
    private int x;
    private int y;
}