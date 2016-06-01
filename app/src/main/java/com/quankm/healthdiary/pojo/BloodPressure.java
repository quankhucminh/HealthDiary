package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class BloodPressure {

    private long _id;
    private long UserID;
    private int Systolic;
    private int Diastolic;
    private int HeartRate;
    private String Condition;
    private String DateTaken;
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

    public int getSystolic() {
        return Systolic;
    }

    public void setSystolic(int systolic) {
        Systolic = systolic;
    }

    public int getDiastolic() {
        return Diastolic;
    }

    public void setDiastolic(int diastolic) {
        Diastolic = diastolic;
    }

    public int getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(int heartRate) {
        HeartRate = heartRate;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
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

    public long getUpdatedTimeStamp() {
        return UpdatedTimeStamp;
    }

    public void setUpdatedTimeStamp(long updatedTimeStamp) {
        UpdatedTimeStamp = updatedTimeStamp;
    }
}
