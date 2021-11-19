package com.plumber.user;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String user_id;
    private String user_number,email,location,name,time,phone,otp;

    private Context context;

    SharedPreferences sharedPreferences;


    public User(Context context) {

        this.context=context;

        sharedPreferences=context.getSharedPreferences("login_details", Context.MODE_PRIVATE);

    }


//    public String getUser_id() {
//        user_id=sharedPreferences.getString("user_id","");
//        return user_id;
//    }
//
//    public void setUser_id(String user_id) {
//        sharedPreferences.edit().putString("user_id",user_id).commit();
//        this.user_id = user_id;
//    }
//
//    public String getUser_number() {
//        user_number=sharedPreferences.getString("user_number","");
//        return user_number;
//    }
//
//    public void setUser_number(String user_number) {
//        sharedPreferences.edit().putString("user_number",user_number).commit();
//        this.user_number = user_number;
//    }
//
//    public String getEmail() {
//        email=sharedPreferences.getString("email","");
//        return email;
//    }
//
//    public void setEmail(String email) {
//        sharedPreferences.edit().putString("email",email).commit();
//        this.email = email;
//    }
//
    public String getLocation() {
        location=sharedPreferences.getString("location","");
        return location;
    }

    public void setLocation(String location) {
        sharedPreferences.edit().putString("location",location).commit();
        this.location = location;
    }
//
//    public String getName() {
//        name=sharedPreferences.getString("name","");
//        return name;
//    }
//
//
//    public void setName(String name) {
//        sharedPreferences.edit().putString("name",name).commit();
//        this.name = name;
//    }
//
//
//    public String getTime() {
//        time=sharedPreferences.getString("time","");
//        return time;
//    }
//
//
//    public void setTime(String time) {
//        sharedPreferences.edit().putString("time",time).commit();
//        this.time = time;
//    }
//
//
//    public String getPhone() {
//        phone=sharedPreferences.getString("phone","");
//        return phone;
//    }
//
//
//    public void setPhone(String phone) {
//        sharedPreferences.edit().putString("phone",phone).commit();
//        this.phone = phone;
//    }
//
//
//    public String getOtp() {
//        otp=sharedPreferences.getString("otp","");
//        return otp;
//    }
//
//
//    public void setOtp(String otp) {
//        sharedPreferences.edit().putString("otp",otp).commit();
//        this.otp = otp;
//    }

    public  void  remove(){

        sharedPreferences.edit().clear().commit();

    }

}