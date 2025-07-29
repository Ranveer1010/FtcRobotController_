package org.firstinspires.ftc.teamcode.FTC_Learning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Config;

@TeleOp
public class LearningJava extends OpMode {

    //Var creation
    int gradeForGradeSeven = 98;
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

    //Get robot speed method
     void robotSpeed() {
        if (gamepad1.left_stick_button) {
            double turtleInput = gamepad1.left_stick_y / -2.0;
            telemetry.addLine("Turtle active");
            telemetry.addData("Turtle speed is: ", turtleInput);
        } else {
            telemetry.addLine("Turtle not active robot at normal speed");
            double speed = -1.0 * gamepad1.left_stick_y;
            telemetry.addData("Bot speed is: ", speed);
        }
    }

    void slideControl() {
        if (gamepad1.b) {
            config.setSlideExtend(gamepad1.right_stick_y / 2.5);
        }
        else{
            config.setSlideExtend(0.0);
        }
    }

    void driveForward(){
        if (gamepad1.a) {
            // Show on telemetry that button A is being held
            telemetry.addLine("A is running new new new new");

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



    //Trigger addition method
    void triggersAdded() {
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
        config.getPivot().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        config.getPivot().setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    @Override
    public void start() {
        telemetry.addLine("Starting Opmode");
        telemetry.addData("Your Grade Is: ", gradeForGradeSeven);
        robotLocation.setAngle(90); //Sets angle to 90 at start
    }

    @Override
    public void loop() {

        speed = gamepad1.left_stick_y * -1.0;


        robotSpeed();
        triggersAdded();
        driveForward();
        slideControl();

        if(gamepad1.left_bumper){
            config.setPivot(134 , 0.5);
        }

        /*String touchSensorPressed = "Not pressed";
        if(touchSensor.isTouchSensorPressed()){
            touchSensorPressed = "Pressed";
        }*/

        //telemetry.addData("Touch sensor " , touchSensor.isTouchSensorPressed());

        telemetry.addData("Right stick X GP1 = ", gamepad1.right_stick_x);
        telemetry.addData("Right stick Y GP1 = ", gamepad1.right_stick_y);
        telemetry.addData("Diff between GP1 left and right joystick y is ", gamepad1.left_stick_y - gamepad1.right_stick_y);

        if(gamepad1.right_stick_button){
            config.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);
        }

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

        telemetry.update();
    }
}
