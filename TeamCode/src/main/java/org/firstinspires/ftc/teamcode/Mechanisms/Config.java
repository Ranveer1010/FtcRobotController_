package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Config {
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor pivot;
    private DcMotor slideExtend;
    private double ticksPerRotation;

    private Servo clawRotateServo;
    private Servo clawVertical;
    private Servo clawRotate2;

    private IMU imu;


    public void init(HardwareMap hwMap){
        rightFront = hwMap.get(DcMotor.class , "right_front");
        rightBack = hwMap.get(DcMotor.class , "right_back");
        leftFront = hwMap.get(DcMotor.class , "left_front");
        leftBack = hwMap.get(DcMotor.class , "left_back");
        pivot = hwMap.get(DcMotor.class , "pivot");
        slideExtend = hwMap.get(DcMotor.class , "slide_extend");

        clawRotateServo = hwMap.get(Servo.class , "claw_rotate_servo");
        clawVertical = hwMap.get(Servo.class , "pinch");
        clawRotate2 = hwMap.get(Servo.class , "claw_rotate_2");

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        imu = hwMap.get(IMU.class , "imu");
        RevHubOrientationOnRobot RevOrientation =
                new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT ,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));

        ticksPerRotation = rightFront.getMotorType().getTicksPerRev();
    }

    public double getHeading(AngleUnit angleUnit){
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    public double clawVerticalPos(){
        return clawVertical.getPosition();
    }
    public void setRotationPos(double pos){ //horizontal/turn
        clawRotateServo.setPosition(pos);
    }
    public void setPinchOpen(){ //pinch
        clawRotate2.setPosition(0.25);
    }
    public void setPinchClose(){
        clawRotate2.setPosition(1.0);
    }
    public void setClaw(double pos){ //vertical
        clawVertical.setPosition(pos);
    }

    public void setZeroPower(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        rightFront.setZeroPowerBehavior(zeroPowerBehavior);
        rightBack.setZeroPowerBehavior(zeroPowerBehavior);
        leftFront.setZeroPowerBehavior(zeroPowerBehavior);
        leftBack.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public void setAllDrivePower(double leftPower, double rightPower) {
        leftFront.setPower(leftPower);
        leftBack.setPower(leftPower);
        rightFront.setPower(rightPower);
        rightBack.setPower(rightPower);
    }

    public void setPivot(double speed){
        pivot.setPower(speed);
    }

    public void setSlideExtend(double speed){
        slideExtend.setPower(speed);
    }

    public double getRightFrontMotorRotations(){
        return rightFront.getCurrentPosition() / ticksPerRotation;
    }

    // Getter methods for encoder positions (added)
    public DcMotor getLeftFront() { return leftFront; }
    public DcMotor getLeftBack() { return leftBack; }
    public DcMotor getRightFront() { return rightFront; }
    public DcMotor getRightBack() { return rightBack; }

    public DcMotor getPivot() {
        return pivot;
    }
}
