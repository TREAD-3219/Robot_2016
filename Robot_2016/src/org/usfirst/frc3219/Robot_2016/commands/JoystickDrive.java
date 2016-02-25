package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends Command {
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
		driveStick = Robot.oi.joystick; // Renames the joystick to
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		// NOTE: do NOT change reverse in initialize!
		// we want it to remain in the same setting even if
		// JoystickDrive is interrupted and then restarts
	}

	@Override
	protected void execute() {

		double rawFwd = driveStick.getY() * reverse; // give an extra name to the "Y" value of the joyStick
		double rawTurn = driveStick.getX() * reverse; // Give and extra name to the "X" value of the joyStick
			double speedScale = driveStick.getThrottle();  // get the value from the throttle of the joystick
			double correctSpeedScale = (speedScale - 1.0) / -2.0; // Make the plus on the throttle actually make the value higher instead of lower. Labeling on the joystick now makes sense.
			double correctFwd = rawFwd * correctSpeedScale; // Make the motors go in the correct direction instead of going backwards, and use the scale of the throttle
			double correctTurn = rawTurn * correctSpeedScale; // keep the turning direction of the motors, and make the turn use the scale of the throttle
			//Navigation stuffs
			double newLeftDist = RobotMap.driveEncoderLeft.getDistance() - lastLeftEncoder;
			double newRightDist = RobotMap.driveEncoderRight.getDistance() - lastRightEncoder;
			SmartDashboard.putNumber("Raw Left Encoder", RobotMap.driveEncoderLeft.getDistance());
			SmartDashboard.putNumber("Raw Right Encoder", RobotMap.driveEncoderRight.getDistance());
			double avgDist = (newLeftDist + newRightDist) / 2.0;

			Navigation.deadRecMoved(avgDist);
			double degrees = 2 * (newLeftDist - newRightDist);
			Navigation.deadRecTurned(degrees);
			lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
			lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
			//end Navigation stuffs

			Robot.drive.driveValues(correctFwd, correctTurn); 

//			//Show the throttle values on the dashboard
//			//-----------------------------------------------------------
//			SmartDashboard.putNumber("Forward", rawFwd * speedScale);// | 
//			SmartDashboard.putNumber("Turn", rawTurn * speedScale);//   |
//			SmartDashboard.putNumber("Speed Scale", speedScale);//      |
//			//-----------------------------------------------------------
//		}

		// end Navigation stuffs
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0); // stops the motors
	}
}
