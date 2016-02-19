package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {

	Joystick driveStick = null;
	double lastLeftEncoder;
	double lastRightEncoder;
	public JoystickDrive() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveStick = Robot.oi.joystick; // Renames the joystick to 
		//lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		//lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
	}

	@Override
	protected void execute() {
//		if (driveStick != null) {
		double rawFwd = driveStick.getY(); // give an extra name to the "Y" value of the joyStick
		double rawTurn = driveStick.getX(); // Give and extra name to the "X" value of the joyStick
			double speedScale = (driveStick.getThrottle()+1)/-2;  // get the value from the throttle of the joystick
			//double correctSpeedScale = speedScale; // Make the plus on the throttle actually make the value higher instead of lower. Labeling on the joystick now makes sense.
			double correctFwd = rawFwd * speedScale * -1; // Make the motors go in the correct direction instead of going backwards, and use the scale of the throttle
			double correctTurn = rawTurn * speedScale; // keep the turning direction of the motors, and make the turn use the scale of the throttle
//			//Navigation stuffs
//			double newLeftDist = RobotMap.driveEncoderLeft.getDistance() - lastLeftEncoder;
//			double newRightDist = RobotMap.driveEncoderRight.getDistance() - lastRightEncoder;
//			double avgDist = (newLeftDist + newRightDist) / 2;
//			Navigation.deadRecMoved(avgDist);
//			double degrees = 2 * (newLeftDist - newRightDist);
//			Navigation.deadRecTurned(degrees);
//			lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
//			lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
//			//end Navigation stuffs
			Robot.drive.driveValues(correctFwd, correctTurn); 
//			//Show the throttle values on the dashboard
//			//-----------------------------------------------------------
//			SmartDashboard.putNumber("Forward", rawFwd * speedScale);// | 
//			SmartDashboard.putNumber("Turn", rawTurn * speedScale);//   |
//			SmartDashboard.putNumber("Speed Scale", speedScale);//      |
//			//-----------------------------------------------------------
//		}


	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0); // stops the motors

	}
}
