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

//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == 0)
//                    System.out.print("- ");
//                else
//                    System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//        }

    }


}