package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoMoat;
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
public class Moat extends CommandGroup {
    
    public  Moat() {
    	this.addParallel(new SetMultiToolPoint(MultiTool.SHOOT_POSITION));
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoMoat());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new StopRobotDrive());
    	//this.addSequential(new AutoShoot());
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.Moat;
    	super.initialize();
    }
}

//FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot