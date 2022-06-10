package com.example.kitchen_tablet;

import java.util.ArrayList;

public class Bon {
    private String time;
    private ArrayList<Meal> b;
    ArrayList<Boolean>show;
    private boolean above;
    private String note;
    private String ID;

    /**
     * creats new bon.
     * @param time the time the bon was created.
     * @param b the meals in the bon.
     * @param above is the meal praioritized.
     * @param ID the meal unique id.
     */
    public Bon(String time, ArrayList<Meal> b,boolean above, String note,String ID) {
        this.time=time;
        this.b=b;
        this.above=above;
        this.note="table number"+note;
        this.ID=ID;
    }
    /**
     * creats new bon.
     * @param time the time the bon was created.
     * @param b the meals in the bon.
     * @param above is the meal praioritized.
     * @param ID the meal unique id.
     * @param show array list of active or not meals in the bon.
     */
    public Bon(String time, ArrayList<Meal> b,boolean above, String note,String ID,ArrayList<Boolean>show) {
        this.time=time;
        this.b=b;
        this.above=above;
        this.note="table number"+note;
        this.ID=ID;
        this.show=show;
    }
    public Bon(){

    }

    /**
     *
     * @return the array list of active or not meals.
     */
    public ArrayList<Boolean> getShow() {
        return show;
    }

    /**
     * @return the meals in the bon.
     */
    public ArrayList<Meal> getB() {
        return b;
    }
    /**
     * @return the time of the bon.
     */
    public String getTime() {
        return time;
    }

    /**
     * @return if there is any perferation
     */
    public boolean isAbove() {
        return above;
    }
    /**
     *
     * @return the note of the bon.
     */
    public String getNote() {
        return note;
    }

    public String getID() {
        return ID;
    }

    /**
     * sets the Array list of meals.
     * @param b the Array list of meals.
     */
    public void setB(ArrayList<Meal> b) {
        this.b = b;
    }


    /**
     * sets the time of the bon
     * @param time the time the bon was created.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * sets if there is any perferation in this bon.
     * @param above
     */
    public void setAbove(boolean above) {
        this.above = above;
    }

    /**
     * sets new note/
     * @param note the new note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     *
     * @param ID id of the bon
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *  sets the array list of active or not meals.
     */
    public void setShow(ArrayList<Boolean> show) {
        this.show = show;
    }

    @Override
    /**
     * makes a string out of all the object parameters.
     */
    public String toString() {
        if (note == null) {
            return "Bon{" +
                    "time='" + time + '\'' +
                    ", b=" + b +
                    ", above=" + above +
                    '}';
        }
        else {
            return "Bon{" +
                    "time='" + time + '\'' +
                    ", b=" + b +
                    ", above=" + above + "table number"+note+
                    '}';
        }
    }
}
