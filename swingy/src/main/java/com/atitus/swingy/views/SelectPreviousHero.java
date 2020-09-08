package com.atitus.swingy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPreviousHero {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public SelectPreviousHero() {
        frame = new JFrame();
        label = new JLabel("Select your Hero");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30,10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select your hero");
        frame.pack();
        frame.setVisible(true);
    }
}