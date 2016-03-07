package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.Robot.Defense;
import org.usfirst.frc3219.Robot_2106.autonomousLibrary.EngageRamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoDefense extends CommandGroup {

		public NoDefense() {
			Robot.defense = Defense.NoDefense;
			this.addSequential(new EngageRamp());
			Robot.drive.driveValues(0, 0);
			Robot.drive.setBrakesOn();
		}
}
