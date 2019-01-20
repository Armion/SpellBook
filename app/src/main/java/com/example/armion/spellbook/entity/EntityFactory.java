package com.example.armion.spellbook.entity;

import com.example.armion.spellbook.entity.spell.Metamagic;
import com.example.armion.spellbook.entity.spell.Spell;
import com.example.armion.spellbook.hud.viewHolder.ViewHolderFactory;


public class EntityFactory {

    private static volatile EntityFactory instance = null;

    private EntityFactory(){

    }

    public final static EntityFactory getInstance(){


        if(EntityFactory.instance == null){

            synchronized (ViewHolderFactory.class) {
                if(EntityFactory.instance == null) {
                    EntityFactory.instance = new EntityFactory();
                }
            }
        }

        return  EntityFactory.instance;
    }


    public Entity create(Class type) throws EntityCreationException {

        if(type.equals(Metamagic.class)){
            return new Metamagic("", 0, "");
        }
        else if (type.equals(SpellSlot.class)){
            return  new SpellSlot(null, 0);
        }
        else if (type.equals(Spell.class)){
            return  new Spell();
        }
        else if (type.equals(Item.class)){
            return  new Item();
        }


        throw new EntityCreationException();

    }
}
