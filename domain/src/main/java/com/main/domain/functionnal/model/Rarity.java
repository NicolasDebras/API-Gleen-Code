package com.main.domain.functionnal.model;

public enum Rarity implements Percentage{

    COMMUN(){
        @Override
        public double getPercentage(){
            return 0.0;
        }
    },
    RARE(){
        @Override
        public double getPercentage(){
            return 10;
        }
    },
    LEGENDARY(){
        @Override
        public double getPercentage(){
            return 20;
        }
    };



}
