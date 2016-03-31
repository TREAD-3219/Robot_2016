package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoChevalDeFrise;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.DropArms;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChevalDeFrise extends CommandGroup {
    
    public ChevalDeFrise() { //uses dead reckoning for AutoChivalDeFrise
    	this.addSequential(new EngageRamp());
    	this.addSequential(new DropArms());
    	this.addSequential(new AutoChevalDeFrise());
    	this.addSequential(new AutoRotate());
    	//this.addSequential(new AutoTurnTowardsGoal());
    	this.addSequential(new AutoShoot());
    }
    
    @Override
    protected void initialize() {
    	Robot.defense = Robot.Defense.ChevalDeFrise;
    	super.initialize();
    }
}

// FOR CHEVAL DE FRISE:
// First EngageRamp --> AutoChivalDeFrise --> DriveOverObstacle --> AutoTurnAndShoot