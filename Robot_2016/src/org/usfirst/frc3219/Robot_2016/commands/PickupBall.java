package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickupBall extends CommandGroup {
	
	public PickupBall() {
		requires(Robot.multiTool);
		requires(Robot.feedMech);
		createPickupCommands();
	}
	
	public void createPickupCommands() {
		this.addParallel(new RollerMotor());
		this.addParallel(new IntakeBall());
	}
}
