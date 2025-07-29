package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        ticksPerRotation = rightFront.getMotorType().getTicksPerRev();
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

    public void setPivot(int pos, double power){
        pivot.setTargetPosition(pos);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivot.setPower(power);
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
