package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Climber;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ReleaseClimber extends Command {
	private static final int TIMEOUT = 1;

	Servo servo4;
	Servo servo5;
	
	@Override
	protected void end() {
		servo4.setAngle(Climber.LEFT_SERVO_CLOSED);
		servo5.setAngle(Climber.RIGHT_SERVO_CLOSED);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		servo4 = RobotMap.pwmServo_4;
		servo5 = RobotMap.pwmServo_5;
		servo4.setAngle(Climber.LEFT_SERVO_OPEN);
		servo5.setAngle(Climber.RIGHT_SERVO_OPEN);
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
