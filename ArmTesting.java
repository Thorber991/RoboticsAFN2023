package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


/*
 * Servo;
   ToDo 1 Catcher       // A and B Buttons
   ToDo 2 Platform flip // Bumpers
   ToDo 1 Recovery      // X or Y Buttons
 * 
 * Mapping Gamepad 2;
   ToDo D-Pad Buttons -- Preset Height //? Complete
   ToDo Misc Buttons  -- Arm Controls  //? Complete
   ToDo Right Stick   -- Manual Height //? Complete
   ToDo Bumpers       -- Platform Flip //? Complete
 * 
 */


@TeleOp(name="Arm Testing", group="Arm")
public class ArmTesting extends LinearOpMode {

		// Arm Motor Imports
	private DcMotorEx LiftL;
	private DcMotorEx LiftR;
	private Servo catcher;
	private Servo platL;
	private Servo platR;
	private Servo recoverCone;
	private final int height1 = 0;
	private final int height2 = 0;
	private final int height3 = 0;
	private final int stdHeight = 0;

	public void runOpMode() {
		
		LiftL = hardwareMap.get(DcMotorEx.class, "LiftL");
		LiftR = hardwareMap.get(DcMotorEx.class, "LiftR");
		catcher = hardwareMap.get(Servo.class, "serv0");
		platL = hardwareMap.get(Servo.class, "serv1");
		platR = hardwareMap.get(Servo.class, "serv2");
		recoverCone = hardwareMap.get(Servo.class, "serv3");
			// Resetting Encoders
		LiftL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
		LiftR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
		LiftL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
		LiftR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
			// Setting the Direction
		LiftR.setDirection(DcMotorEx.Direction.REVERSE);
		platR.setDirection(Servo.Direction.REVERSE);



		waitForStart();
		while(opModeIsActive()){
			
				
			if(gamepad2.right_trigger == 1) { //* Manual Arm Controls with Safety to prevent accidental discharge
				LiftL.setPower(gamepad2.left_stick_y/2);
				LiftR.setPower(gamepad2.left_stick_y/2);
			}
			
			if(gamepad2.dpad_up) { //* Preset Heights Controls
				LiftL.setTargetPosition(height3);
				LiftR.setTargetPosition(height3);
			} else if(gamepad2.dpad_left) {
				LiftL.setTargetPosition(height1);
				LiftR.setTargetPosition(height1);
			} else if(gamepad2.dpad_right) {
				LiftL.setTargetPosition(height2);
				LiftR.setTargetPosition(height2);
			} else if(gamepad2.dpad_down) {
				LiftL.setTargetPosition(stdHeight);
				LiftR.setTargetPosition(stdHeight);
			}
			
			if(gamepad2.a) {
				catcher.setPosition(0);
			} else if(gamepad2.b) {
				catcher.setPosition(1);
			}

			if(gamepad2.right_bumper && platL.getPosition() == 0) { //* Platform Flip Mechanism. Gotta Test and Fix. Unsure.
				platL.setPosition(1);
				platR.setPosition(1);
			} else if(gamepad2.right_bumper && platL.getPosition() == 1) {
				platL.setPosition(0);
				platR.setPosition(0);
			}

			if(gamepad2.y && recoverCone.getPosition() == 0) {
				recoverCone.setPosition(1);
			} else if(gamepad2.y && recoverCone.getPosition() == 1) {
				recoverCone.setPosition(0);
			}

			
			telemetry.addData("Robot", "OpMode Started");
			telemetry.addData("Arm Status", "At Rest");
			telemetry.addData("Left Motor Power", LiftL.getPower());
			telemetry.addData("Left Motor Position", LiftL.getCurrentPosition());
			telemetry.addData("Right Motor Power", LiftR.getPower());
			telemetry.addData("Right Motor Position", LiftR.getCurrentPosition());
			telemetry.addData("Catcher Position", catcher.getPosition());
			telemetry.addData("PlatL Position", platL.getPosition());
			telemetry.addData("PlatR Position", platR.getPosition());
			//telemetry.addData("Cone Recovery Position", recoverCone.getCurrentPosition());
			telemetry.update();
			
		}
	}
}
