package utils;

public class Location {
    private String name;
    public double x;
    public double y;
    
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
    public Location(String name, double x, double y) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public String getName() {
        return name;
    }
    
}