package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DeadReckoningChecks extends Command {

	private static double deadRecX = Navigation.getDeadRecX();
	private static double deadRecY = Navigation.getDeadRecY();
	private static double deadRecAngle = Navigation.getDeadRecAngle();

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		double angleIsOffBy = Robot.sensors.getAngle() - Navigation.getDeadRecAngle();
		double pitch = Robot.sensors.navx.getPitch();
		// Angle checks
		if (Math.abs(angleIsOffBy) >= 1.0) {
			Navigation.deadRecTurned(angleIsOffBy);
		}
		// OuterWorks Checks
		if (Math.abs(pitch) >= 5.0) {
			Navigation.inOuterWorks = true;
		} else {
			Navigation.inOuterWorks = false;
		}
		if (Navigation.inOuterWorks) {
			if (Navigation.getDeadRecArea().equals("Enemy Outer Works")) {
				Navigation.deadRecX = 410;

			} else if (Navigation.getDeadRecArea().equals("Friendly Outer Works")) {
				Navigation.deadRecX = 190;
			}

		}
		SmartDashboard.putNumber("Dead Rec X", Navigation.getDeadRecX());
		SmartDashboard.putNumber("Dead Rec Y", Navigation.getDeadRecY());
		SmartDashboard.putNumber("Dead Rec Angle", Navigation.getDeadRecAngle());
		SmartDashboard.putString("Dead Rec Area", Navigation.getDeadRecArea());

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
