package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label,nameLabel,classLabel,levelLabel,expLabel,attackLabel,defenceLabel,hpLabel,name,heroClass,level,exp,attack,defence,hp;
    private JButton create;

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


        create = new JButton("Start");
        create.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(label);
        panel.add(classLabel);
        panel.add(heroClass);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(levelLabel);
        panel.add(level);
        panel.add(expLabel);
        panel.add(exp);
        panel.add(attackLabel);
        panel.add(attack);
        panel.add(defenceLabel);
        panel.add(defence);
        panel.add(hpLabel);
        panel.add(hp);
        panel.add(create);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Start");

        frame.pack();
        frame.setVisible(true);

        //System.out.println(hero.getName());

    }

    public void actionPerformed(ActionEvent e) {

    }


}