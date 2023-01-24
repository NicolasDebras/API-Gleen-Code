package com.main.domain.functionnal.model;

import lombok.val;

public enum Speciality implements SpecialityType{
    TANK(){

        @Override
        public int getPv(Rarity rarity) {
            val pvDefault = 1000;
            return (int) (pvDefault + (rarity.getPercentage() * pvDefault)) ;
        }

        @Override
        public int getPower(Rarity rarity) {
            val powerDefault = 100;
            return (int) (powerDefault + (rarity.getPercentage() * powerDefault)) ;
        }

        @Override
        public int getAmor(Rarity rarity) {
            val armor = 20;
            return (int) (armor + (armor * rarity.getPercentage()));
        }

        @Override
        public Advantage getAdvantageType() {
            return new Advantage(
                    Speciality.MAGE,
                    20
            );
        }
    },
    ASSASSIN(){

        @Override
        public int getPv(Rarity rarity) {
            val pvDefault = 800;
            return (int) (pvDefault + (rarity.getPercentage() * pvDefault)) ;
        }

        @Override
        public int getPower(Rarity rarity) {
            val powerDefault = 200;
            return (int) (powerDefault + (rarity.getPercentage() * powerDefault)) ;
        }

        @Override
        public int getAmor(Rarity rarity) {
            val armor = 5;
            return (int) (armor + (armor * rarity.getPercentage()));
        }

        @Override
        public Advantage getAdvantageType() {
            return new Advantage(
                    Speciality.TANK,
                    30
            );
        }
    },
    MAGE(){
        @Override
        public int getPv(Rarity rarity) {
            val pvDefault = 700;
            return (int) (pvDefault + (rarity.getPercentage() * pvDefault)) ;
        }

        @Override
        public int getPower(Rarity rarity) {
            val powerDefault = 150;
            return (int) (powerDefault + (rarity.getPercentage() * powerDefault)) ;
        }

        @Override
        public int getAmor(Rarity rarity) {
            val armor = 10;
            return (int) (armor + (armor * rarity.getPercentage()));
        }

        @Override
        public Advantage getAdvantageType() {
            return new Advantage(
                    Speciality.ASSASSIN,
                    25
            );
        }
    };


}