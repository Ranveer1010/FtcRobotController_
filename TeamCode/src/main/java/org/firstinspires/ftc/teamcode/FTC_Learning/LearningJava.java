package org.firstinspires.ftc.teamcode.FTC_Learning;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LearningJava extends OpMode{
    int gradeForGradeSeven = 98;
    void robotSpeed(){
        double speed = -1.0 * gamepad1.left_stick_y;
        telemetry.addData("Bot speed is: " , speed);
    }
    void triggersAdded(){
        double trigger1 = gamepad1.left_trigger;
        double trigger2 = gamepad1.right_trigger;
        double triggersTogether = trigger1 + trigger2;
        telemetry.addData("Triggers added is: " , triggersTogether);
    }
    void turtleMode(){

        while(gamepad1.a){
            double gp1StickYTurtle = gamepad1.right_stick_y/2.0;
            double gp1StickXTurtle = gamepad1.right_stick_x/2.0;
            telemetry.addLine("Turtle active");
            telemetry.addData("GP1 Y = " , gp1StickYTurtle);
            telemetry.addData("GP1 X = " , gp1StickXTurtle);

        }
    }

    @Override
    public void init(){
        telemetry.addLine("Hello World!");
    }
    @Override
    public void start(){
        telemetry.addLine("Starting Opmode");
        telemetry.addData("Your Grade Is: " , gradeForGradeSeven);
    }

    @Override
    public void loop(){
    robotSpeed();
    triggersAdded();
    turtleMode();
        telemetry.addData("Right stick X GP1 = " , gamepad1.right_stick_x);
        telemetry.addData("Right stick Y GP1 = " , gamepad1.right_stick_y);
        telemetry.addData("Diff between GP1 left and right joystick y is " , gamepad1.left_stick_y - gamepad1.right_stick_y);
        if (gamepad1.b){
            telemetry.addLine("B is pressed");
        }
    }
}
