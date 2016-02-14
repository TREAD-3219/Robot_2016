package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoController extends Command {

	boolean	quickReleaseOverride = false;
	double PwmServo4StartValue = 50.0; //use this to set the start value
	double PwmServo4ValueMax = 2000;
	double PwmServo4ValueMin = 50;
	double PwmServo4Value = PwmServo4StartValue;
	
	//button press values

	// how to set up a timer
	//http://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java
	// TODO set up timer

	@Override
	protected void end() {

	}

	@Override
	protected void execute() {
		SmartDashboard.getBoolean("quick release overide");
		
		if (quickReleaseOverride == true)
				{
			//For manual setting of servo 1 and some safety for setting wrong values ----------------------------------------
			double pwmServo4Value = SmartDashboard.getNumber("PwmServo1");
			if (pwmServo4Value >= PwmServo4ValueMin && pwmServo4Value <= PwmServo4ValueMax) {
				RobotMap.pwmServo_4.setRaw((int) pwmServo4Value);
			} else {
				if (pwmServo4Value < PwmServo4ValueMin) {
					System.out.print("The Servo cannot be set to \"" + pwmServo4Value
							+ "\"! You can only use values from 50 to 2000!");
					RobotMap.pwmServo_4.setRaw((int) PwmServo4ValueMin);
					SmartDashboard.putNumber("pwmServo_4", (int)PwmServo4ValueMin);
				} else {
					if (pwmServo4Value > PwmServo4ValueMax) {
						System.out.print("The Servo cannot be set to \"" + pwmServo4Value + "\"! You can only use values from 50 to 2000!");
						RobotMap.pwmServo_4.setRaw((int)PwmServo4ValueMax);
						SmartDashboard.putNumber("pwmServo_4", (int)PwmServo4ValueMax);
					} else {
						System.out.print("Something in ServoController Manual value setting went very, very wrong. The value that was entered is: " + pwmServo4Value);
						RobotMap.pwmServo_4.setRaw((int)PwmServo4StartValue);
						SmartDashboard.putNumber("pwmServo_4", PwmServo4StartValue);
					}

				}

			}

			//end of Servo 1 manual ------------------------------------
			//Start of Servo 1 
				}else {
					
					
					if(Robot.oi.safetyStartPressed_value && Robot.oi.safetyYPressed_value  && EnableClimberButtons.SafetyClimberEnable)
					{
						RobotMap.pwmServo_4.setRaw(2000);
						System.out.print("The quick release has been triggered.");
					}
						
						
					
					
					
					
				}
		
		
		
		
		/*
		 The intended use of Servo one is for the pin pulling mechanism.
		 The intended use of Servo two is for some other thing that is important that Z talked about		 
		 */
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("quick release overide", false);
		SmartDashboard.putNumber("PwmServo1", PwmServo4Value);
		RobotMap.pwmServo_4.setRaw((int) PwmServo4Value);
		System.out.print("Servo angle Set to through the use of \"initialize\" to " + PwmServo4Value);
	
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {

		return false;
	}

}
