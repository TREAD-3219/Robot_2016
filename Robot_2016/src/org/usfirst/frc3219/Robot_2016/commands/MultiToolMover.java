package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiToolMover extends NeverFinishCommand {
	private static final double ENCODER_ANGLE_TOLERANCE = 5.0;
	private static final double MINIMUM_UP_SPEED = -0.1;
	private static final double MINIMUM_DOWN_SPEED = 0.1;
	private static final double SPEED_TOLERENCE = 0.1;
	private static final double ARM_STOP_SPEED = 0.0;
	Joystick gameController;
	double speed = 0.0;
	boolean setpointSet = false;
	double setPointAngle = 0;

	public MultiToolMover() {
		requires(Robot.multiTool);
		this.setInterruptible(false);
	}

	@Override
	protected void initialize() {
		gameController = Robot.oi.gameController;
		speed = gameController.getY();
		setpointSet = false;
	}

	@Override
	protected void execute() {

		// consider a better scaling algorithm here... cubic?
		// also, perhaps asymmetric? i.e., bigger up than down.
		speed = gameController.getY() * 0.5 * -1.0;

		if (Math.abs(speed) > SPEED_TOLERENCE) {
			// add a null zone to the controller
			if (speed > MINIMUM_UP_SPEED || speed < MINIMUM_DOWN_SPEED) {
				Robot.multiTool.driveArmUpDown(speed);
			} else {
				Robot.multiTool.driveArmUpDown(0);
			}
			setpointSet = false;
		} else {
			if(!setpointSet) {
				setPointAngle = Robot.sensors.armEncoderAngle();
				setpointSet = true;
			} else {
				double delta = Robot.sensors.armEncoderAngle() - setPointAngle;
				if(Math.abs(delta) >= ENCODER_ANGLE_TOLERANCE) {
					if(delta > 0.0) {
						Robot.multiTool.driveArmUpDown(0.2); // Go back up!
					} else {
						Robot.multiTool.driveArmUpDown(0.0); // Let it sag!
					}
				}
			}
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
