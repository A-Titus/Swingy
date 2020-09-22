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
    int maxX = 0;
    int maxY = 0;
    boolean check;
    CreateMap mapStats;
    CreateEnemies newEnemies = new CreateEnemies();
    CheckContact contact = new CheckContact();
    ArtifactFactory artifactFact = new ArtifactFactory();
    SaveData save = new SaveData();

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
        //statsPanel.setPreferredSize(new Dimension(350, 190));

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
        maxX = mapSize -1 ;
        maxY = mapSize - 1;

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
            enemyLabel = new JLabel("E");
            panelHolder[enemy.getX()][enemy.getY()].add(enemyLabel);

        }
        heroLabel = new JLabel("H");
        panelHolder[hero.getX()][hero.getY()].add(heroLabel);

        //call create enemy function and for each create a panel holder ovbject

/////////////outputPanel////////////////////////////
        outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        outputPanel.setLayout(new GridLayout(0,1));



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
    public void wonDialog(){
        JFrame dialogframe;

        dialogframe = new JFrame();
        int output = JOptionPane.showConfirmDialog(dialogframe
                , "Congrats!! You have won the game!!!?"
                ,"Game Over"
                ,JOptionPane.OK_CANCEL_OPTION);

        if(output == JOptionPane.OK_OPTION){
            dialogframe.dispose();
            frame.dispose();
            new SelectHero();
        }else if(output == JOptionPane.CANCEL_OPTION){
            System.exit(1);
        }
    }

    public boolean keepArtifactDialog(String artifactName){
        JFrame dialogframe;

        dialogframe = new JFrame();
        int output = JOptionPane.showConfirmDialog(dialogframe
                , "You have picked up an " + artifactName + ". Do you want to keep it?"
                ,"Artifact"
                ,JOptionPane.YES_NO_OPTION);

        if(output == JOptionPane.YES_OPTION){
            return true;
        }else{
            dialogframe.dispose();
            return false;
        }
    }

    public void lostBattleDialog(){
        JFrame dialogframe;

        dialogframe = new JFrame();
        int output = JOptionPane.showConfirmDialog(dialogframe
                , "Sorry You have Lost that battle?"
                ,"Game Over"
                ,JOptionPane.OK_CANCEL_OPTION);

        if(output == JOptionPane.OK_OPTION){
            dialogframe.dispose();
            frame.dispose();
            new SelectHero();
        }else if(output == JOptionPane.CANCEL_OPTION){
            System.exit(1);
        }
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

            outputPanel.add(new JLabel("You chose to battle"));

            //simulate battle
            enemyToRemove = contact.getEnemy(hero, enemies);//get enemy that needs to be removed
            enemyStats = contact.calcXp(enemyToRemove);
            result = contact.battle(hero, enemies);
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

               //update output panel
                outputPanel.add(new JLabel("Hero : "+ hero.getName() +" vs Enemy : " + enemyToRemove.getName()));
                outputPanel.add(new JLabel("Attack:  " + hero.getName() + ": " + hero.getAttack()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getAttack()));
                outputPanel.add(new JLabel("Defence:  " + hero.getName() + ": " + hero.getDefence()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getDefence()));
                outputPanel.add(new JLabel("Hp:  " + hero.getName() + ": " + hero.getHp()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getHp()));
                outputPanel.add(new JLabel("Congratulations You have Won the Battle!!!"));
                Artifacts chosenArtifact = new Artifacts();
                chosenArtifact = artifactFact.getArtifact();

                if(chosenArtifact.getName() == "weapon"){
                    if(keepArtifactDialog("weapon") == true){
                        hero.setAttack(hero.getAttack() + chosenArtifact.getAttack());
                        outputPanel.add(new JLabel("You have recieved a weapon artifact your Attack has increased by "+ chosenArtifact.getAttack()));

                    }
                }else if(chosenArtifact.getName() == "armor"){
                    if(keepArtifactDialog("armor") == true) {
                        hero.setDefence(hero.getDefence() + chosenArtifact.getDefence());
                        outputPanel.add(new JLabel("You have recieved an armor artifact your Defence has increased by " + chosenArtifact.getDefence()));
                    }
                }else if(chosenArtifact.getName() == "helm"){
                    if(keepArtifactDialog("helm") == true){
                        hero.setHp(hero.getHp() + chosenArtifact.getHp());
                        outputPanel.add(new JLabel("You have recieved a helm artifact your Hp has increased by "+ chosenArtifact.getHp()));
                    }
                }
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
                outputPanel.add(new JLabel("Hero : "+ hero.getName() +" vs Enemy : " + enemyToRemove.getName()));
                outputPanel.add(new JLabel("Attack:  " + hero.getName() + ": " + hero.getAttack()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getAttack()));
                outputPanel.add(new JLabel("Defence:  " + hero.getName() + ": " + hero.getDefence()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getDefence()));
                outputPanel.add(new JLabel("Hp:  " + hero.getName() + ": " + hero.getHp()+ " vs " + enemyToRemove.getName() +": " + enemyToRemove.getHp()));

                //save data to text file
                save.save(hero);
                lostBattleDialog();
               System.out.println("game over");

           }

        } else if(output == JOptionPane.NO_OPTION){
            outputPanel.add(new JLabel("Scardey Cat"));
//            System.out.println("Chicken shit");
            //print to panel
        }
    }

    public void actionPerformed(ActionEvent e) {

        int heroLvl = hero.getLevel();

        if (e.getSource() == north){
            System.out.println("north");
            hero.setX(hero.getX() - 1);
            if(mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY) == true){
                panelHolder[hero.getX()][hero.getY()].add(heroLabel);
                mapPanel.revalidate();
                mapPanel.repaint();
                save.save(hero);
                wonDialog();
            }
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());

        }else if(e.getSource() == east){
            System.out.println("east");
            hero.setY(hero.getY() + 1);
            if(mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY) == true){
                panelHolder[hero.getX()][hero.getY()].add(heroLabel);
                mapPanel.revalidate();
                mapPanel.repaint();
                save.save(hero);
                wonDialog();
            }
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == south){
            System.out.println("south");
            hero.setX(hero.getX() + 1);
            if(mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY) == true){
                panelHolder[hero.getX()][hero.getY()].add(heroLabel);
                mapPanel.revalidate();
                mapPanel.repaint();
                save.save(hero);
                wonDialog();
            }
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){

                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == west){
            System.out.println("west");
            hero.setY(hero.getY() - 1);
            if(mapStats.checkBorderCoordinates(heroLvl, hero.getX(), hero.getY(), maxX, maxY) == true){
                panelHolder[hero.getX()][hero.getY()].add(heroLabel);
                mapPanel.revalidate();
                mapPanel.repaint();
                save.save(hero);
                wonDialog();
            }
            panelHolder[hero.getX()][hero.getY()].add(heroLabel);
            mapPanel.revalidate();
            mapPanel.repaint();
            check = contact.check(hero, enemies);
            if(check == true){
                battleDialog();
            }
            System.out.println("X: " + hero.getX());
            System.out.println("Y: " + hero.getY());
        }else if(e.getSource() == exit){
//          add saving current stats to text file
            frame.dispose();
            save.save(hero);
            new SelectHero();

        }
    }


}
