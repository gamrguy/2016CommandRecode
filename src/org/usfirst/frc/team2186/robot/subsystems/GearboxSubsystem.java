
package org.usfirst.frc.team2186.robot.subsystems;

import org.usfirst.frc.team2186.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearboxSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void shiftLeft(int gear) {
		Robot.driveSubsystem.m_left.shift(gear);
	}
	
	public void shiftRight(int gear) {
		Robot.driveSubsystem.m_right.shift(gear);
	}
	
	public void shift(int gear) {
		shiftLeft(gear);
		shiftRight(gear);
	}
}

