package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class Remind {
    private long _id;
    private long PresMedID;
    private String TimeRemind;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getPresMedID() {
        return PresMedID;
    }

    public void setPresMedID(long presMedID) {
        PresMedID = presMedID;
    }

    public String getTimeRemind() {
        return TimeRemind;
    }

    public void setTimeRemind(String timeRemind) {
        TimeRemind = timeRemind;
    }
}
