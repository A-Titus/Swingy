package com.atitus.swingy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import com.atitus.swingy.models.*;
import com.atitus.swingy.controllers.*;

public class SelectPreviousHero implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel selectPanel;
     JButton  start;
    private JComboBox comboBox;
    ArrayList<Hero> heros = new ArrayList<Hero>();
    SaveData save = new SaveData();
    String[] heroNames;
    Hero hero = new Hero();

    public SelectPreviousHero() {
        frame = new JFrame();
        label = new JLabel("Select your Hero");

        selectPanel = new JPanel();
        selectPanel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        selectPanel.setLayout(new GridLayout(0, 1));


        start = new JButton("start");
        start.addActionListener(this);

        start.setBounds(200,100,75,20);
        selectPanel.revalidate();
        selectPanel.repaint();

        //////////////////
        heros = save.readHeroData();
        heroNames = new String[heros.size()];
        for(int i = 0; i < heroNames.length; i++) {
            String name = i+1 + ". " + heros.get(i).getHeroClass() + ", Name: " + heros.get(i).getName()+ ", Level: " + heros.get(i).getLevel() +", Exp: " + heros.get(i).getExp() + ", Attack: "+ heros.get(i).getAttack() + ", Defence: "+ heros.get(i).getDefence()+ ", Hp: "+ heros.get(i).getHp();
            heroNames[i] = name;
        }
        comboBox = new JComboBox(heroNames);
        ////////////////


        selectPanel.add(label);
        selectPanel.add(comboBox);
        selectPanel.add(start);

        frame.add(selectPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select your hero");
        frame.pack();
        frame.setVisible(true);
    }


   public void actionPerformed(ActionEvent e) {
        GetHeroStats heroData = new GetHeroStats();
        CreateMap map = new CreateMap();
       int x = 0;
       int y = 0;

        if (e.getSource() == start) {
            for (int i = 0; i <= heroNames.length - 1; i++) {
                String selected = comboBox.getSelectedItem().toString();
                if (selected == heroNames[i]) {
//                    System.out.println(selected);
                     x = map.getCenter(heros.get(i).getLevel());
                     y = map.getCenter(heros.get(i).getLevel());
                     hero = heroData.initHero(heros.get(i).getName(), heros.get(i).getHeroClass(), heros.get(i).getLevel(), heros.get(i).getExp(), heros.get(i).getAttack(), heros.get(i).getDefence(), heros.get(i).getHp(), x, y);
                     frame.dispose();
                     new Start(hero);
                }
            }
//            int x = mapStats.getCenter(levelResult);
//            int y = mapStats.getCenter(levelResult);
//            hero = heroData.initHero(nameResult, heroClassResult, levelResult, expResult, attackResult, defenceResult, hpResult, x, y);
//            new Start(hero);
        }
    }
}