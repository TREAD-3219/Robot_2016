package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoChivalDeFrise;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.AutoTurnAndShoot;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DriveOverObstacle;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.DropArms;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChivalDeFrise extends CommandGroup {
    
    public  ChivalDeFrise() { //uses dead reckoning for AutoChivalDeFrise
    	this.addSequential(new EngageRamp());
    	this.addSequential(new DropArms());
    	this.addSequential(new AutoChivalDeFrise());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

// FOR CHIVAL DE FRISE:
// First EngageRamp --> AutoChivalDeFrise --> DriveOverObstacle --> AutoTurnAndShoot