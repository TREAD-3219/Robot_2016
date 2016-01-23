package org.usfirst.frc3219.Robot_2016.subsystems;

import java.awt.Point;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Navigation extends Subsystem{
	Sensors sensors = new Sensors();
	public Point deadReckoningLoc = new Point(0, 0); // called to change every
														// time
	// the code tells the robot to move.
	private double deadReckoningAngle;
	private double deadRecLidar1Prediction;
	private double deadRecLidar2Prediction;
	private Point lidarLocation = new Point(0, 0);
	private double lidar1;
	private double lidar2;
	private Point ultraSonicLocation = new Point(0, 0);
	private double distanceFromWall;
	private Point trustedPosition = new Point(0, 0);
	private double trustedAngle;

	public void deadReckoning() {
		//here is where any needed calculations would go for deadReckoning
		
		
		//predicts the distance the lidars will measure through triangulation.
		double d = deadReckoningLoc.y;
		double F = deadReckoningAngle;
		double C = 45; //this is up to however we mount the lidars
		double a = d / Math.cos(F);
		double b = d / Math.cos(F + C);
		
		/*double B = 180 - (90 - F);
		double c = (a * Math.sin(C)) / (180 - C - B);
		double b = Math.sqrt(a * a + c * c - 2 * a * c * Math.cos(B));*/ 		//ignore me im an idiot, i wrote all this for nothing. Excuse me while i jump off a bridge
		
		
		//Math.sqrt(a * a + (a * Math.sin(C)) / (180 - C - B) * (a * Math.sin(C)) / (180 - C - B) - 2 * a * (a * Math.sin(C)) / (180 - C - B) * Math.cos(180 - (90 - F)));
		
		
		deadRecLidar1Prediction = a;
		deadRecLidar2Prediction = b;
	}

	public void lidarTriangulation() {
		lidar1 = sensors.readLidar1();
		lidar2 = sensors.readLidar2(); // this will maybe be added later?
		int C = 45;
		double c = Math.sqrt(lidar1 * lidar1 + lidar2 * lidar2 - 2 * lidar1 * lidar2 * Math.cos(C));
		double B = Math.asin((lidar2 * Math.sin(C)) / c);
		double F = 90 - (180 - B);
		double f = lidar1 * Math.sin(F);
		distanceFromWall = Math.sqrt(f * f + lidar1 * lidar1);
	}

	public void ultraSonicTriangulation() {

	}
	public double[] compareDeadReckoning() {
		double[] trustedMeasurements = new double[3];
		trustedMeasurements[0] = Math.abs(deadRecLidar1Prediction - lidar1) / 5.0;
		trustedMeasurements[1] = Math.abs(deadRecLidar2Prediction - lidar2) / 5.0;
		return trustedMeasurements;
	}
	
	public double[] compareLidars() {
		double[] trustedMeasurements = new double[3];
		return trustedMeasurements;
	}
	
	public double[] compareUltraSonics() {
		double[] trustedMeasurements = new double[3];
		return trustedMeasurements;
	}
	
	public void trustedPosition() {
		double[] trustedStats = new double[3]; //0 is deadReckoning
		double[] deadRecStats = compareDeadReckoning();
		int sum = 0;
		for (int i = 0; i < deadRecStats.length; i++) {
			sum += deadRecStats[i];
		}
		trustedStats[0] = sum / deadRecStats.length;
		if  (trustedStats[0] >= 4.0) {
			//then use this as positioning
		} else {
			double[] lidarStats = compareLidars();
			sum = 0;
			for (int i = 0; i < lidarStats.length; i++) {
				sum += lidarStats[i];
			}
			trustedStats[1] = sum / lidarStats.length;
			if (trustedStats[1] >= 4.0) {
				//then use that for navigation
			} else {
				double[] ultraSonicStats = compareUltraSonics();
				sum = 0;
				for (int i = 0; i < ultraSonicStats.length; i++) {
					sum += ultraSonicStats[i];
				}
				trustedStats[2] = sum / ultraSonicStats.length;
			}
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
