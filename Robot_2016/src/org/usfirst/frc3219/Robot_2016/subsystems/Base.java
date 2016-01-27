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
import org.usfirst.frc3219.Robot_2016.commands.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Base extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Encoder quadrature_Encoder_1 = RobotMap.baseQuadrature_Encoder_1;
    private final Encoder quadrature_Encoder_2 = RobotMap.baseQuadrature_Encoder_2;
   /* private final SpeedController speed_Controller_0 = RobotMap.baseSpeed_Controller_0;
    private final SpeedController speed_Controller_1 = RobotMap.baseSpeed_Controller_1;
    private final SpeedController speed_Controller_2 = RobotMap.baseSpeed_Controller_2;
    private final SpeedController speed_Controller_3 = RobotMap.baseSpeed_Controller_3;
    private final RobotDrive robotDrive41 = RobotMap.baseRobotDrive41;*/

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

