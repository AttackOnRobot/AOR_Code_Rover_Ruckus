package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "teleop-pose")
public class Teleop extends RobotHardware {
    double speedFactor = 1.00;

    @Override
    public void runOpMode() {
        initHardware();
        setMotorRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double armPower = 0, goalPower = 0;
        waitForStart();
        //could use nano time but it's more cpu intensive and also milliseconds are accurate enough for us
        long startTime = System.currentTimeMillis();
        long lastTime = startTime;
        long elapsedTime = 0, totalElapsedTime = 0, currentTime = 0;
        //milliseconds to accelerate to full power
        long timeToMaxPower = 300;
        double accelPerMil = 1.0 / timeToMaxPower;
        while (opModeIsActive()) {

            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastTime;
            lastTime = currentTime;
            //speed factor is a multplier
            if( gamepad2.x ) speedFactor = 0.75;
            else speedFactor = 1.00;
            /*current arm power is total increase in speed (ie delta v)
            we want to limit acceleration (ie delta v / t)
            thus we want to limit change in arm power over time
            thus we want to multiply the milliseconds elapsed by the goal acceleration/ millisecond
            also scaled by the difference between the goal power and the current power
             */
            armPower = armPower + accelPerMil * elapsedTime * (gamepad2.left_stick_y - armPower);

            }
            if( armPower > 1.0) armPower = 1.0;
            else if( armPower < -1.0) armPower = -1.0;
            else if( Math.abs(armPower) < 0.05 && Math.abs(gamepad2.left_stick_y) < 0.05 ) armPower = 0;
            //update telemetry
            telemetry.addData("armPower (inactive)", armPower);
            telemetry.update();
            //set motors to powers
            armMotor.setPower( gamepad2.left_stick_y * 0.75 * speedFactor );
            leftDriveMotor.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
            rightDriveMotor.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
    }
}
