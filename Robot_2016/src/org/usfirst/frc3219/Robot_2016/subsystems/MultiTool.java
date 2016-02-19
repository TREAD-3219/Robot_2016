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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class MultiTool extends Subsystem {

    public Victor driveRollerMotor = RobotMap.driveRollerMotorController;
    public Victor driveArmMotor = RobotMap.driveMultiToolArmMotor;
    public Encoder multiToolEncoder = RobotMap.multiToolEncoder;
    public DigitalInput LimitSwitch = RobotMap.multiToolLimitSwitchHigh;
    
    public int selectedTool = 0;

    public void driveRoller(double speed) {
    	driveRollerMotor.set(speed);
    }
    
    public void driveArmUpDown(double speed) {
    	driveArmMotor.set(speed);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

