package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRamparts;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.StopRobotDrive;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.SetMultiToolPoint;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Ramparts extends CommandGroup {
    
    public  Ramparts() {
    	this.addParallel(new SetMultiToolPoint(MultiTool.SHOOT_POSITION));
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRamparts());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new StopRobotDrive());
    	this.addSequential(new AutoShoot());
    }
    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.Ramparts;
    	super.initialize();
    }
}

//FOR AUTORAMPARTS:
	// First EngageRamp --> AutoRamparts --> AutoTurnAndShoot