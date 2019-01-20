package com.example.armion.spellbook.entity;

public class EntityCreationException extends Exception {

    public EntityCreationException(){
        System.out.println("impossible to instantiate the entity");
    }
}
