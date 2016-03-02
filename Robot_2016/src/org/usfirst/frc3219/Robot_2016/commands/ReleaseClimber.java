package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ReleaseClimber extends Command {
	private static final int TIMEOUT = 1;
	private static final float RIGHT_SERVO_CLOSED = 180.0f;
	private static final float LEFT_SERVO_CLOSED = 0.0f;
	private static final float RIGHT_SERVO_OPEN = 0.0f;
	private static final float LEFT_SERVO_OPEN = 180.0f;
	Servo servo4;
	Servo servo5;
	
	@Override
	protected void end() {
		servo4.setAngle(LEFT_SERVO_OPEN);
		servo5.setAngle(RIGHT_SERVO_OPEN);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		servo4 = RobotMap.pwmServo_4;
		servo5 = RobotMap.pwmServo_5;
		servo4.setAngle(LEFT_SERVO_CLOSED);
		servo5.setAngle(RIGHT_SERVO_CLOSED);
		this.setTimeout(TIMEOUT);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}
	
}
