package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup { 
	
	public AutoShoot() {
		requires(Robot.shooter);
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		this.addSequential(new RunShooter(10.0));
		this.addSequential(new AutoTimeout(.05));
		this.addSequential(new FeedShooter());
		this.addSequential(new StopShooter());
	}
	
}
