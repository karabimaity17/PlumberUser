package com.plumber.user.Model;

public class BookingHistoryModel {
    String id,name,desc,booking_type,date,time,day,status;
    int image;
    public BookingHistoryModel(String id, String name, String desc, String booking_type, String date, String time, String day, int image, String status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.booking_type = booking_type;
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

    public String getBooking_type() {
        return booking_type;
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
