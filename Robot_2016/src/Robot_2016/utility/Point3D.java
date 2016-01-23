/**
 * 
 */
package Robot_2016.utility;

/**
 * @author 3219
 * a Point class that maintains 3 coordinates. The third coordinate
 *         may be used as a yaw angle, some methods depend on this.
 *
 */

public class Point3D {
	private static final float DEGREES_TO_RADIANS = (float) Math.PI / 180.0f;
	float _x;
	float _y;
	float _z;

	public Point3D(float x, float y, float z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Point3D(double x, double y, double z) {
		_x = (float) x;
		_y = (float) y;
		_z = (float) z;
	}

	public float getX() {
		return _x;
	}

	public float getY() {
		return _y;
	}

	public float getZ() {
		return _z;
	}

	public Point3D difference(Point3D other) {
		float newX = other._x - _x;
		float newY = other._y - _y;
		float newZ = other._z - _z;
		return new Point3D(newX, newY, newZ);
	}

	public float distance(Point3D other) {
		Point3D diff = this.difference(other);
		double totalDiff = Math.sqrt(Math.pow(diff._x, 2)
				+ Math.pow(diff._y, 2) + Math.pow(diff._z, 2));
		return (float) totalDiff;
	}

	public float distanceInXYPlane(Point3D other) {
		Point3D diff = this.difference(other);
		double totalDiff = Math.sqrt(Math.pow(diff._x, 2)
				+ Math.pow(diff._y, 2));
		return (float) totalDiff;
	}

	// create a new point at distance d using this._z as the angle of the vector
	public Point3D pointAtDistance(float d) {
		double rZ = this._z * DEGREES_TO_RADIANS;
		double sinZ = Math.sin(rZ);
		double cosZ = Math.cos(rZ);
		float newX = (float) (_x + cosZ * d);
		float newY = (float) (_y + sinZ * d);
		return new Point3D(newX, newY, _z);
	}

	public Point3D divideBy(float increments) {
		float newX = _x / increments;
		float newY = _y / increments;
		float newZ = _z / increments;
		return new Point3D(newX, newY, newZ);
	}

	public Point3D add(Point3D incrementalVector) {
		float newX = _x + incrementalVector._x;
		float newY = _y + incrementalVector._y;
		float newZ = _z + incrementalVector._z;
		return new Point3D(newX, newY, newZ);
	}
}
