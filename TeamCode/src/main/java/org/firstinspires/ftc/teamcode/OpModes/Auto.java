package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Canvas;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.Mechanisms.Config;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class Auto extends LinearOpMode {
    Config config = new Config();

    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime timer = new ElapsedTime();


        waitForStart();
        timer.reset();

        config.init(hardwareMap);
        WebcamName camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        AprilTagProcessor aprilTagProcessor = new AprilTagProcessor.Builder().setDrawTagID(true).setDrawTagOutline(true).setDrawAxes(true).setLensIntrinsics(280.495, 280.495, 319.995, 239.986).build();

        VisionPortal visionPortal =
                VisionPortal.easyCreateWithDefaults(camera, aprilTagProcessor);

        while (!isStopRequested() && opModeIsActive()) {
            if (timer.time() < 1) {
                config.setAllDrivePower(-0.1, -0.1);
            } else if (timer.time() < 3) {
                config.setAllDrivePower(0.0, 0.0);
            } else if (timer.time() > 3 && timer.time() < 10) {
                List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
                if (!detections.isEmpty()) {
                    int id = detections.get(0).id;
                    AprilTagPoseFtc tagPose = detections.get(0).ftcPose;
                    telemetry.addData("Id ", id);
                    telemetry.addData("Yaw ", tagPose.yaw);
                    telemetry.addData("Roll ", tagPose.roll);
                    telemetry.addData("Pitch ", tagPose.pitch);
                    telemetry.addData("Bearing ", tagPose.bearing);
                    telemetry.addData("Distance ", tagPose.range);

                    if (Math.abs(detections.get(0).ftcPose.bearing) < 2) {
                        config.setAllDrivePower(0.0, 0.0);
                    } else {
                        config.setAllDrivePower(-0.05, 0.05);
                    }
                }else{
                    config.setAllDrivePower(-0.05,0.05);
                }
            }
            telemetry.update();
            sleep(1000);
        }
    }
}

