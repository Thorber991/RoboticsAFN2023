package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Gyroscope;*/
import com.qualcomm.robotcore.hardware.DcMotorEx;



@SuppressWarnings("ALL")
@TeleOp(name="Drivetrain", group="Drivetrain")
public class DriveTrain extends LinearOpMode {
		// Misc
	/*private Blinker control_Hub;
	private HardwareDevice webcam_1;
	private Gyroscope imu;*/
		
		// Motors
	private DcMotorEx LA;
	private DcMotorEx LF;
	private DcMotorEx RA;
	private DcMotorEx RF;

		// Mechanum Wheel Variables
	public double sin;
	public double cos;
	public double max;
	public double theta;
	public double power;
	private final double x_stick = gamepad1.left_stick_x;
	private final double y_stick = gamepad1.left_stick_y;
	public double turn = gamepad1.right_stick_x;

	private double leftFront;
	private double leftRear;
	private double rightFront;
	private double rightRear;




	@Override
	public void runOpMode() {
			// Configuring
		LA = hardwareMap.get(DcMotorEx.class, "moto0");
		LF = hardwareMap.get(DcMotorEx.class, "moto1");
		RA = hardwareMap.get(DcMotorEx.class, "moto2");
		RF = hardwareMap.get(DcMotorEx.class, "moto3");
			// Reversing necessary motors
		RA.setDirection(DcMotorEx.Direction.REVERSE);
		RF.setDirection(DcMotorEx.Direction.REVERSE);
			// Resetting encoders
		LA.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		LF.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		RA.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		RF.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		
			// Waiting for game start
		waitForStart();
		while(opModeIsActive()) {
			
				// Input Theta and Power
				// Mechanum Wheel movement controls. Test math
			theta = Math.atan2(y_stick, x_stick);
			power = Math.hypot(x_stick, y_stick);
			
			sin = Math.sin(theta - Math.PI/4);
			cos = Math.cos(theta - Math.PI/4);
			max = Math.max(Math.abs(sin), Math.abs(cos));

			leftFront  = power * cos/max + turn;
			leftRear   = power * cos/max + turn;
			rightFront = power * cos/max - turn;
			rightRear  = power * cos/max - turn;

			if((power +Math.abs(turn) > 1)) {
				leftFront  /= power + turn;
				leftRear   /= power + turn;
				rightFront /= power + turn;
				rightRear  /= power + turn;
			}

			LF.setPower(leftFront);
			LA.setPower(leftRear);
			RF.setPower(rightFront);
			RA.setPower(rightRear);
			
				// Telemetry Updates
			telemetry.addData("Sin", sin);
			telemetry.addData("Cos", cos);
			telemetry.addData("Max", max);
			telemetry.addData("Theta", theta);
			telemetry.addData("Power", power);
			telemetry.addData("LA", LA.getPower());
			telemetry.addData("LF", LF.getPower());
			telemetry.addData("RA", RA.getPower());
			telemetry.addData("RF", RF.getPower());
			telemetry.update();
		}
	}
}
