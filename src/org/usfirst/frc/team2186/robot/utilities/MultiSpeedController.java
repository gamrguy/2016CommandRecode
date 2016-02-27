package org.usfirst.frc.team2186.robot.utilities;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * 	This class is used to define a multi-speed
 * 	motor controlling system.
 **/

public class MultiSpeedController {
	private ArrayList<SpeedController> m_sp;
	
	public MultiSpeedController(SpeedController... m) {
		m_sp = new ArrayList<SpeedController>();
		for (SpeedController sp : m) {
			m_sp.add(sp);
		}
	}
	
	public MultiSpeedController(ArrayList<SpeedController> ms) {
		m_sp = ms;
	}

	public void set(double speed) {
		for(SpeedController m : m_sp) {
			m.set(speed);
		}
	}
	
	public void stop() {
		for(SpeedController m : m_sp) {
			m.set(0);
		}
	}
}
