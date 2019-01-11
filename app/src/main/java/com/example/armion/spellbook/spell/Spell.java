/**
 * @author Armion
 * @version 0.01
 */

package com.example.armion.spellbook.spell;


import com.example.armion.spellbook.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Spell {

    private  School school;
    private String range;
    private List<Descriptor> descriptorList = new ArrayList<>();
    private Dice dice;
    private String castingTime;
    private String area;
    private String duration;
    private String description;
    private String name;
    private int level;
    private UUID id;
    private String link;

    public Spell(){}

    public Spell(School school, String range, List<Descriptor> descriptorList, Dice dice, String castingTime, String area, String duration, String description, String name, int level, String link) {
        this.school = school;
        this.range = range;
        this.descriptorList = descriptorList;
        this.dice = dice;
        this.castingTime = castingTime;
        this.area = area;
        this.duration = duration;
        this.description = description;
        this.name = name;
        this.level = level;
        this.id = UUID.randomUUID();
        this.link = link;
    }

    public Spell(School school, String range, List<Descriptor> descriptorList, Dice dice, String castingTime, String area, String duration, String description, String name, int level, UUID id, String link) {
        this.school = school;
        this.range = range;
        this.descriptorList = descriptorList;
        this.dice = dice;
        this.castingTime = castingTime;
        this.area = area;
        this.duration = duration;
        this.description = description;
        this.name = name;
        this.level = level;
        this.id = id;
        this.link = link;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public List<Descriptor> getDescriptorList() {
        return descriptorList;
    }

    public void setDescriptorList(List<Descriptor> descriptorList) {
        this.descriptorList = descriptorList;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
