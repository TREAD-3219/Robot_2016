// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class MultiTool extends Subsystem {

<<<<<<< HEAD
   
	public Victor driveRollerMotor = RobotMap.driveRollerMotorController;
    public Victor driveArmMotor = RobotMap.driveMultiToolMotor1;
    public DigitalInput limitSwitchLow = RobotMap.multiToolLimit_Switch_1;
    public DigitalInput limitSwitchHigh = RobotMap.multiToolLimit_Switch_2;
   
    public boolean isSwitchSetLow(){
    	boolean limitLow = limitSwitchLow.get();
    	return limitLow;
    }
=======
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Victor driveRollerMotor = RobotMap.driveRollerMotorController;
    public Victor driveArmMotor = RobotMap.driveMultiToolArmMotor;
    public DigitalInput limitSwitchLow = RobotMap.multiToolLimitSwitchLow;
    public DigitalInput limitSwitchHigh = RobotMap.multiToolLimitSwitchHigh;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
>>>>>>> refs/remotes/origin/master

    public boolean isSwitchSetHigh(){
    	boolean limitHigh = limitSwitchHigh.get();
    	return limitHigh;
    }
    
    public void driveRoller(double speed) {
    	driveRollerMotor.set(speed);
    }
    
    public void driveArmUpDown(double speed) {
    	driveArmMotor.set(speed);
    }

    public void initDefaultCommand() {
    }
}

