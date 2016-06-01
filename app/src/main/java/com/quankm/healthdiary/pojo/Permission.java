package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class Permission {
    private long _id;
    private long UserID;
    private long ObserverID;
    private String DateAdded;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }

    public long getObserverID() {
        return ObserverID;
    }

    public void setObserverID(long observerID) {
        ObserverID = observerID;
    }

    public String getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(String dateAdded) {
        DateAdded = dateAdded;
    }
}
