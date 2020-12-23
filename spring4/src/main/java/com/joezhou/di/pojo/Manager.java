package com.joezhou.di.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JoeZhou
 */

@Data
public class Manager implements Serializable {

    public Manager() {
        System.out.println("Manager()...");
    }

    public Manager(int sal, int bonus) {
        System.out.println("total: " + (sal + bonus));
    }

    public Manager(double sal, double bonus) {
        System.out.println("total: " + (sal + bonus));
    }
}