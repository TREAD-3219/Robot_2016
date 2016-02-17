package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CycleTool extends Command {
	int change = 0;
	boolean finished = false;
	public CycleTool(int change) {
		this.change = change;
	}

	@Override
	protected void initialize() {
		Robot.multiTool.selectedTool = (Robot.multiTool.selectedTool + change) % 4;
		
	}

	@Override
	protected void execute() {
		finished = true;
		
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
