package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoRough;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoRough());
    	//this.addSequential(new AutoTurnAndShoot());
    }
}

//FOR AUTOROUGH:
	// First EngageRamp --> AutoRough --> AutoTurnAndShoot