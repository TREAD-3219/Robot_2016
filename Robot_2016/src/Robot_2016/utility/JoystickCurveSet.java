package Robot_2016.utility;


public class JoystickCurveSet {
	private static final double DEADZONE = 0.05;
	private static JoystickCurveSet _linear = new JoystickCurveSet(
			new JoystickResponseCurve(0.2, 0.0, 3, DEADZONE),
			new JoystickResponseCurve(0.2, 0.0, 3, DEADZONE),
			new JoystickResponseCurve(0.2, 0.0, 3, DEADZONE));
	private static JoystickCurveSet _conservative = new JoystickCurveSet(
			new JoystickResponseCurve(0.2, 0.4, 3, DEADZONE),
			new JoystickResponseCurve(0.2, 0.4, 3, DEADZONE),
			new JoystickResponseCurve(0.2, 0.6, 3, DEADZONE));
	private static JoystickCurveSet _killer = new JoystickCurveSet(
			new JoystickResponseCurve(0.4, 0.4, 3, DEADZONE),
			new JoystickResponseCurve(0.4, 0.4, 3, DEADZONE),
			new JoystickResponseCurve(0.4, 0.4, 3, DEADZONE));
	
	private JoystickResponseCurve forward;
	private JoystickResponseCurve strafe;
	private JoystickResponseCurve turn;
	
	public JoystickCurveSet(JoystickResponseCurve f, JoystickResponseCurve s, JoystickResponseCurve t) {
		forward = f;
		strafe = s;
		turn = t;
	}
	
	public double adjustThrottle(double rawThrottle) {
		// range is -1 (full forward) to 1 (full back)
		// translate to 0 to 1.0;
		double throttle = rawThrottle - 1.0;
		double adjusted = throttle / 2.0;
		return adjusted;
	}
	
	public Point3D transform(Point3D input, double throttle) {
		double fwd = forward.transform(input.getY(), throttle);
		double str = strafe.transform(input.getX(), throttle);
		double rot = turn.transform(input.getZ(), throttle);
		Point3D res = new Point3D(str, fwd, rot);
		return res;
	}
	
	public static JoystickCurveSet linear() {
		return _linear;
	}
	
	public static JoystickCurveSet conservative() {
		return _conservative;
	}
	
	public static JoystickCurveSet aggressive() {
		return _killer;
	}
}
