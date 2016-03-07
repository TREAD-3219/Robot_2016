package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends NeverFinishCommand {

	static double reverse = -1.0;

	Joystick driveStick = null;
	double lastLeftEncoder;
	double lastRightEncoder;

	public static void reverse() {
		reverse *= -1.0;
	}

	public JoystickDrive() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {

		driveStick = Robot.oi.joystick; // Renames the joystick to driveStick

		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		// NOTE: do NOT change reverse in initialize!
		// we want it to remain in the same setting even if
		// JoystickDrive is interrupted and then restarts
	}

	@Override
	protected void execute() {
		double rawFwd = driveStick.getY();
		double rawTurn = driveStick.getX();
		// get the value from the throttle of the joystick
		double speedScale = driveStick.getThrottle();
		// Make the plus on the throttle actually make the value higher instead
		// of lower. Labeling on the joystick now makes sense.
		double correctSpeedScale = (speedScale - 1) / -2;

		// swap front and rear end by multiply by 1 or -1
		double correctFwd = rawFwd * correctSpeedScale * reverse;
		double correctTurn = rawTurn * correctSpeedScale * reverse;

		// Navigation stuffs
		double newLeftDist = RobotMap.driveEncoderLeft.getDistance() - lastLeftEncoder;
		double newRightDist = RobotMap.driveEncoderRight.getDistance() - lastRightEncoder;
		double avgDist = (newLeftDist + newRightDist) / 2;
		Robot.navigation.dedRecMoved(avgDist);
		double degrees = 2 * (newLeftDist - newRightDist);
		Robot.navigation.dedRecTurned(degrees);
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		// end Navigation stuffs

		Robot.drive.driveValues(correctFwd, correctTurn);
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
