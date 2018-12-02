package com.example.armion.spellbook;

import com.example.armion.spellbook.spell.PreparedSpell;

public class SpellSlot {

    private PreparedSpell preparedSpell;
    private int level;
    private boolean used;


    public SpellSlot(PreparedSpell preparedSpell, int level) {
        this.preparedSpell = preparedSpell;
        this.level = level;
        this.used = false;
    }

    public PreparedSpell getPreparedSpell() {
        return preparedSpell;
    }

    public void setPreparedSpell(PreparedSpell preparedSpell) {
        this.preparedSpell = preparedSpell;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
