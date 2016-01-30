package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends Command {

	Joystick driveStick = null;

	public JoystickDrive() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveStick = Robot.oi.joystick;
	}

	@Override
	protected void execute() {
		if (driveStick != null) {
			double rawFwd = driveStick.getY();
			double rawTurn = driveStick.getZ();
			double speedScale = driveStick.getThrottle();

			Robot.drive.driveValues(rawFwd * speedScale, rawTurn * speedScale);
			SmartDashboard.putNumber("Forward", rawFwd * speedScale);
			SmartDashboard.putNumber("Turn", rawTurn * speedScale);
			SmartDashboard.putNumber("Speed Scale", speedScale);
		}

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
		Robot.drive.driveValues(0.0, 0.0);

	}
}
