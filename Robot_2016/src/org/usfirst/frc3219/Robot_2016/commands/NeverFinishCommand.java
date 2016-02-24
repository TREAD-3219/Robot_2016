package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

// This class is a parent for Commands that do not stop after initiation.
// It's really more of a documentation thing to make it clear it will not stop
// What use is there for a command that never stops?  A command that,
// for example, updates the SmartDashboard with sensor readings.

public abstract class NeverFinishCommand extends Command {

	@Override
	protected final boolean isFinished() {
		return false;
	}
}
