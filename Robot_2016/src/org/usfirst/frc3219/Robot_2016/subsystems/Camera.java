package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera {
	public static final double IMAGE_CENTER = 320.0;

	// Blob Analysis
	public double getCOG_X() {
		return SmartDashboard.getNumber("SHAPE_X_COORD", -1.0);
	}

	public String getFileName() {
		return SmartDashboard.getString("SHAPE_FILENAME", "unknown");
	}

	public boolean targetDetected() {
		return Robot.camera.getFileName().startsWith("Object");
	}

	public static void setupRobotMap() {
		RobotMap.roboRealmTable = NetworkTable.getTable("SmartDashboard");
		RobotMap.camera = new Camera();
	}
	
}
