package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoRWall;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShootBackwards;
import frc3219.autonomousLibrary.BackwardsEngageRamp;

/**
 *
 */
public class RockWall extends CommandGroup {
    
    public  RockWall() {
    	this.addSequential(new BackwardsEngageRamp());
    	this.addSequential(new AutoRWall());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShootBackwards());
    	this.addSequential(new AutoShoot());
    }
}

//FOR AUTORWALL:
	// First EngageRamp --> AutoRWall --> AutoTurnAndShoot