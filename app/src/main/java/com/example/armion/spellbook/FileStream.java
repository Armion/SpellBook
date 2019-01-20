package com.example.armion.spellbook;

import android.content.Context;
import android.util.JsonReader;
import android.widget.Toast;

import com.example.armion.spellbook.entity.Bag;
import com.example.armion.spellbook.entity.Character;
import com.example.armion.spellbook.entity.Item;
import com.example.armion.spellbook.entity.Money;
import com.example.armion.spellbook.entity.SpellSlot;
import com.example.armion.spellbook.entity.spell.Metamagic;
import com.example.armion.spellbook.entity.spell.Spell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public abstract class FileStream {


    public static void saveLife(int life, Context context, String name){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Character character = FileStream.getCharacter(name, context);

        character.setHp(life);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSpell(List<Spell> spellList, Context context, String name){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = FileStream.getCharacter(name, context);

        character.setSpellList(spellList);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveItems(List<Item> itemList, Context context, String name){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = FileStream.getCharacter(name, context);

        Bag bag = character.getBag();

        bag.setItems(itemList);

        character.setBag(bag);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "impossible d'enregistrer  le sac !", Toast.LENGTH_SHORT).show();
        }

    }

    public static void saveSpellSlot(List<SpellSlot> spellList, Context context, String name){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = FileStream.getCharacter(name, context);

        character.setSpellSlotList(spellList);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveMetamagic(List<Metamagic> metamagicList, Context context, String name){


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = FileStream.getCharacter(name, context);

        character.setMetamagicList(metamagicList);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public static void saveMoney(Context context, Money money, String name){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = FileStream.getCharacter(name, context);

        character.setMoney(money);

        FileOutputStream output = null;


        FileStream.saveCharacter(character, name, context);


    }

    public static void saveCharacter(Character character, String name, Context context){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Character getCharacter(String name, Context context){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileInputStream in;
        JsonReader reader = null;
        Character character = new Character();

        try {
            in = context.openFileInput(name + ".json");

            character = gson.fromJson(new InputStreamReader(in, "UTF-8"), Character.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(character == null){
            return new Character();
        }

        return character;


    }




}
