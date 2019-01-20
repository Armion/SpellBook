package com.example.armion.spellbook.entity.spell;

public enum SavingThrow {

    reflex("Reflex"),
    will("Will"),
    fortitude("Fortitude");


    private String name = "";

    //Constructor
    SavingThrow(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
