package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRough;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.StopRobotDrive;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.SetMultiToolPoint;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	this.addSequential(new SetMultiToolPoint(MultiTool.SHOOT_POSITION));
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRough());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new StopRobotDrive());
		this.addSequential(new AutoShoot());
    }

    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.RoughTerrain;
    	super.initialize();
    	SmartDashboard.putBoolean(EngageRamp.ENGAGE_RAMP_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoRough.AUTO_ROUGH_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoRotate.AUTO_ROTATE_FINISH_TAG, false);
    	SmartDashboard.putBoolean(EngageRamp.DROP_ARMS_START_TAG, false);
    	SmartDashboard.putBoolean(EngageRamp.DROP_ARMS_FINISH_TAG, false);
    	SmartDashboard.putBoolean(StopRobotDrive.STOP_ROBOT_DRIVE_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoShoot.AUTO_SHOOT_START_TAG, false);
    }
}
