package com.example.bottomnamviagtionbar.Interfaces;

public class Bills {
    private String name;
    private Float value;


    //getters
    public String getName(){
        return name;
    }

    public Float getValue(){
        return value;
    }
    //setters
    public void setName(String _name){
        this.name= _name;
    }

    public void setValue(Float _value){
        this.value = _value;
    }


}
