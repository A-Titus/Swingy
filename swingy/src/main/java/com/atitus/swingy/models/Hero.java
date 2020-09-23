package com.atitus.swingy.models;


import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hero{

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "hero class cannot be empty")
    private String heroClass;

    @Positive(message = "level should only be positive numbers")
    @Min(value = 1, message = "level should not be less than 0")
    @Max(value = 10, message = "level should not be greater than 10")
    private int level;

    @Positive(message = "exp cannot be negitive")
    private int exp;

    @Positive(message = "attack cannot be negitive")
    private int attack;

    @Positive(message = "defence cannot be negitive")
    private int defence;

    @Positive(message = "hp cannot be negitive")
    private int hp;

    private int x;
    private int y;
}