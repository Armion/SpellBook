package com.example.armion.spellbook;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private List<Item> items;


    public Bag(){

        this.items = new ArrayList<>();
    }

    public Bag(List<Item> items){

        this.items = items;
    }
}
