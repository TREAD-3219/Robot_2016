package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoMoat;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class Moat extends CommandGroup {
    
    public  Moat() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoMoat());
    	//this.addSequential(new AutoTurnAndShoot());
    }
}

//FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot