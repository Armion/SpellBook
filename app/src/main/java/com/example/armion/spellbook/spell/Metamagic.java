package com.example.armion.spellbook.spell;

import java.util.Objects;

public class Metamagic {

    String name;
    int level;
    String description;

    public Metamagic(String name, int level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metamagic metamagic = (Metamagic) o;
        return level == metamagic.level &&
                name.equals(metamagic.name) &&
                description.equals(metamagic.description);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
