package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoRWall;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class RockWall extends CommandGroup {
    
    public  RockWall() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRWall());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

//FOR AUTORWALL:
	// First EngageRamp --> AutoRWall --> AutoTurnAndShoot