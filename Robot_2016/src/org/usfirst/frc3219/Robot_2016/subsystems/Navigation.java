package org.usfirst.frc3219.Robot_2016.subsystems;

import java.awt.Point;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.DedReckoningChecks;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Navigation extends Subsystem {
	Sensors sensors;
	public double dedRecX;
	public double dedRecY;
	private String dedRecArea = "null";
	// the code tells the robot to move.
	private double dedRecAngle;
	private double dedRecSpeed;
	private double dedRecTotalForward;
	private double dedRecLidar1Prediction;
	private double dedRecLidar2Prediction;
//	private Point lidarLocation = new Point(0, 0);
//	private Point ultraSonicLocation = new Point(0, 0);
//	private Point trustedPosition = new Point(0, 0);
	private double trustedAngle;
	
	private boolean inOuterWorks = false;

	public Navigation() {
		 sensors = new Sensors();
	}
	
	public boolean inOuterWorks() {
		return this.inOuterWorks;
	}
	// 
	public double angleTurnToward(double x, double y) {
		double a = x - dedRecX;
		double b = y - dedRecY;
		double c = Math.sqrt(a * a + b * b);
		double A = Math.asin(a / c);
		double B = 90 - A;
		double finalTurnDegrees = B + dedRecAngle;
		return finalTurnDegrees;
	}

	public void setSpeed(double speed) {
		dedRecSpeed = speed;
	}

	public double getSpeed() {
		return dedRecSpeed;
	}

	public double distanceToPoint(double x, double y) {
		double a = x - dedRecX;
		double b = y - dedRecY;
		double distance = Math.sqrt(a * a + b * b);
		return distance;
	}

	public Point getDedRecPoint() {
		Point estimateLoc = new Point(0, 0);
		estimateLoc.x = (int) (dedRecX - (dedRecY % 1));
		estimateLoc.y = (int) (dedRecY - (dedRecY % 1));
		return estimateLoc;
	}

	public double getDedRecAngle() {
		return dedRecAngle;
	}

	public double getDedRecX() {
		return dedRecX;
	}

	public double getDedRecY() {
		return dedRecY;
	}

	public void dedRecMoved(double distance) {
		dedRecX += distance * Math.sin(dedRecAngle * (Math.PI / 180));
		dedRecY += distance * Math.cos(dedRecAngle * (Math.PI / 180));
		dedRecTotalForward += distance;
	}

	public void dedRecTurned(double degrees) {
		dedRecAngle += degrees;
	}

	public String getDedRecArea() {
		String territory;
		double newX = dedRecX;
		double newY = dedRecY;
		if (dedRecY >= 324) {
			territory = "Enemy";
			newX = (dedRecX - 638) * -1;
			newY = (dedRecY - 648) * -1;
		} else {
			territory = "Friendly";
		}
		if (newX <= 54 && newX >= 0 && newY <= 288 && newY >= 0) {
			dedRecArea = (territory + "Secret Passage");
		}
		if (newX <= 319 && newX >= 54 && newY <= 190 && newY >= 0) {
			dedRecArea = (territory + "Courtyard");
		}
		if (newX <= 319 && newX >= 54 && newY <= 238 && newY >= 190) {
			dedRecArea = (territory + "Outer Works");
		}
		if (newX <= 319 && newX >= 0 && newY <= 324 && newY >= 238) {
			dedRecArea = (territory + "Neutral Zone");
		}
		return dedRecArea;
	}

	public double lidarPrediction() {
		double d = dedRecY;
		double F = dedRecAngle;
		// double C = 45; //this is up to however we mount the lidars
		double a = d / Math.cos(F);
		// double b = d / Math.cos(F + C);
		dedRecLidar1Prediction = a;
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

	public boolean checkDedRecAngle() {
		boolean goodEnough = false;
		int probabilityOfCorrectCount = 0;
		 // compass will be added from Robot.sensors
		if (Math.abs(dedRecAngle - Robot.sensors.navx.getFusedHeading()) <= 5.0) {
			probabilityOfCorrectCount++;
		}
		
		// what is this supposed to test?
		if (Math.abs(dedRecAngle/* - findEncoderAngle() */) <= 5.0) {
			probabilityOfCorrectCount++;
		}
		
		// seems clear goodEnough will always be false... 
		if (probabilityOfCorrectCount >= 3) {
			goodEnough = true;
		}
		
		return goodEnough;
	}

	public boolean checkDedRecLoc() {
		boolean goodEnough = false;
		if (Math.abs(dedRecY - findLidarPositionY()) <= 5.0) {
		}

		if (Math.abs(dedRecX - findLidarPositionX()) <= 5.0) {
		}

		if (Math.abs(dedRecTotalForward /* - findEncoderForwardDist() */) <= 5.0) {
		}
		return goodEnough;
	}

	public void ultraSonicTriangulation() {

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DedReckoningChecks());
	}

	public void checkAttitude() {
		double pitch = Robot.sensors.navx.getPitch();
		if (Math.abs(pitch) >= 5.0) {
			inOuterWorks = true;
		} else {
			inOuterWorks = false;
		}
	}

	public void move(double avgDist) {
		
	}

	public void turn(double degrees) {
		// TODO Auto-generated method stub
		
	}
}