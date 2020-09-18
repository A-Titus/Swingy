package com.atitus.swingy.controllers;

import com.atitus.swingy.models.*;
import java.util.*;

public class ArtifactFactory {
    ArrayList<Artifacts> artifacts = new ArrayList<Artifacts>();
    Artifacts weapon = new Artifacts();
    Artifacts armor = new Artifacts();
    Artifacts helm = new Artifacts();
    Artifacts selectedArtifact = new Artifacts();

    public Artifacts getArtifact(){
        weapon.setName("weapon");
        weapon.setAttack(150);
        armor.setName("armor");
        armor.setDefence(200);
        helm.setName("helm");
        helm.setHp(300);

        artifacts.add(weapon);
        artifacts.add(armor);
        artifacts.add(helm);
        int choice = decide();
        if(choice == 1){
            //give artifact
            selectedArtifact = getRandomElement(artifacts);
            return(selectedArtifact);
        }
        return(selectedArtifact);
    }

    public int decide(){
        int choice[]={1,2};
        Random r = new Random();
        int index = r.nextInt(choice.length);
        return(choice[index]);
    }

    public Artifacts getRandomElement(ArrayList<Artifacts> artifacts)
    {
        Random rand = new Random();
        return artifacts.get(rand.nextInt(artifacts.size()));

    }
}