package com.atitus.swingy.controllers;

import java.util.*;

public class CreateMap{
    private int level;
    private int mapSize;
    private int[][] grid;

    public int getMapSize(int level){
        this.level = level;
        this.mapSize = (level - 1) * 5 + 10 - (level % 2);
        //this.grid = new int[this.mapSize][this.mapSize];
        return(this.mapSize);
    }

    public int getCenter(int level){
        int total = 0;
        int y = 0;
        int i = 0;
        int average = getMapSize(level);

       total = average * (average + 1)/2; //get average
        total = total - average;
        average = total/average;//cater for 0 based index
        return(average);
    }
}