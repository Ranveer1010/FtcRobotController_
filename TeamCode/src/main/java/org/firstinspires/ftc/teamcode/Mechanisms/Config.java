package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Config {
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor pivot;
    private DcMotor slideExtend;
    private double ticksPerRotation;

    public void init(HardwareMap hwMap){
        rightFront = hwMap.get(DcMotor.class , "right_front");
        rightBack = hwMap.get(DcMotor.class , "right_back");
        leftFront = hwMap.get(DcMotor.class , "left_front");
        leftBack = hwMap.get(DcMotor.class , "left_back");
        pivot = hwMap.get(DcMotor.class , "pivot");
        slideExtend = hwMap.get(DcMotor.class , "slide_extend");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Switch to RUN_TO_POS in tele/auto
        slideExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ticksPerRotation = rightFront.getMotorType().getTicksPerRev();
    }
    public void setRightFront(double speed){
        rightFront.setPower(speed);
    }public void setRightBack(double speed){
        rightBack.setPower(speed);
    }public void setLeftFront(double speed){
        leftFront.setPower(speed);
    }public void setLeftBack(double speed){
        leftBack.setPower(speed);
    }public void setPivot(int pos, double power){
        pivot.setTargetPosition(pos);
        pivot.setPower(power);
    }public void setSlideExtend(double speed){
        slideExtend.setPower(speed);
    }
    public double getRightFrontMotorRotations(){
        return rightFront.getCurrentPosition() / ticksPerRotation;
    }
}
