package com.atitus.swingy.models;
//add lombok annotations
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class Artifacts{
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Positive(message = "attack cannot be negitive")
    private int attack;
    @Positive(message = "defence cannot be negitive")
    private int defence;
    @Positive(message = "hp cannot be negitive")
    private int hp;

}