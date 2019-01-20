/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook.spell;

public enum School {

    abjuration("Abjuration"),
    conjuration("Conjuration"),
    divination("Divination"),
    enchantment("Enchantment"),
    evocation("Evocation"),
    illusion("Illusion"),
    necromancy("Necromancy"),
    sin("Sin"),
    transmutation("Transmutation"),
    universalist("Universalist"),
    elemental("Elemental");

    private String name = "";

    //Constructor
    School(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
