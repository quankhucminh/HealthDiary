package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class ChronicDisease {
    private long _id;
    private long UserID;
    private String ConditionName;
    private String Note;
    private boolean isActive;
    private boolean isUpdated;
    private long UpdatedTimeStamp;

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

    public String getConditionName() {
        return ConditionName;
    }

    public void setConditionName(String conditionName) {
        ConditionName = conditionName;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public long getUpdatedTimeStamp() {
        return UpdatedTimeStamp;
    }

    public void setUpdatedTimeStamp(long updatedTimeStamp) {
        UpdatedTimeStamp = updatedTimeStamp;
    }
}
