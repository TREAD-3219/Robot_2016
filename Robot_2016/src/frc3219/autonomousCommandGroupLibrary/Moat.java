package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoMoat;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class Moat extends CommandGroup {
    
    public  Moat() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoMoat());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

//FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot