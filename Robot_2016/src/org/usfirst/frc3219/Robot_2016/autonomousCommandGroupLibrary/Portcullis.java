package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoPortcullisAlign;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoPortcullisBackUp;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoPortcullisRaise;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnTowardsGoal;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DriveOverObstacle;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Portcullis extends CommandGroup {
    
    public  Portcullis() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoPortcullisAlign());
    	this.addSequential(new AutoPortcullisRaise());
    	this.addParallel(new AutoPortcullisBackUp());
    	this.addSequential(new DriveOverObstacle());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnTowardsGoal());
    	this.addSequential(new AutoShoot());
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.Portcullis;
    	super.initialize();
    }
}

//FOR PORTCULLIS:
	// First EngageRamp --> AutoPortcullisAlign --> parallel(AutoPortcullisRaise/AutoPortcullisBackUp) --> DriveOverObstacle --> AutoTurnAndShoot