package com.neo.domain;

import java.util.ArrayList;

public class Result {
    private ArrayList<int[]> testsuite;
    private long time;
    private int size;

    public Result(ArrayList<int[]>testsuite, long time,int size){
        this.testsuite = testsuite;
        this.time = time;
        this.size=size;
    }

    public double getTime() {
        return time;
    }
    public int getSize(){
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<int []> getTestsuite() {
        return testsuite;
    }

    public void setTime(long time) {
        this.time = time;
    }
}