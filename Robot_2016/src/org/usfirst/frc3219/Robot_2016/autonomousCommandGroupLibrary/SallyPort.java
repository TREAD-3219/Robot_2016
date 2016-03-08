package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoTurnTowardsGoal;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.DriveOverObstacle;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.SallyDoorOpen;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.SallyTwist;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SallyPort extends CommandGroup {
    
    public  SallyPort() {
        this.addSequential(new EngageRamp());
    	this.addSequential(new SallyDoorOpen());
    	this.addSequential(new SallyTwist());
    	this.addSequential(new DriveOverObstacle());
    	this.addSequential(new AutoRotate());
    	//this.addSequential(new AutoTurnTowardsGoal());
    	this.addSequential(new AutoShoot());
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.SallyPort;
    	super.initialize();
    }
}

// FOR SALLY PORT:
// First EngageRamp --> SallyDoorOpen --> SallyTwist --> DriveOverObstacleBackwards --> AutoTurnAndShoot