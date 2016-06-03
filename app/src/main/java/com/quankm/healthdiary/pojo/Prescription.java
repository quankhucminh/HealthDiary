package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/30/2016.
 */
public class Prescription {
    private long _id;
    private long UserID;
    private String Name;
    private String NameClean;
    private String Condition;
    private String ConditionClean;
    private long ValidFromDate;
    private long ValidToDate;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public long getValidFromDate() {
        return ValidFromDate;
    }

    public void setValidFromDate(long validFromDate) {
        ValidFromDate = validFromDate;
    }

    public long getValidToDate() {
        return ValidToDate;
    }

    public void setValidToDate(long validToDate) {
        ValidToDate = validToDate;
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

    public String getNameClean() {
        return NameClean;
    }

    public void setNameClean(String nameClean) {
        NameClean = nameClean;
    }

    public String getConditionClean() {
        return ConditionClean;
    }

    public void setConditionClean(String conditionClean) {
        ConditionClean = conditionClean;
    }
}
