package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnAndShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DrawbridgeExecuteArms;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DrawbridgePID_Back;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DriveOverObstacle;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DropArms;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageDrawbridge;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Drawbridge extends CommandGroup {
    
    public  Drawbridge() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new EngageDrawbridge());
    	this.addParallel(new DropArms());
    	this.addSequential(new DrawbridgeExecuteArms());
    	this.addParallel(new DrawbridgePID_Back());
    	this.addSequential(new DriveOverObstacle());
    	this.addSequential(new AutoRotate());
        this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
    
    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.Drawbridge;
    	super.initialize();
    }
}
//FOR DRAWBRIDGE:
	// First EngageRamp --> parallel(EngageDrawBridge/DropArms) --> parallel(DrawbridgeExecuteArms/DrawbridgePID_Back) --> DriveOverObstacle --> AutoTurnAndShoot