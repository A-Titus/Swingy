package com.atitus.swingy.views;

import com.atitus.swingy.controllers.*;
import com.atitus.swingy.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewHero implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JLabel label,nameLabel,classLabel,levelLabel,expLabel,attackLabel,defenceLabel,hpLabel;
    JTextField name,level,exp, attack,defence,hp;
    private String classes[]={"barbarian","archer","wizzard"};
    JComboBox heroClass;
    JRadioButton defaultHero,customHero;
    private JButton create;
    boolean check;
    CreateMap mapStats = new CreateMap();


    public CreateNewHero() {
        frame = new JFrame();

        label = new JLabel("Create your Hero");
        nameLabel = new JLabel("Name");
        classLabel = new JLabel("Class");
        levelLabel = new JLabel("Level");
        expLabel = new JLabel("Exp");
        attackLabel = new JLabel("Attack");
        defenceLabel = new JLabel("Defence");
        hpLabel = new JLabel("Hp");

        defaultHero=new JRadioButton("Default hero");
        defaultHero.setBounds(100,50,100,30);
        defaultHero.addActionListener(this);
        customHero=new JRadioButton("Custom hero");
        customHero.setBounds(100,100,100,30);
        customHero.addActionListener(this);
        ButtonGroup bg=new ButtonGroup();
        bg.add(defaultHero);bg.add(customHero);


        heroClass = new JComboBox(classes);
        heroClass.setBounds(50, 100,90,20);
        name = new JTextField();
        name.setBounds(50,50,150,20);
        level = new JTextField();
        level.setBounds(50,50,150,20);
        exp = new JTextField();
        exp.setBounds(50,50,150,20);
        attack = new JTextField();
        attack.setBounds(50,50,150,20);
        defence = new JTextField();
        defence.setBounds(50,50,150,20);
        hp = new JTextField();
        hp.setBounds(50,50,150,20);


        create = new JButton("create");
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
        panel.add(defaultHero);
        panel.add(customHero);
        panel.add(create);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create new hero");
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        check = true;
        if (e.getSource() == defaultHero){
            if(defaultHero.isSelected()){
                customHero.setSelected(false);
                level.setVisible(false);
                exp.setVisible(false);
                attack.setVisible(false);
                defence.setVisible(false);
                hp.setVisible(false);
                levelLabel.setVisible(false);
                expLabel.setVisible(false);
                attackLabel.setVisible(false);
                defenceLabel.setVisible(false);
                hpLabel.setVisible(false);

            }
        }else if(e.getSource() == customHero){
            if(customHero.isSelected()){
                defaultHero.setSelected(false);
                level.setVisible(true);
                exp.setVisible(true);
                attack.setVisible(true);
                defence.setVisible(true);
                hp.setVisible(true);
                levelLabel.setVisible(true);
                expLabel.setVisible(true);
                attackLabel.setVisible(true);
                defenceLabel.setVisible(true);
                hpLabel.setVisible(true);
                check = false;
            }
        }else if(e.getSource() == create ){

                String heroClassResult = heroClass.getSelectedItem().toString();
                String nameResult = name.getText();
                int levelResult = 1;
                int expResult = 1000;
                int attackResult = 1000;
                int defenceResult = 1000;
                int hpResult = 1000;
                int x = mapStats.getCenter(levelResult);
                int y = mapStats.getCenter(levelResult);


            //pass all this data to GetHeroStats controller.
            GetHeroStats heroData = new GetHeroStats();
            Hero hero = new Hero();
            Hero c_hero = new Hero();

            if(defaultHero.isSelected()){
                hero = heroData.initHero(nameResult, heroClassResult, levelResult, expResult, attackResult, defenceResult, hpResult, x, y);
                new Start(hero);
            }else{//if custom hero is selected

                validateInputs(level.getText(), exp.getText(), attack.getText(), defence.getText(), hp.getText());

                x = mapStats.getCenter(Integer.parseInt(level.getText()));//-------------do input validation--------------
                y = mapStats.getCenter(Integer.parseInt(level.getText()));


                c_hero = heroData.initHero(name.getText(), heroClass.getSelectedItem().toString(), Integer.parseInt(level.getText()),Integer.parseInt(exp.getText()), Integer.parseInt(attack.getText()), Integer.parseInt(defence.getText()), Integer.parseInt(hp.getText()), x, y);
                new Start(c_hero);
            }

            frame.dispose();
        }
    }
    public void validateInputs(String level, String exp, String attack, String defence, String hp ){
        try
        {
            Integer.parseInt(level);
        }
        catch (NumberFormatException err)
        {
            System.out.println(level + " is not a valid number");
            System.exit(1);
        }

        try
        {
            Integer.parseInt(exp);
        }
        catch (NumberFormatException err)
        {
            System.out.println(exp + " is not a valid number");
            System.exit(1);
        }

        try
        {
            Integer.parseInt(attack);
        }
        catch (NumberFormatException err)
        {
            System.out.println(attack + " is not a valid number");
            System.exit(1);
        }

        try
        {
            Integer.parseInt(defence);
        }
        catch (NumberFormatException err)
        {
            System.out.println(defence + " is not a valid number");
            System.exit(1);
        }

        try
        {
            Integer.parseInt(hp);
        }
        catch (NumberFormatException err)
        {
            System.out.println(hp + " is not a valid number");
            System.exit(1);
        }

    }
}