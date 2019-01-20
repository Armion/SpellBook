package com.example.armion.spellbook.entity.spell;

import android.content.Context;

import com.example.armion.spellbook.FileStream;
import com.example.armion.spellbook.entity.Entity;

import java.util.List;
import java.util.UUID;

public class Metamagic extends Entity {

    String name;
    int level;
    String description;
    UUID id;

    public Metamagic(String name, int level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.id = UUID.randomUUID();
    }

    public Metamagic(String name, int level, String description, UUID id) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public void save(List<? extends Entity> entityList, Context context, String name) {
        FileStream.saveMetamagic((List<Metamagic>) entityList, context, "Elyndil");
    }
}
