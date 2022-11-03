// This program is written by Torben Bergan, date November 3, time 08:35

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Main Testing Teleop", group="Linear Opmode")

public class DriveTestingTB extends LinearOpMode {

        // Motor imports and encoding
    private DcMotorEx LA;
    private DcMotorEx LF;
    private DcMotorEx RA;
    private DcMotorEx RF;

    @Override
    public void runOpMode() { // Main OpMode
        telemetry.addData("Elapsed Time", "Initialized");
        telemetry.update();

            // Getting the configuration from Robot Controller
        LA  = hardwareMap.get(DcMotorEx.class, "moto0");
        LF  = hardwareMap.get(DcMotorEx.class, "moto1");
        RA  = hardwareMap.get(DcMotorEx.class, "moto2");
        RF  = hardwareMap.get(DcMotorEx.class, "moto3");
            // ----------- Reset Encoders before Use ---------//
        LA.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RA.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        RF.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            // -------- Configure Encoders after Reset -------//
        LA.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LF.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RA.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        



            // Telemetry Data for Debuging.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("LA Power", LA.getPower());
        telemetry.addData("LF Power", LF.getPower());
        telemetry.addData("RA Power", RA.getPower());
        telemetry.addData("RF Power", RF.getPower());
        telemetry.addData("LA Position", LA.getCurrentPosition());
        telemetry.addData("LF Position", LF.getCurrentPosition());
        telemetry.addData("RA Position", RA.getCurrentPosition());
        telemetry.addData("RF Position", RF.getCurrentPosition());
        telemetry.update();

}