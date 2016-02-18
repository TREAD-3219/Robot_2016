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
public class FeedMech extends Subsystem { //This class is for Putting the ball into the shooter

	private Victor feederMotor = RobotMap.shooterFeeder;
	private DigitalInput feederLimitSwitch = RobotMap.feederLimitSwitch;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean getLimitSwitch() {
		return feederLimitSwitch.get();
	}
	
	public void spinFeeder() {
		feederMotor.set(1.0);
	}
	public void stopFeeder() {
		feederMotor.set(0.0);
	}

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

