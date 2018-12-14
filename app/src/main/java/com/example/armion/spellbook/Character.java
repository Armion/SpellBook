/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook;

import com.example.armion.spellbook.spell.Metamagic;
import com.example.armion.spellbook.spell.PreparedSpell;
import com.example.armion.spellbook.spell.Spell;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Character {

    private int intelligence;
    private int strength;
    private int endurance;
    private int wisdom ;
    private int constitution;
    private int charisma;
    private int maxHP;
    private int hp;
    private int level;
    private List<Spell> spellList;
    private List<Metamagic> metamagicList;
    private String name;
    private List<SpellSlot> spellSlotList;
    private float maxWeight;
    private float weight;
    private Bag bag;
    private Money money;



    public Character(int intelligence, int strength, int endurance, int wisdom, int constitution, int charisma, int hp, int level, List<Spell> spellList, List<Metamagic> metamagicList, String name, List<SpellSlot> spellSlotList, Bag bag, Money money) {
        this.intelligence = intelligence;
        this.strength = strength;
        this.endurance = endurance;
        this.wisdom = wisdom;
        this.constitution = constitution;
        this.charisma = charisma;
        this.hp = hp;
        this.level = level;
        this.spellList = spellList;
        this.metamagicList = metamagicList;
        this.name = name;
        this.spellSlotList = spellSlotList;
        this.bag = bag;
        this.money = money;
    }

    public Character(){
        this.intelligence = 0;
        this.strength = 0;
        this.endurance = 0;
        this.wisdom = 0;
        this.constitution = 0;
        this.charisma = 0;
        this.level = 0;
        this.hp = 0;
        this.spellList = new ArrayList<>();
        this.spellSlotList = new ArrayList<>();
        this.metamagicList = new ArrayList<>();
        this.name = "";
        this.spellSlotList = new ArrayList<>();
        this.bag = new Bag();
        this.money = new Money();

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Spell> getSpellList() {
        return spellList;
    }

    public void setSpellList(List<Spell> spellList) {
        this.spellList = spellList;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public List<Metamagic> getMetamagicList() {
        return metamagicList;
    }

    public void setMetamagicList(List<Metamagic> metamagicList) {
        this.metamagicList = metamagicList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpellSlot> getSpellSlotList() {
        return spellSlotList;
    }

    public void setSpellSlotList(List<SpellSlot> spellSlotList) {
        this.spellSlotList = spellSlotList;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
