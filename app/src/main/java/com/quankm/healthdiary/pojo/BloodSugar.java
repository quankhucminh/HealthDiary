package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class BloodSugar {
    private long _id;
    private long UserID;
    private String RecordType;
    private float SugarLevel;
    private String DateTaken;
    private boolean isActive;
    private boolean isUpdated;
    private long UpdatedTimeStamp;

    public long getUpdatedTimeStamp() {
        return UpdatedTimeStamp;
    }

    public void setUpdatedTimeStamp(long updatedTimeStamp) {
        UpdatedTimeStamp = updatedTimeStamp;
    }

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

    public String getRecordType() {
        return RecordType;
    }

    public void setRecordType(String recordType) {
        RecordType = recordType;
    }

    public float getSugarLevel() {
        return SugarLevel;
    }

    public void setSugarLevel(float sugarLevel) {
        SugarLevel = sugarLevel;
    }

    public String getDateTaken() {
        return DateTaken;
    }

    public void setDateTaken(String dateTaken) {
        DateTaken = dateTaken;
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
}
