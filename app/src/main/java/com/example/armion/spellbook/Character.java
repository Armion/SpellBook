/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook;

import com.example.armion.spellbook.spell.PreparedSpell;
import com.example.armion.spellbook.spell.Spell;

import java.util.List;

public class Character {

    private int intelligence;
    private int strength;
    private int endurance;
    private int wisdom ;
    private int constitution;
    private int charisma;
    private int hp;
    private int level;
    private List<Spell> spellList;
    private  List<PreparedSpell> preparedSpellList ;


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

    public List<PreparedSpell> getPreparedSpellList() {
        return preparedSpellList;
    }

    public void setPreparedSpellList(List<PreparedSpell> preparedSpellList) {
        this.preparedSpellList = preparedSpellList;
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


}
