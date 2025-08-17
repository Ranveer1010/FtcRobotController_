package org.firstinspires.ftc.teamcode.OpModes;  public class RobotLocation {  // Start of the RobotLocation class

    double angle;// Stores the current heading (angle in degrees)
    private double x = 0;
    private double y = 0;
    // Constructor – sets the starting angle
    public RobotLocation(double angle) {
        this.angle = angle;
    }
    //Returns angle
    public double getHeading() {
        double angle = this.angle;

        while (angle > 180) {
            angle -= 360;
        }
        while (angle < -180) {
            angle += 360;
        }

        return angle;
    }

    //Makes the angle into a printable string
    @Override
    public String toString() {
        return "RobotLocation: angle (" + angle + ")";
    }

    public void turn(double angleChange) {
        angle += angleChange;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getAngle() {
        return angle;
    }
    double getX(){
        return x;
    }
    public void changeX(double change){
        x += change;
    }
    public void setX(double x){
        this.x = x;
    }
    double getY(){
        return y;
    }
    public void changeY(double change){
        y += change;
    }
    public void setY(double y){
        this.y = y;
    }
}
