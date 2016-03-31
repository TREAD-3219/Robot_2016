package org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.Robot.Defense;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.StopRobotDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoDefense extends CommandGroup {

		public NoDefense() {
			Robot.defense = Defense.NoDefense;
			this.addSequential(new EngageRamp());
			this.addSequential(new StopRobotDrive());
		}
}
