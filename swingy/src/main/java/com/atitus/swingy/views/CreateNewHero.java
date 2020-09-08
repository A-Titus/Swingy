package com.atitus.swingy.views;

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
    private JButton create;

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
        panel.add(create);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create new hero");
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String heroClassResult = heroClass.getSelectedItem().toString();
        String nameResult = name.getText();
        int levelResult = Integer.parseInt(level.getText());
        int expResult = Integer.parseInt(exp.getText());
        int attackResult = Integer.parseInt(attack.getText());
        int defenceResult = Integer.parseInt(defence.getText());
        int hpResult = Integer.parseInt(hp.getText());


        //pass all this data to GetHeroStats controller.


//        System.out.println(heroClassResult);
//        System.out.println(nameResult);
//        System.out.println(levelResult);
//        System.out.println(expResult);
//        System.out.println(attackResult);
//        System.out.println(defenceResult);
//        System.out.println(heroClassResult);
//        System.out.println(hpResult);
    }
}