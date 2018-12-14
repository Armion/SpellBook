package com.example.armion.spellbook;

public class Item {

    private String name;
    private Money value;
    private String description;
    private int durability;
    private float weight;


    public Item(String name, Money value, String description, int durability, float weight) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.durability = durability;
        this.weight = weight;
    }

    public Item() {
        this.name = new String();
        this.value = new Money();
        this.description = new String();
        this.durability = 0;
        this.weight = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getValue() {
        return value;
    }

    public void setValue(Money value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
