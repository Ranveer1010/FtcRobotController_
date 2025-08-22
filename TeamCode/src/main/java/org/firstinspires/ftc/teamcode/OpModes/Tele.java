package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Mechanisms.Config;

@TeleOp
public class Tele extends OpMode {

    boolean initDone;
    double speed;

    //Object creation
    RobotLocation robotLocation = new RobotLocation(0); //Sets start angle to 0
    Config config = new Config();

    double squareInputWithSign(double input) {
        double output = input * input;
        if (input < 0) {
            output = output * -1.0;
        }
        return output;
    }

    void slideControl() {
        if (gamepad2.b) {
            gamepad2.rumble(100);
            config.setSlideExtend(gamepad2.right_stick_y / 2.5);
        }
        else{
            config.setSlideExtend(0.0);
        }
    }

    void driveForward(){ //drive method
        if (gamepad1.a) {
            gamepad1.rumble(100);
            // Get average encoder position of left side motors
            int leftPos = (config.getLeftFront().getCurrentPosition() + config.getLeftBack().getCurrentPosition()) / 2;
            // Get average encoder position of right side motors
            int rightPos = (config.getRightFront().getCurrentPosition() + config.getRightBack().getCurrentPosition()) / 2;
            // Calculate the error (difference) between left and right side
            int error = leftPos - rightPos;
            // Apply a correction factor to reduce drifting/curving
            double correction = error * 0.001;  // You can tune this value
            // Get forward/backward input from joystick (negative to correct direction)
            double basePower = gamepad1.left_stick_y;
            // Adjust motor power to keep the bot driving straight
            config.setAllDrivePower(basePower - correction, basePower + correction);
        } else {
            // If A is not pressed, stop all motors
            config.setAllDrivePower(0, 0);
        }

    }
    int state = 0;
    public void stateMethod() {
        switch (state) {
            case 0:
                if (getRuntime() <= 2.5) {
                    config.setAllDrivePower(0.25, 0.25);
                }else{
                    state = 1;
                }
                break;
            case 1:
                if (getRuntime() <= 5) {
                    config.setAllDrivePower(0.5, 0.5);
                }else{
                    state = 2;
                }
                break;
            case 2:
                if (getRuntime() <= 7.5) {
                    config.setAllDrivePower(1.0, 1.0);
                }
                break;
            default:
                if (gamepad1.right_stick_button) {
                    config.setAllDrivePower(0.0, 0.0);
                }
                else{
                    config.setAllDrivePower(1.0,1.0);
                }
        }
    }
    //Trigger addition method
    void triggersAdded(){
        double trigger1 = gamepad1.left_trigger;
        double trigger2 = gamepad1.right_trigger;
        double triggersTogether = trigger1 + trigger2;
        telemetry.addData("Triggers added is: ", triggersTogether);
    }

    @Override
    public void init() {
        telemetry.addLine("Hello World!");
        initDone = true;
        telemetry.addData("Init done = ", initDone);
        config.init(hardwareMap);
        config.getPivot().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    @Override
    public void start() {
        telemetry.addLine("Starting Opmode");
        robotLocation.setAngle(90); //Sets angle to 90 at start
    }

    @Override
    public void loop() {

        double imuOffSet = 0.0;
        double imuHeadingDegrees = config.getHeading(AngleUnit.DEGREES) + imuOffSet;


        telemetry.addData("Yaw is" , imuHeadingDegrees);

        speed = gamepad1.left_stick_y * -1.0;

        triggersAdded();
        driveForward();
        slideControl();

        if(gamepad1.left_stick_button){
            stateMethod();
        }

        if(gamepad1.back){
            config.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        config.setPivot(gamepad2.left_stick_y);
    if(gamepad2.left_trigger > 0) {
        config.setRotationPos(gamepad2.left_trigger);
    }
    if(gamepad2.right_trigger > 0){
        config.setRotationPos(-gamepad2.right_trigger);
    }

        if(gamepad2.right_bumper){
            config.setPinchOpen();
            gamepad2.rumbleBlips(1);
        }
        if(gamepad2.left_bumper){
            config.setPinchClose();
            gamepad2.rumbleBlips(1);
        }
        if(gamepad2.dpad_up) {
            config.setClaw(0.0);
            gamepad2.rumbleBlips(1);
        }
        if(gamepad2.dpad_left){
            config.setClaw(1.0);
            gamepad2.rumbleBlips(1);
        }
        if(gamepad2.dpad_down){
            config.setClaw(0.5);
        }
        telemetry.addData("Claw is" , config.clawVerticalPos());


        if (gamepad1.x) {
            robotLocation.turn(30);
        }

        double getRobotHeading = robotLocation.getHeading();
        double getRobotAngle = robotLocation.getAngle();
        telemetry.addData("Heading (normalized)", getRobotHeading);
        telemetry.addData("Raw angle", getRobotAngle);

        if (gamepad1.dpad_left) {
            robotLocation.changeX(0.1);
        }
        if (gamepad1.dpad_right) {
            robotLocation.changeX(-0.1);
        }
        if (gamepad1.dpad_up) {
            robotLocation.changeY(0.1);
        }
        if (gamepad1.dpad_down) {
            robotLocation.changeY(-0.1);
        }

        telemetry.addData("X position", robotLocation.getX());
        telemetry.addData("Y position", robotLocation.getY());
        telemetry.addData("RF rotations are: " , config.getRightFrontMotorRotations());
        telemetry.addData("Runtime is: " , getRuntime());

        telemetry.update();
    }
}
