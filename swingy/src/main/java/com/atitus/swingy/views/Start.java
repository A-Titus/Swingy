package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener {
    private JFrame frame;
    private JPanel statsPanel,buttonPanel, mapPanel, outputPanel;
    private JLabel label,nameLabel,classLabel,levelLabel,expLabel,attackLabel,defenceLabel,hpLabel,name,heroClass,level,exp,attack,defence,hp,heroLabel, enemyLabel,outputLabel;
    private JButton north, east, south, west, exit;
    JPanel[][] panelHolder = new JPanel[1][1];
    ArrayList<Enemies> enemies = new ArrayList<Enemies>();
    Hero hero = new Hero();
    int heroX = 0;
    int heroY = 0;
    boolean check;
    CreateMap mapStats;
    CreateEnemies newEnemies = new CreateEnemies();
    CheckContact contact = new CheckContact();

    public Start(Hero hero) {
        this.hero = hero;
        frame = new JFrame();

        label = new JLabel("Start");
        nameLabel = new JLabel("Name");
        classLabel = new JLabel("Class");
        levelLabel = new JLabel("Level");
        expLabel = new JLabel("Exp");
        attackLabel = new JLabel("Attack");
        defenceLabel = new JLabel("Defence");
        hpLabel = new JLabel("Hp");

        name = new JLabel(hero.getName());
        heroClass = new JLabel(hero.getHeroClass());
        level = new JLabel(String.valueOf(hero.getLevel()));
        exp = new JLabel(String.valueOf(hero.getExp()));
        attack = new JLabel(String.valueOf(hero.getAttack()));
        defence = new JLabel(String.valueOf(hero.getDefence()));
        hp = new JLabel(String.valueOf(hero.getHp()));

///////////////statsPanel/////////////////////////////
        statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        statsPanel.setLayout(new GridLayout(0,1));

        statsPanel.add(label);
        statsPanel.add(classLabel);
        statsPanel.add(heroClass);
        statsPanel.add(nameLabel);
        statsPanel.add(name);
        statsPanel.add(levelLabel);
        statsPanel.add(level);
        statsPanel.add(expLabel);
        statsPanel.add(exp);
        statsPanel.add(attackLabel);
        statsPanel.add(attack);
        statsPanel.add(defenceLabel);
        statsPanel.add(defence);
        statsPanel.add(hpLabel);
        statsPanel.add(hp);

/////////buttonPanel///////////////////////
        north = new JButton("north");
        north.addActionListener(this);
        east = new JButton("east");
        east.addActionListener(this);
        south = new JButton("south");
        south.addActionListener(this);
        west = new JButton("west");
        west.addActionListener(this);
        exit = new JButton("exit");
        exit.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        buttonPanel.setLayout(new GridLayout(0,1));

        buttonPanel.add(north);
        buttonPanel.add(east);
        buttonPanel.add(south);
        buttonPanel.add(west);
        buttonPanel.add(exit);

///////////////mapPanel////////////////////
        mapStats = new CreateMap();
        int mapSize = mapStats.getMapSize(hero.getLevel());

        panelHolder = new JPanel[mapSize][mapSize];

        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mapPanel.setLayout(new GridLayout(mapSize,mapSize));

        for (int i = 0; i < mapSize; i++)   {

            for (int j = 0; j < mapSize; j++)  {
                panelHolder[i][j] = new JPanel();
                panelHolder[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPanel.add(panelHolder[i][j]);
            }
        }

        enemies = newEnemies.create(hero.getLevel());

        for(Enemies enemy : enemies)
        {
            enemyLabel = new JLabel(enemy.getName());
            panelHolder[enemy.getX()][enemy.getY()].add(enemyLabel);

        }
        heroLabel = new JLabel(hero.getName());
        panelHolder[hero.getX()][hero.getY()].add(heroLabel);

        //call create enemy function and for each create a panel holder ovbject

/////////////outputPanel////////////////////////////
        outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        outputPanel.setLayout(new GridLayout(0,1));

        outputLabel = new JLabel("gfgddfgfdgdfsgfxxg");
        outputPanel.add(outputLabel);


////////////////frame/////////////////////////

        frame.add(statsPanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Start");
        frame.setPreferredSize(new Dimension(1350, 750));
        frame.pack();
        frame.setVisible(true);

        //System.out.println(hero.getName());

    }

    public void battleDialog(){
        JFrame dialogframe;
        String result = "";
        Enemies enemyToRemove = new Enemies();
        int enemyStats = 0;
        int lvl = 1;

        dialogframe = new JFrame();
        int output = JOptionPane.showConfirmDialog(dialogframe
                , "Do you want to battle?"
                ,"Battle/Run"
                ,JOptionPane.YES_NO_OPTION);

        if(output == JOptionPane.YES_OPTION){
            System.out.println("Yes");
            //simulate battle
            enemyToRemove = contact.getEnemy(hero, enemies);//get enemy that needs to be removed
            System.out.println("test2");
            enemyStats = contact.calcXp(enemyToRemove);
            System.out.println("test3");

           result = contact.battle(hero, enemies);
            System.out.println("test4");
           if (result == "won"){
               System.out.println("level up");
               //level up
               hero.setExp(hero.getExp() + enemyStats);
               lvl = contact.levelUp(hero);
               hero.setLevel(lvl);
               //update stats panel
               name.setText(hero.getName());
               heroClass.setText(hero.getHeroClass());
               level.setText(String.valueOf(hero.getLevel()));
               exp.setText(String.valueOf(hero.getExp()));
               attack.setText(String.valueOf(hero.getAttack()));
               defence.setText(String.valueOf(hero.getDefence()));
               hp.setText(String.valueOf(hero.getHp()));
               statsPanel.updateUI();

                //remove enemy from map
               panelHolder[enemyToRemove.getX()][enemyToRemove.getY()].removeAll();//clear that specific panel
               enemies = contact.removeEnemy(hero, enemies); //delete enemy from arraylist
               panelHolder[hero.getX()][hero.getY()].add(heroLabel);//read hero after being cleared above
               panelHolder[enemyToRemove.getX()][enemyToRemove.getY()].updateUI();//update ui to show remove enemy
           }else if (result == "lost"){
               System.out.println("game over");
               //save data to text file
               System.exit(0);


           }

        } else if(output == JOptionPane.NO_OPTION){
            System.out.println("Chicken shit");
            //print to panel
        }
    }

    public void actionPerformed(ActionEvent e) {
        int maxX = mapStats.getMapSize(hero.getLevel()) -1 ;
        int maxY = mapStats.getMapSize(hero.getLevel()) - 1;
        int heroLvl = hero.getLevel();

        if (e.getSource() == north){
            System.out.println("north");
            hero.setX(hero.getX() - 1);
            mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());

        }else if(e.getSource() == east){
            System.out.println("east");
            hero.setY(hero.getY() + 1);
            mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == south){
            System.out.println("south");
            hero.setX(hero.getX() + 1);
            mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == west){
            System.out.println("west");
            hero.setY(hero.getY() - 1);
            mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY);
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == exit){
//          add saving current stats to text file
            System.out.println("exit");
            System.exit(0);

        }
    }


}
