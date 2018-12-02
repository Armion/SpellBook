/**
 * @author Armion
 * @version 0.01
 */


package com.example.armion.spellbook.spell;

import android.content.Context;

import com.example.armion.spellbook.R;

public enum Descriptor {



    banishment("Banishment"),
    counterspell("Counterspell"),
    creation("Creation"),
    extradimension("Extradimension"),
    infernalBinder("Infernal Binder"),
    teleportation("Teleportation"),
    prophecy("Prophecy"),
    foresight("Foresight"),
    scryer("Scryer"),
    controller("Controller"),
    manipulator("Manipulator"),
    admixture("Admixture"),
    generation("Generation"),
    deception("Deception"),
    mageOfTheVeil("Mage Of Veil"),
    phantasm("Phantasm"),
    shadow("Shadow"),
    life("Life"),
    undead("Undead"),
    enhancement("Enhancement"),
    shapechange("Shapechange"),
    arcaneCrafter("Arcane Crafter"),
    aether("Aether"),
    air("Air"),
    ice("Ice"),
    smoke("Smoke"),
    earth("Earth"),
    magma("Magma"),
    mud("Mud"),
    fire("Fire"),
    water("Water"),
    metal("Metal"),
    dark("Void"),
    wood("Wood");

    private String name = "";

    //Constructor
    Descriptor(String name){
        this.name = name;

    }

    public String toString(){
        return name;
    }
}
