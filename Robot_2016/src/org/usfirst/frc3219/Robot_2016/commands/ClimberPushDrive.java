package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberPushDrive extends Command {

	CANTalon leftFront = RobotMap.driveLeftDriveB;
	CANTalon leftBack = RobotMap.driveLeftDriveA;
	CANTalon rightFront = RobotMap.driveRightDriveB;
	CANTalon rightBack = RobotMap.driveRightDriveA;
	
	@Override
	protected void initialize() {
		Robot.drive.takeOver(true);
		
	}

	@Override
	protected void execute() {
		boolean leftPressed = Robot.oi.leftClimb.get();
		boolean rightPressed = Robot.oi.rightClimb.get();
		if(leftPressed && rightPressed) {
			leftFront.set(-1.0);
			leftBack.set(-1.0);
			rightFront.set(1.0);
			rightBack.set(1.0);
		} else if(leftPressed) {
			leftFront.set(-1.0);
			leftBack.set(-1.0);
			rightFront.set(0.0);
			rightBack.set(0.0);
		} else if(rightPressed) {
			leftFront.set(0.0);
			leftBack.set(0.0);
			rightFront.set(1.0);
			rightBack.set(1.0);
		} else {
			return; //AH!
		}

	}

	@Override
	protected boolean isFinished() {
		boolean leftPressed = Robot.oi.leftClimb.get();
		boolean rightPressed = Robot.oi.rightClimb.get();
		return !(leftPressed && rightPressed);
	}

	@Override
	protected void end() {
		Robot.drive.takeOver(false);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

}
