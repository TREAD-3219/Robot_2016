package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoMoat;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnAndShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Moat extends CommandGroup {
    
    public  Moat() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoMoat());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.Moat;
    	super.initialize();
    }
}

//FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot