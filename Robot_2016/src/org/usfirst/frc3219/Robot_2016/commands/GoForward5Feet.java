package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoForward5Feet extends CommandGroup{
	public GoForward5Feet() {
		createGoForwardCommand();
	}
	private void createGoForwardCommand(){
		this.addSequential(new AutoDrive(50, 10));
	}
}
