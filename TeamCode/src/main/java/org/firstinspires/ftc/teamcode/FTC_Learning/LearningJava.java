package org.firstinspires.ftc.teamcode.FTC_Learning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LearningJava extends OpMode {

    int gradeForGradeSeven = 98;
    boolean initDone;
    RobotLocation robotLocation = new RobotLocation(0);

    void robotSpeed() {
        if (gamepad1.a) {
            double turtleInput = gamepad1.left_stick_y / -2.0;
            telemetry.addLine("Turtle active");
            telemetry.addData("Turtle speed is: ", turtleInput);
        } else {
            telemetry.addLine("Turtle not active robot at normal speed");
            double speed = -1.0 * gamepad1.left_stick_y;
            telemetry.addData("Bot speed is: ", speed);
        }
    }

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
    }

    @Override
    public void start() {
        telemetry.addLine("Starting Opmode");
        telemetry.addData("Your Grade Is: ", gradeForGradeSeven);

        // Set angle once at start
        robotLocation.setAngle(90);
    }

    @Override
    public void loop() {
        robotSpeed();
        triggersAdded();

        telemetry.addData("Right stick X GP1 = ", gamepad1.right_stick_x);
        telemetry.addData("Right stick Y GP1 = ", gamepad1.right_stick_y);
        telemetry.addData("Diff between GP1 left and right joystick y is ", gamepad1.left_stick_y - gamepad1.right_stick_y);

        if (gamepad1.b) {
            telemetry.addLine("B is pressed");
        }

        // Turn the robot by 30 degrees only when X button is pressed
        if (gamepad1.x) {
            robotLocation.turn(30);
        }

        // Update heading variables after possible turn
        double getRobotHeading = robotLocation.getHeading();
        double getRobotAngle = robotLocation.getAngle();

        telemetry.addData("Heading (normalized)", getRobotHeading);
        telemetry.addData("Raw angle", getRobotAngle);

        // Update X/Y based on dpad presses
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
    }
}
