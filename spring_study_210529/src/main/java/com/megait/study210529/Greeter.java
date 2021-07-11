package com.megait.study210529;

public class Greeter {
    private String format;
    private static Greeter greeter = new Greeter();

    private Greeter() {

    }

    public static Greeter getInstance() {
        return greeter;
    }

    public String greet(String guest) {
        return String.format(format, guest);
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
