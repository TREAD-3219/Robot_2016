package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoClimb extends CommandGroup {
	public void createAutoClimbCommands() {
		this.addSequential(new SetMultiToolPoint());
		this.addSequential(new ReleaseClimber());
		this.addSequential(new AutoDrive(-1, 24));
		
	}

}
