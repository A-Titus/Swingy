package com.atitus.swingy.models;
//add lombok annotations

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Enemies{

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "hero class cannot be empty")
    private String enemiesClass;
    @Positive(message = "level should only be positive numbers")
    private int level;
    @Positive(message = "attack cannot be negitive")
    private int attack;
    @Positive(message = "defence cannot be negitive")
    private int defence;
    @Positive(message = "hp cannot be negitive")
    private int hp;
    private int x;
    private int y;

}