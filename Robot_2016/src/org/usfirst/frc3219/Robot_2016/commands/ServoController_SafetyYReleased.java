package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ServoController_SafetyYReleased extends Command {
	public static double safetyYPressed_value;
	//RELEASED RELEASED RELEASED RELEASED
	//Y Y Y Y Y Y Y Y
	@Override
	protected void end() {

	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		if(safetyYPressed_value == 0) {
			System.out.print("the button Y was released while released. The value is " + safetyYPressed_value);
			end();
			
		}else {
			safetyYPressed_value = 0 ;
			end();
		}
		
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
