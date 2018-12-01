package com.example.armion.spellbook;

import android.content.Context;
import android.util.JsonReader;

import com.example.armion.spellbook.spell.Metamagic;
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



    public static void saveMetamagic(List<Metamagic> metamagicList, Context context, String name){


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Character character = new Character();

        character.setMetamagicList(metamagicList);

        FileOutputStream output = null;


        try {
            output = context.openFileOutput(name + ".json", MODE_PRIVATE);
            output.write(gson.toJson(character).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }




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
            System.out.println(character.getCharisma());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return character;


    }



}
