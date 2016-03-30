package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiToolMover extends NeverFinishCommand {
	private static final double MINIMUM_UP_SPEED = -0.1;
	private static final double MINIMUM_DOWN_SPEED = 0.1;
	private static final double SPEED_TOLERENCE = 0.1;
	private static final double ARM_STOP_SPEED = 0.0;
	Joystick gameController;
	double speed = 0.0;
	boolean setpointSet = false;

	public MultiToolMover() {
		requires(Robot.multiTool);
		this.setInterruptible(false);
	}

	@Override
	protected void initialize() {
		gameController = Robot.oi.gameController;
		speed = gameController.getY();
		Robot.multiTool.disable();
		setpointSet = false;
	}

	@Override
	protected void execute() {

		// consider a better scaling algorithm here... cubic?
		// also, perhaps asymmetric? i.e., bigger up than down.
		speed = gameController.getY() * 0.5;

		if (Math.abs(speed) > SPEED_TOLERENCE) {
			// add a null zone to the controller
			if ((speed > MINIMUM_DOWN_SPEED && !Robot.multiTool.getLowerLimitSwitch())
					|| (speed < MINIMUM_UP_SPEED && !Robot.multiTool.getUpperLimitSwitch())) {
				Robot.multiTool.driveArmUpDown(speed);
				setpointSet = false;
			} else {
				if (!setpointSet) {
					Robot.multiTool.driveArmHold();
					setpointSet = true;
				}
			}
		} else if (!setpointSet) {
			Robot.multiTool.driveArmHold();
			setpointSet = true;
		}
	}

	// non-interruptible, these will never be called:
	@Override
	protected void interrupted() {
	}

	@Override
	protected void end() {
	}
}
