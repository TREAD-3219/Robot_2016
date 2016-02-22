package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends NeverFinishCommand {

	Joystick driveStick = null;
	double lastLeftEncoder;
	double lastRightEncoder;

	public JoystickDrive() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		driveStick = Robot.oi.joystick; // Renames the joystick to
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
	}

	@Override
	protected void execute() {
		// give an extra name to the "Y" value of the joyStick
		double rawFwd = driveStick.getY();
		// Give and extra name to the "X" value of the joyStick
		double rawTurn = driveStick.getX();
		// get the value from the throttle of the joystick
		double speedScale = driveStick.getThrottle();
		// Make the plus on the throttle actually make the value higher instead
		// of lower. Labeling on the joystick now makes sense.
		double correctSpeedScale = (speedScale - 1) / -2;
		// Make the motors go in the correct direction instead of going
		// backwards, and use the scale of the throttle
		double correctFwd = rawFwd * correctSpeedScale;
		// keep the turning direction of the motors, and make the turn use the
		// scale of the throttle
		double correctTurn = rawTurn * correctSpeedScale;

		// Navigation stuffs
		double newLeftDist = RobotMap.driveEncoderLeft.getDistance() - lastLeftEncoder;
		double newRightDist = RobotMap.driveEncoderRight.getDistance() - lastRightEncoder;
		SmartDashboard.putNumber(Sensors.LEFT_ENCODER_TAG, RobotMap.driveEncoderLeft.getDistance());
		SmartDashboard.putNumber(Sensors.RIGHT_ENCODER_TAG, RobotMap.driveEncoderRight.getDistance());

		// move this to Navigation
		// into dedRecMoved(leftDist, rightDist)
		// or just dedRecUpdate(), and have Navigation read encoders.

		double avgDist = (newLeftDist + newRightDist) / 2;
		Robot.navigation.dedRecMoved(avgDist);

		double degrees = 2 * (newLeftDist - newRightDist); // hunh? is this
															// right?
		// don't you need sines and stuff to convert triangle side lengths into
		// angles?
		Robot.navigation.dedRecTurned(degrees);
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		// end Navigation stuffs

		Robot.drive.driveValues(correctFwd, correctTurn);

		// //Show the throttle values on the dashboard
		// //-----------------------------------------------------------
		// SmartDashboard.putNumber("Forward", rawFwd * speedScale);// |
		// SmartDashboard.putNumber("Turn", rawTurn * speedScale);// |
		// SmartDashboard.putNumber("Speed Scale", speedScale);// |
		// //-----------------------------------------------------------
		// }

	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0); // stops the motors
	}
}
