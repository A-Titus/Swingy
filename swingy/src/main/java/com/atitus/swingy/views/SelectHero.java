package com.atitus.swingy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectHero implements ActionListener {

   // int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    JRadioButton rb1,rb2;
    JButton b;

    public SelectHero() {
        frame = new JFrame();

        JButton confirm = new JButton("confirm");
        confirm.addActionListener(this);

        label = new JLabel("Choose your option");

        ///////////////

            rb1=new JRadioButton("Create new Hero");
            rb1.setBounds(100,50,100,30);
            rb2=new JRadioButton("Select previous Hero");
            rb2.setBounds(100,100,100,30);
            ButtonGroup bg=new ButtonGroup();
            bg.add(rb1);bg.add(rb2);

        //////////////

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(label);
        panel.add(rb1);
        panel.add(rb2);
        panel.add(confirm);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("my first GUI");
        frame.pack();
        frame.setVisible(true);

    }

//    public static void main(String[] args) {
//        new firstGui();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //count++;
       // label.setText("Number of clicks: " + count);
        if(rb1.isSelected()){
            rb2.setSelected(false);
            System.out.println("you have selected create a new hero");
            new CreateNewHero();
            frame.dispose();
        }
        if(rb2.isSelected()){
            rb1.setSelected(false);
            System.out.println("you have selected using a previous hero");
            new SelectPreviousHero();
            frame.dispose();
        }
    }
}