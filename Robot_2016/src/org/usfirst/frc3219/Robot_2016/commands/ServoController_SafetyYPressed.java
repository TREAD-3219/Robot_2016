package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ServoController_SafetyYPressed extends Command {
	public double safetyYPressed_value;
	//PRESS PRESS PRESS PRESS
	//Y Y Y Y Y Y Y Y
	@Override
	protected void end() {

	}
	
	@Override
	protected void execute() {
	
	}

	@Override
	protected void initialize() {
		if(safetyYPressed_value == 1) {
			System.out.print("the button Y was pressed while pressed. The value is " + safetyYPressed_value);
			end();
			
		}else {
			safetyYPressed_value = 1;
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
