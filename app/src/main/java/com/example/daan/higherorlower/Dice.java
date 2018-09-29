package com.example.daan.higherorlower;

import java.util.Random;

public class Dice {

    private int indexRolled;
    private Random random = new Random();

    public int rollDice () {
        int temporary = random.nextInt(6);

        //Preventing duplicates
        if (!(temporary == indexRolled)) {
            this.indexRolled = temporary;
        } else {
            rollDice();
        }

        return this.indexRolled;
    }

    public int getIndexRolled() {
        return this.indexRolled;
    }
}
