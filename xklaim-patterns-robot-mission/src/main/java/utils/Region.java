package utils;

/*  Java class representing a rectangular region with a position (x,y) and dimensions (width, height):   */

public class Region {
    private double x;
    private double y;
    private double width;
    private double height;
    
    public Region(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public boolean contains(double x, double y) {
        return (x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height);
    }
    
    public boolean contains(Region other) {
        return (other.x >= this.x && other.x + other.width <= this.x + this.width &&
                other.y >= this.y && other.y + other.height <= this.y + this.height);
    }
}