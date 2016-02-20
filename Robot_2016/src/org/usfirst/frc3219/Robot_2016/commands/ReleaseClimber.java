package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ReleaseClimber extends Command {
	Servo servo4;
	Servo servo5;
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		servo4 = RobotMap.pwmServo_4;
		servo5 = RobotMap.pwmServo_5;
		servo4.setAngle(0.0f);
		servo5.setAngle(180.0f);
		this.setTimeout(1);
		
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
