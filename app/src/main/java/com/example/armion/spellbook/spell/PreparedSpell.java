package com.example.armion.spellbook.spell;


import java.util.ArrayList;
import java.util.List;

public class PreparedSpell {

    Spell spell;
    List<Metamagic> metamagicList = new ArrayList<Metamagic>();

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public List<Metamagic> getMetamagicList() {
        return metamagicList;
    }

    public void setMetamagicList(List<Metamagic> metamagicList) {
        this.metamagicList = metamagicList;
    }

    public int getLevel(){

        int level = spell.getLevel();

        for(Metamagic m : metamagicList){
            level += m.getLevel();
        }

        return level;
    }
}
