package org.usfirst.frc3219.Robot_2016.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Camera {
	// Blob Analysis
	public double getCOG_X() {
		return SmartDashboard.getNumber("COG_X", -1.0);
	}

	public String getFileName() {
		return SmartDashboard.getString("SHAPE_FILENAME", "unknown");
	}
}
