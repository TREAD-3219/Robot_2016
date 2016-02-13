package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ServoController_SafetyStartReleased extends Command {
	public static double safetyStartPressed_value;
	//RELEASED RELEASED RELEASED RELEASED 
	//START START START START START
	@Override
	protected void end() {

	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		if(safetyStartPressed_value == 1) {
			System.out.print("the button Start was pressed while pressed. The value is " + safetyStartPressed_value);
			end();
			
		}else {
			 safetyStartPressed_value = 1;
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
