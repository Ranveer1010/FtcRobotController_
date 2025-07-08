package org.firstinspires.ftc.teamcode.FTC_Learning;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LearningJava extends OpMode{
    int gradeForGradeSeven = 98;

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
        double speed = gamepad1.left_stick_y / 2.0;
        telemetry.addData("Right stick X GP1 = " , gamepad1.right_stick_x);
        telemetry.addData("Right stick Y GP1 = " , gamepad1.right_stick_y);
        telemetry.addData("Diff between GP1 left and right joystick y is " , gamepad1.left_stick_y - gamepad1.right_stick_y);
        telemetry.addData("Sum of GP1 two triggers is " , gamepad1.left_trigger + gamepad1.right_trigger);
        telemetry.addData("Speed is ", speed);
        if (gamepad1.b){
            telemetry.addLine("B is pressed");
        }
        if(gamepad1.a){
            gamepad1.right_stick_y = gamepad1.right_stick_y/1;
            gamepad1.right_stick_x = gamepad1.right_stick_x/1;
        }
        else{
            gamepad1.right_stick_y = gamepad1.right_stick_y/2;
            gamepad1.right_stick_x = gamepad1.right_stick_x/2;
        }
        //crazy mode:
        if(gamepad1.left_bumper){
            gamepad1.left_stick_y = gamepad1.left_stick_x;
            gamepad1.left_stick_x = gamepad1.left_stick_y;
            gamepad1.right_stick_x = gamepad1.right_stick_y;
            gamepad1.right_stick_y = gamepad1.right_stick_x;
        }
    }
}
