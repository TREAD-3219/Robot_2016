package org.usfirst.frc3219.Robot_2016.subsystems;

import java.awt.Point;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Navigation extends Subsystem {
	Sensors sensors = new Sensors();
	public static double deadRecX;
	public static double deadRecY;
	private static String deadRecArea = "null";
	// the code tells the robot to move.
	private static double deadRecAngle;
	private static double deadRecSpeed;
	private static double deadRecTotalForward;
	private double deadRecLidar1Prediction;
	private double deadRecLidar2Prediction;
	private Point lidarLocation = new Point(0, 0);
	private Point ultraSonicLocation = new Point(0, 0);
	private Point trustedPosition = new Point(0, 0);
	private double trustedAngle;
	public static boolean inOuterWorks = false;

	public static double angleTurnToward(double x, double y) {
		double a = x - deadRecX;
		double b = y - deadRecY;
		double c = Math.sqrt(a * a + b * b);
		double A = Math.asin(a / c);
		double B = 90 - A;
		double finalTurnDegrees = B + deadRecAngle;
		return finalTurnDegrees;
	}

	public static void setSpeed(double speed) {
		deadRecSpeed = speed;
	}

	public static double getSpeed() {
		return deadRecSpeed;
	}

	public static double distanceToPoint(double x, double y) {
		double a = x - deadRecX;
		double b = y - deadRecY;
		double distance = Math.sqrt(a * a + b * b);
		return distance;
	}

	public static Point getDeadRecPoint() {
		Point estimateLoc = new Point(0, 0);
		estimateLoc.x = (int) (deadRecX - (deadRecY % 1));
		estimateLoc.y = (int) (deadRecY - (deadRecY % 1));
		return estimateLoc;
	}

	public static double getDeadRecAngle() {
		return deadRecAngle;
	}

	public static double getDeadRecX() {
		return deadRecX;
	}

	public static double getDeadRecY() {
		return deadRecY;
	}

	public static void deadRecMoved(double distance) {
		deadRecX += distance * Math.sin(deadRecAngle * (Math.PI / 180));
		deadRecY += distance * Math.cos(deadRecAngle * (Math.PI / 180));
		deadRecTotalForward += distance;
	}

	public static void deadRecTurned(double degrees) {
		deadRecAngle += degrees;
	}

	public static String getDeadRecArea() {
		String territory;
		double newX = deadRecX;
		double newY = deadRecY;
		if (deadRecY >= 324) {
			territory = "Enemy";
			newX = (deadRecX - 638) * -1;
			newY = (deadRecY - 648) * -1;
		} else {
			territory = "Friendly";
		}
		if (newX <= 54 && newX >= 0 && newY <= 288 && newY >= 0) {
			deadRecArea = (territory + "Secret Passage");
		}
		if (newX <= 319 && newX >= 54 && newY <= 190 && newY >= 0) {
			deadRecArea = (territory + "Courtyard");
		}
		if (newX <= 319 && newX >= 54 && newY <= 238 && newY >= 190) {
			deadRecArea = (territory + "Outer Works");
		}
		if (newX <= 319 && newX >= 0 && newY <= 324 && newY >= 238) {
			deadRecArea = (territory + "Neutral Zone");
		}
		return deadRecArea;
	}

	public double lidarPrediction() {
		double d = deadRecY;
		double F = deadRecAngle;
		// double C = 45; //this is up to however we mount the lidars
		double a = d / Math.cos(F);
		// double b = d / Math.cos(F + C);
		deadRecLidar1Prediction = a;
		// deadRecLidar2Prediction = b;
		return a;
	}

	public double findLidarPositionY() {
		double distanceFromWall = 0.0; // need to find distance using
										// triangulation
		double lidar1 = sensors.readLidar1();
		double lidar2 = sensors.readLidar1(); // this will maybe be added later?
		int C = 45;
		double c = Math.sqrt(lidar1 * lidar1 + lidar2 * lidar2 - 2 * lidar1 * lidar2 * Math.cos(C));
		double B = Math.asin((lidar2 * Math.sin(C)) / c);
		double F = 90 - (180 - B);
		double f = lidar1 * Math.sin(F);
		distanceFromWall = Math.sqrt(f * f + lidar1 * lidar1);
		return distanceFromWall;
	}

	public double findLidarPositionX() {
		double distanceFromWall = 0.0; // need to find distance using
										// triangulation
		double lidar3 = sensors.readLidar1();
		double lidar4 = sensors.readLidar1(); // these will maybe be added
												// later?
		int C = 45;
		double c = Math.sqrt(lidar3 * lidar3 + lidar4 * lidar4 - 2 * lidar3 * lidar4 * Math.cos(C));
		double B = Math.asin((lidar4 * Math.sin(C)) / c);
		double F = 90 - (180 - B);
		double f = lidar3 * Math.sin(F);
		distanceFromWall = Math.sqrt(f * f + lidar3 * lidar3);
		return distanceFromWall;
	}

	public boolean checkDeadRecAngle() {
		boolean goodEnough = false;
		int probabilityOfCorrectCount = 0;
		if (Math.abs(deadRecAngle/* - compass */) <= 5.0) { // compass will be
															// added from
															// Robot.sensors
			probabilityOfCorrectCount++;
		}
		if (Math.abs(deadRecAngle/* - findEncoderAngle() */) <= 5.0) {
			probabilityOfCorrectCount++;
		}
		if (probabilityOfCorrectCount >= 3) {
			goodEnough = true;
		}
		return goodEnough;
	}

	public boolean checkDeadRecLoc() {
		boolean goodEnough = false;
		int probabilityOfCorrectCount = 0;
		if (Math.abs(deadRecY - findLidarPositionY()) <= 5.0) {
			probabilityOfCorrectCount++;
		}

		if (Math.abs(deadRecX - findLidarPositionX()) <= 5.0) {
			probabilityOfCorrectCount++;
		}

		if (Math.abs(deadRecTotalForward /* - findEncoderForwardDist() */) <= 5.0) {
			probabilityOfCorrectCount++;
		}
		return goodEnough;
	}

	public void ultraSonicTriangulation() {

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}