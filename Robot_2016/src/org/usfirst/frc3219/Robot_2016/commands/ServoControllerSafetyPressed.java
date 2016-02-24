package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoControllerSafetyPressed extends Command {
	boolean finished = false;
	// PRESSED PRESSED PRESSED PRESSED
	// START START START START START
	private static final String QUICK_RELEASE_OVERRIDE = "quick release override";
	
	@Override
	protected void end() {

	}

	@Override
	protected void execute() {
		if (EnableClimberButtons.safetyClimberEnable || SmartDashboard.getBoolean(QUICK_RELEASE_OVERRIDE)) {
			if (Robot.oi.buttonStart.get() && Robot.oi.buttonY.get()) {
				SmartDashboard.putBoolean("Climber enabling", true);
				Scheduler.getInstance().add(new AutoClimb());
				finished = true;
			} else {
				SmartDashboard.putBoolean("Climber enabling", false);
			}
		} else {
			SmartDashboard.putBoolean("Climber enabling", false);
		}
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}
}
