package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpBoulders extends CommandGroup{
	
	public PickUpBoulders(){
		requires(Robot.multiTool);
	}
	
	public void boulderGrabber(){
		this.addSequential(new RollerMotor());
		this.addSequential(new FeedShooter());
	}
}
