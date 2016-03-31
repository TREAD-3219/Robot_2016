package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRough;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoTurnTowardsGoal;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	SmartDashboard.putBoolean("EngageRampFinish", false);
    	SmartDashboard.putBoolean("AutoRoughFinish", false);
    	SmartDashboard.putBoolean("AutoRotateFinish", false);
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRough());
    	this.addSequential(new AutoRotate());
		Robot.drive.driveValues(0, 0);
		this.addSequential(new AutoShoot());
    	System.out.println("END OF AUTONOMOUS");
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.RoughTerrain;
    	super.initialize();
    }
}

//FOR AUTOROUGH:
	// First EngageRamp --> AutoRough --> AutoTurnAndShoot