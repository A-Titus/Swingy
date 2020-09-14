package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener {
    private JFrame frame;
    private JPanel statsPanel,buttonPanel, mapPanel, outputPanel;
    private JLabel label,nameLabel,classLabel,levelLabel,expLabel,attackLabel,defenceLabel,hpLabel,name,heroClass,level,exp,attack,defence,hp,testLabel;
    private JButton north, east, south, west, exit;
    //int t = 9;

    public Start(Hero hero) {
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
        CreateMap mapStats = new CreateMap();
        int mapSize = mapStats.getMapSize(hero.getLevel());

        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mapPanel.setLayout(new GridLayout(mapSize,mapSize));

        for (int i = 0; i < mapSize; i++)   {

            for (int j = 0; j < mapSize; j++)  {
                mapPanel.add(new JLabel("a "));
            }
        }

/////////////outputPanel////////////////////////////
        outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        outputPanel.setLayout(new GridLayout(0,1));

        testLabel = new JLabel("gfgddfgfdgdfsgfxxg");
        outputPanel.add(testLabel);


////////////////frame/////////////////////////

        frame.add(statsPanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mapPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Start");


        frame.pack();
        frame.setVisible(true);

        //System.out.println(hero.getName());

    }





    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == north){
            System.out.println("north");
        }else if(e.getSource() == east){
            System.out.println("east");
        }else if(e.getSource() == south){
            System.out.println("south");
        }else if(e.getSource() == west){
            System.out.println("west");
        }else if(e.getSource() == exit){
//            frame.CLOSE();
            System.out.println("exit");
            System.exit(0);

        }
    }


}
