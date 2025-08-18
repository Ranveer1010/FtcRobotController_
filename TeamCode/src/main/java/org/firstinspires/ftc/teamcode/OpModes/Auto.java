package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Mechanisms.Config;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.openftc.easyopencv.OpenCvCamera;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.List;

@Autonomous
public class Auto extends LinearOpMode {
    OpenCvCamera cam;
    WebcamName camera = hardwareMap.get(WebcamName.class , "Webcam 1");
    double extra = 1.0;
    Config config = new Config();
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        config.init(hardwareMap);

        AprilTagProcessor aprilTagProcessor =
        AprilTagProcessor.easyCreateWithDefaults();
        VisionPortal visionPortal =
        VisionPortal.easyCreateWithDefaults(camera , aprilTagProcessor);

        while (!isStopRequested() && opModeIsActive()) {
            List<AprilTagDetection> detections = aprilTagProcessor.getDetections();

            for(AprilTagDetection detection : detections){
                int id = detection.id;
                AprilTagPoseFtc tagPose = detection.ftcPose;
                telemetry.addData("Id " , id);
                telemetry.addData("Yaw " , tagPose.yaw);
                telemetry.addData("Roll " , tagPose.roll);
                telemetry.addData("Pitch " , tagPose.pitch);
                telemetry.addData("Bearing " , tagPose.bearing);
                telemetry.addData("Distance " , tagPose.range);
            }
            if(!detections.isEmpty()){
                AprilTagDetection detection = detections.get(0);
                double tagBearing0 = detection.ftcPose.bearing;

                if(Math.abs(tagBearing0) > extra){
                    config.setAllDrivePower(0.1, 0.4);
                }else{
                    config.setAllDrivePower(0.0,0.0);
                    sleep(50);
                    config.setClaw(0.5);
                    sleep(5);
                    config.setPinchOpen();
                    sleep(5);
                    config.setPinchClose();
                }

            }
            telemetry.update();
            sleep(50);
        }
    }
}