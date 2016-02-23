package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoMoat;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShootBackwards;
import frc3219.autonomousLibrary.BackwardsEngageRamp;

/**
 *
 */
public class Moat extends CommandGroup {
    
    public  Moat() {
    	this.addSequential(new BackwardsEngageRamp());
    	this.addSequential(new AutoMoat());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShootBackwards());
    	this.addSequential(new AutoShoot());
    }
}

//FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot