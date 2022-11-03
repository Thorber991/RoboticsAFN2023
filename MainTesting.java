/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

// This program is written by Torben Bergan, date September 20, time 10:03

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Main Testing Teleop", group="Linear Opmode")

public class MainTesting extends LinearOpMode {

    // Main drive train motors
    private DcMotorEx LA;
    private DcMotorEx LF;
    private DcMotorEx RA;
    private DcMotorEx RF;

    // Aux Motor outputs
        // 4 available options

    @Override
    public void runOpMode() { // Main OpMode
        telemetry.addData("Elapsed Time", "Initialized");
        telemetry.update();

        // Getting the configuration from Robot Controller
        LA  = hardwareMap.get(DcMotorEx.class, "moto0");
        LF  = hardwareMap.get(DcMotorEx.class, "moto1");
        RA  = hardwareMap.get(DcMotorEx.class, "moto2");
        RF  = hardwareMap.get(DcMotorEx.class, "moto3");
        // -----------------------------------------------//
        LA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // The motors are flipped, therefore one side's "forward" is backward relative to the robot
        // Reverse the motor that runs backwards when connected directly to the battery
        LA.setDirection(DcMotor.Direction.FORWARD);
        LF.setDirection(DcMotor.Direction.FORWARD);
        RA.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.REVERSE);
        // -----------------------------------------------//

        // Wait for the game to start
        waitForStart();
        runtime.reset();

        // Runs until the end of the match
        while (opModeIsActive()) {

            // Driver inputs, controls driving and turning.
            if (gamepad1.left_bumper) { // Sets left side reverse when bumper is pressed
                LA.setPower(-1);
                LF.setPower(-1);
            }
            if (gamepad1.right_bumper) { // Sets right side reverse when bumper is pressed
                RA.setPower(-1);
                RF.setPower(-1);
            }
            // Sets the motor power equal to the value outputed by pushing the triggers on the Controller, forward motion only
            LA.setPower(gamepad1.left_trigger);
            LF.setPower(gamepad1.left_trigger);
            RA.setPower(gamepad1.right_trigger);
            RF.setPower(gamepad1.right_trigger);


            // Aux inputs, controls control arm, pickup and other auxiallry options/features.

            // Show the elapsed game time and other necessary information for debugging purposes.
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
    }
}