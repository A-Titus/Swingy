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
     JButton select, start;
    private JComboBox comboBox;
    ArrayList<Hero> heros = new ArrayList<Hero>();
    SaveData save = new SaveData();

    public SelectPreviousHero() {
        frame = new JFrame();
        label = new JLabel("Select your Hero");

        selectPanel = new JPanel();
        selectPanel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        selectPanel.setLayout(new GridLayout(0, 1));

        select = new JButton("select");
        select.addActionListener(this);
        start = new JButton("start");
        start.addActionListener(this);

        select.setBounds(200,100,75,20);

        //////////////////
        heros = save.readHeroData();
        String[] heroNames = new String[heros.size()];
        for(int i = 0; i < heroNames.length; i++) {
            String name = heros.get(i).getHeroClass() + " " + heros.get(i).getName();
            heroNames[i] = name;
        }
        comboBox = new JComboBox(heroNames);
        ////////////////


        selectPanel.add(label);
        selectPanel.add(comboBox);

        frame.add(selectPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select your hero");
        frame.pack();
        frame.setVisible(true);
    }


   public void actionPerformed(ActionEvent e) {
        GetHeroStats heroData = new GetHeroStats();

        if (e.getSource() == select) {
//            Hero data = new Hero();
//            data = comboBox.getItemAt(comboBox.getSelectedIndex()); //fix ??!?!?!?!?!??!?!?!?!
//            System.out.println(data.getName());
        }
        if (e.getSource() == start) {
//            int x = mapStats.getCenter(levelResult);
//            int y = mapStats.getCenter(levelResult);
//            hero = heroData.initHero(nameResult, heroClassResult, levelResult, expResult, attackResult, defenceResult, hpResult, x, y);
//            new Start(hero);
        }
    }
}