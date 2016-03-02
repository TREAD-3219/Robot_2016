package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnAndShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DriveOverObstacle;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DriveOverObstacleBackwards;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.SallyDoorOpen;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.SallyTwist;

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
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

// FOR SALLY PORT:
// First EngageRamp --> SallyDoorOpen --> SallyTwist --> DriveOverObstacleBackwards --> AutoTurnAndShoot