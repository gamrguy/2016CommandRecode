
package org.usfirst.frc.team2186.robot.subsystems;

import org.usfirst.frc.team2186.robot.RobotMap;
import org.usfirst.frc.team2186.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
	VictorSP m_linear = new VictorSP(RobotMap.Intake.LINEAR);
	public static final double OUTPUT = .75;
	public static final double INPUT = -.75;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeCommand(0));
    }
    
	public void setRollers(double speed) {
		m_linear.set(speed);
	}
}

