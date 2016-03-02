package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoClimb extends CommandGroup {
	public AutoClimb() {
		requires(Robot.climber);
		createAutoClimbCommands();
		
	}
	public void createAutoClimbCommands() {
		this.addSequential(new SetMultiToolPoint(MultiTool.SHOOT_POSITION));
		this.addSequential(new ReleaseClimber());
		//this.addSequential(new AutoDrive(1, 24));
	}

}
