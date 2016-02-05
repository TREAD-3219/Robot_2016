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

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    Jaguar spinShooterWheel = RobotMap.spinShooterWheel;
    Victor shooterFeeder = RobotMap.shooterFeeder;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    public void shoot(double shootSpeed) {
    	spinShooterWheel.set(shootSpeed);
    }
    
    public void feed(double speed) {
    	shooterFeeder.set(speed);
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	
        // Set up counter mode
        /*RobotMap.normalCounter.setUpSource(1);
    	RobotMap.normalCounter.setUpDownCounterMode();
    	
    	// Reset
    	RobotMap.normalCounter.reset();
    	
    	// Counter Setting
    	RobotMap.normalCounter.setMaxPeriod(.1);
    	RobotMap.normalCounter.setUpdateWhenEmpty(true);lk;ajdflakjflkafjalk;jfal;kjfalkfjaslkfj aj  this laptop sucks i quit - Kyle Gatley
    	RobotMpa.normalCounter
    	RobotMap.normalCounter.setReverseDirection(false);
    	RobotMap.normalCounter.setSamplesToAverage(10);
    	RobotMap.normalCounter.setDistancePerPulse(12);*/
    }
}

