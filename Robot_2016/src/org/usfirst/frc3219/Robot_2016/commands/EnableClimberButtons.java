package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EnableClimberButtons extends Command {
	public static boolean SafetyClimberEnable = false;
	
	private static final String QUICK_RELEASE_OVERRIDE = "quick release override";
	Servo servo4;
	Servo servo5;
	@Override
	protected void end() {

		SafetyClimberEnable = true;
		System.out.print("The climber can now be used!");
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		// 1:55
		this.setTimeout(1); // wait to trigger isFinished() to start end()
		SafetyClimberEnable = false;
		SmartDashboard.putBoolean(QUICK_RELEASE_OVERRIDE, false);
		servo4 = RobotMap.pwmServo_4;
		servo5 = RobotMap.pwmServo_5;
		servo4.setAngle(180.0f);
		servo5.setAngle(0.0f);
		SmartDashboard.putBoolean("Climber enabling", false);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {

		return this.isTimedOut();
	}

}
