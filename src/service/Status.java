package service;

public class Status {
    private final String STATUS;

    private Status(String status) {
        this.STATUS = status;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getSTATUS() {
        return STATUS;
    }

    @Override
    public String toString() {
        return STATUS;
    }
}
