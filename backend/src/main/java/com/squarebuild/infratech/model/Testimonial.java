package com.squarebuild.infratech.model;

public class Testimonial {
    private final String name;
    private final String location;
    private final String message;
    private final int rating;

    public Testimonial(String name, String location, String message, int rating) {
        this.name = name;
        this.location = location;
        this.message = message;
        this.rating = rating;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getMessage() { return message; }
    public int getRating() { return rating; }
}
