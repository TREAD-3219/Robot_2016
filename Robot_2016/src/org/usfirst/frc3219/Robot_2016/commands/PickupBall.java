package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickupBall extends CommandGroup {
	int direction;
	
	public PickupBall() {
		requires(Robot.multiTool);
		requires(Robot.feedMech);
		createPickupCommands();
		this.direction = 1;
	}
	
	public PickupBall(int direction) {
		requires(Robot.multiTool);
		requires(Robot.feedMech);
		createPickupCommands();
		this.direction = direction;
	}
	
	public void createPickupCommands() {
		this.addParallel(new RollerMotor(direction));
		this.addParallel(new IntakeBall(direction));
	}
}
