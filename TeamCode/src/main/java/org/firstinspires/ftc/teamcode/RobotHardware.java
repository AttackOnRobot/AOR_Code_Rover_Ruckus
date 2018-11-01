package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class RobotHardware extends LinearOpMode {
    DcMotor leftDriveMotor = null;
    DcMotor rightDriveMotor = null;
    DcMotor winchMotor = null;
    Servo hookServo = null;
   // DcMotor armMotor = null;

    protected void configMotor(DcMotor m, DcMotor.Direction d, DcMotor.RunMode runMode) {
        m.resetDeviceConfigurationForOpMode();
        m.setMode(runMode);
        m.setDirection(d);
        m.setPower(0.0);
    }


    protected void initHardware() {
        leftDriveMotor = hardwareMap.dcMotor.get("left");
        rightDriveMotor = hardwareMap.dcMotor.get("right");
        winchMotor = hardwareMap.dcMotor.get("winch");
        configMotor(winchMotor, DcMotorSimple.Direction.FORWARD, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hookServo = hardwareMap.Servo.get("hook");
        //armMotor = hardwareMap.dcMotor.get("arm");
    }
    protected void setMotorRunMode(DcMotor.RunMode runMode) {
        configMotor(leftDriveMotor, DcMotorSimple.Direction.FORWARD, runMode);
        configMotor(rightDriveMotor, DcMotorSimple.Direction.REVERSE, runMode);
        //configMotor(armMotor, DcMotorSimple.Direction.FORWARD, runMode);
    }
}
