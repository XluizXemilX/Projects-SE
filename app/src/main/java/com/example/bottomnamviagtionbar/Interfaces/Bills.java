package com.example.bottomnamviagtionbar.Interfaces;

        import android.widget.Spinner;

        import java.util.Date;

public class Bills {

    private Float value;
    private String name;
    private Date date;


    //getters
    public String GetString(){
        return name;
    }

    public Date GetDate(){
        return date;
    }

    public Float getValue(){
        return value;
    }
    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setValue(Float _value){
        this.value = _value;
    }


}
