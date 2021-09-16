package com.umaralikhon.entity;

class CodeGenerator {
    private static int min = 100000;
    private static int max = 999999;

    public static int rnd(){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
