package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class RobotHardware extends LinearOpMode {
    DcMotor leftDriveMotor = null;
    DcMotor rightDriveMotor = null;
    DcMotor armMotor = null;

    protected void configMotor(DcMotor m, DcMotor.Direction d, DcMotor.RunMode runMode) {
        m.resetDeviceConfigurationForOpMode();
        m.setMode(runMode);
        m.setDirection(d);
        m.setPower(0.0);
    }


    protected void initHardware() {
        leftDriveMotor = hardwareMap.dcMotor.get("left");
        rightDriveMotor = hardwareMap.dcMotor.get("right");
        armMotor = hardwareMap.dcMotor.get("arm");
    }
    protected void setMotorRunMode(DcMotor.RunMode runMode) {
        configMotor(leftDriveMotor, DcMotorSimple.Direction.FORWARD, runMode);
        configMotor(rightDriveMotor, DcMotorSimple.Direction.REVERSE, runMode);
        configMotor(armMotor, DcMotorSimple.Direction.FORWARD, runMode);
    }
}
