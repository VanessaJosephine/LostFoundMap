package com.example.lostfoundmap.sqlitehelper;

public class Advert {
    private int id;
    String name, type, phone, desc, date, location, latitude, longitude;

    public Advert(String name, String type, String phone, String desc, String date, String location, String latitude, String longitude) {
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.desc = desc;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }
}
