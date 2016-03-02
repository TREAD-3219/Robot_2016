package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRough;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnAndShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRough());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

//FOR AUTOROUGH:
	// First EngageRamp --> AutoRough --> AutoTurnAndShoot