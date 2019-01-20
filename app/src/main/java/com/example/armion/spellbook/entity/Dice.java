package com.example.armion.spellbook.entity;

public enum Dice {

    d2("D2"),
    d4("D4"),
    d6("D6"),
    d8("D8"),
    d10("D10"),
    d12("D12"),
    d20("D20"),
    d100("D100");





    private String name;

    //Constructor
    Dice(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
