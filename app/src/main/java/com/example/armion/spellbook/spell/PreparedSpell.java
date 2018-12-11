package com.example.armion.spellbook.spell;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PreparedSpell {

    private  Spell spell;
    private List<Metamagic> metamagicList = new ArrayList<>();
    private UUID id;


    public PreparedSpell(Spell spell, List<Metamagic> metamagicList) {
        this.spell = spell;
        this.metamagicList = metamagicList;
        this.id = UUID.randomUUID();
    }

    public PreparedSpell(Spell spell, List<Metamagic> metamagicList, UUID id) {
        this.spell = spell;
        this.metamagicList = metamagicList;
        this.id = id;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getLevel(){

        int level = spell.getLevel();

        for(Metamagic m : metamagicList){
            level += m.getLevel();
        }

        return level;
    }
}
