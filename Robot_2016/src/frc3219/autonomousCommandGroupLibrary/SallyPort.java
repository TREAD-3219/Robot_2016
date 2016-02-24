package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.DriveOverObstacle;
import frc3219.autonomousLibrary.DriveOverObstacleBackwards;
import frc3219.autonomousLibrary.EngageRamp;
import frc3219.autonomousLibrary.SallyDoorOpen;
import frc3219.autonomousLibrary.SallyTwist;

/**
 *
 */
public class SallyPort extends CommandGroup {
    
    public  SallyPort() {
        this.addSequential(new EngageRamp());
    	this.addSequential(new SallyDoorOpen());
    	this.addSequential(new SallyTwist());
    	this.addSequential(new DriveOverObstacle());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

// FOR SALLY PORT:
// First EngageRamp --> SallyDoorOpen --> SallyTwist --> DriveOverObstacleBackwards --> AutoTurnAndShoot