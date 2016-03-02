package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoRamparts;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class Ramparts extends CommandGroup {
    
    public  Ramparts() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRamparts());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

//FOR AUTORAMPARTS:
	// First EngageRamp --> AutoRamparts --> AutoTurnAndShoot