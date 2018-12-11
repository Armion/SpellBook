package com.example.armion.spellbook;

import com.example.armion.spellbook.spell.PreparedSpell;

import java.util.UUID;

public class SpellSlot {

    private PreparedSpell preparedSpell;
    private int level;
    private boolean used;
    private UUID id;


    public SpellSlot(PreparedSpell preparedSpell, int level) {
        this.preparedSpell = preparedSpell;
        this.level = level;
        this.used = false;
        this.id = UUID.randomUUID();
    }

    public SpellSlot(PreparedSpell preparedSpell, int level, boolean used, UUID id) {
        this.preparedSpell = preparedSpell;
        this.level = level;
        this.used = used;
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
