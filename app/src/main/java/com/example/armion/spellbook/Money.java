package com.example.armion.spellbook;

public class Money {

    private int copperPieces;
    private int silverPieces;
    private int goldPieces;
    private int platinumPieces;


    public Money(int copperPieces, int silverPieces, int goldPieces, int platinumPieces) {
        this.copperPieces = copperPieces;
        this.silverPieces = silverPieces;
        this.goldPieces = goldPieces;
        this.platinumPieces = platinumPieces;
    }

    public Money() {
        this.copperPieces = 0;
        this.silverPieces = 0;
        this.goldPieces = 0;
        this.platinumPieces = 0;
    }

    public int getCopperPieces() {
        return copperPieces;
    }

    public void setCopperPieces(int copperPieces) {
        this.copperPieces = copperPieces;
    }

    public int getSilverPieces() {
        return silverPieces;
    }

    public void setSilverPieces(int silverPieces) {
        this.silverPieces = silverPieces;
    }

    public int getGoldPieces() {
        return goldPieces;
    }

    public void setGoldPieces(int goldPieces) {
        this.goldPieces = goldPieces;
    }

    public int getPlatinumPieces() {
        return platinumPieces;
    }

    public void setPlatinumPieces(int platinumPieces) {
        this.platinumPieces = platinumPieces;
    }
}
