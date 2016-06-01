package com.quankm.healthdiary.pojo;

/**
 * Created by Infernocorez on 5/31/2016.
 */
public class PresMed {
    private long _id;
    private long PresID;
    private long MedID;
    private boolean isActive;
    private boolean isUpdated;
    private long UpdatedTimeStamp;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getPresID() {
        return PresID;
    }

    public void setPresID(long presID) {
        PresID = presID;
    }

    public long getMedID() {
        return MedID;
    }

    public void setMedID(long medID) {
        MedID = medID;
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
