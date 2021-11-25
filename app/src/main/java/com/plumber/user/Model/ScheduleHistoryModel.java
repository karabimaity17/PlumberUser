package com.plumber.user.Model;

public class ScheduleHistoryModel {
    String id,name,desc,ratting,date,time,day,status;
    int image;
    public ScheduleHistoryModel(String id, String name, String desc, String ratting, String date, String time, String day, int image, String status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.ratting = ratting;
        this.date = date;
        this.time = time;
        this.day = day;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getRatting() {
        return ratting;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public int getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }
}
