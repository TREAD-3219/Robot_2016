package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpitOutBall extends CommandGroup {
	
	public SpitOutBall() {
		
	}
	
	public void createSpitOutCommands() {
		this.addParallel(new RollerMotor());
		this.addParallel(new IntakeBall());
	}

}
