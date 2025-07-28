package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TouchSensorConfig {
    private DigitalChannel touchSensor;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor"); //says to look for this whenever touch sensor is called
        touchSensor.setMode(DigitalChannel.Mode.INPUT); //sets the touch sensor as an input
    }
    public boolean isTouchSensorPressed(){
        if(!touchSensor.getState()){
            return true;
        }
        return false;
    }
}
