package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRWall;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.StopRobotDrive;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWall extends CommandGroup {
    
    public  RockWall() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRWall());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new StopRobotDrive());
    	this.addSequential(new AutoShoot());
    }
    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.RockWall;
    	super.initialize();
    }
}

//FOR AUTORWALL:
	// First EngageRamp --> AutoRWall --> AutoTurnAndShoot