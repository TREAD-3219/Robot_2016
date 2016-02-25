package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoCenterToGoal;

public class AutoShoot extends CommandGroup { //This is for the full feed and shooting mechanisms
	
	public AutoShoot() {
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		this.addSequential(new ShootBoulder());
		this.addParallel(new AutoCenterToGoal());
	}
	
}