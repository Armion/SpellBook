package com.example.armion.spellbook.entity;

import android.content.Context;

import java.util.List;

public abstract class Entity {

    public abstract void save(List<? extends Entity> entityList, Context context, String name);
}
