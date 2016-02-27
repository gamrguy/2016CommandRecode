
package org.usfirst.frc.team2186.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import java.util.ArrayList;

import org.usfirst.frc.team2186.robot.Robot;
import org.usfirst.frc.team2186.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2186.robot.subsystems.IntakeSubsystem;

/**
 *	Handles autonomous based on the given script.
 *	Script commands are ([]'s mean required, <>'s mean not required):
 *		forward [inches/feet] <ft>
 *		backward [inches/feet] <ft>
 *		turn [degrees] <left>
 *		intake (no options)
 *		shift [high/low]
 */
public class AutonomousCommand extends Command {
	private ArrayList<String[]> commands;
	private String backup;
	
    public AutonomousCommand(String s) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSubsystem);
        requires(Robot.gearboxSubsystem);
        requires(Robot.intakeSubsystem);
        
        //Structure: ["forward", "10", "ft"], ["turn", "90", "left"], etc.
        if(s != "" && s != null)
        	for(String x: s.split("\n"))
        		commands.add(x.split(" "));
        
        backup = s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//In case autonomous is started more than once in one session
    	if(backup != "" && backup != null && commands.size() == 0)
        	for(String x: backup.split("\n"))
        		commands.add(x.split(" "));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(commands.size() == 0)
    		return;
    	
    	String[] cmd = commands.remove(0);
    	String action = cmd[0].toLowerCase();
    	String extra = "";
    	double dist = 0;
    	if(cmd.length > 1 && action != "shift")
    		dist = Double.parseDouble(cmd[1]);
    	if(cmd.length > 2 && action != "shift")
    		extra = cmd[2].toLowerCase();
    	else extra = cmd[1];
    	
    	//handling for measurement in feet instead of inches
    	if(extra == "ft")
    		if(action == "forward" || action == "backward")
    			dist *= 12;
    	
    	switch(action)
    	{
    	case "forward":
    		if(cmd.length >= 2)
    			Robot.driveSubsystem.goDistance(dist, DriveSubsystem.MAX_SPEED);
    		break;
    	case "backward":
    		if(cmd.length >= 2)
    			Robot.driveSubsystem.goDistance(-dist, DriveSubsystem.MAX_SPEED);
    		break;
    	case "turn":
    		if(cmd.length >= 3 && extra == "left")
    			Robot.driveSubsystem.turnAngle(DriveSubsystem.MAX_SPEED, -dist);
    		else
    			Robot.driveSubsystem.turnAngle(DriveSubsystem.MAX_SPEED, dist);
    		break;
    	case "intake":
    		Robot.intakeSubsystem.setRollers(IntakeSubsystem.OUTPUT);
    		Timer.delay(5);
    		break;
    	case "shift":
    		if(cmd.length >= 2){
    			if(extra == "high")
    				Robot.gearboxSubsystem.shift(1);
    			else
    				Robot.gearboxSubsystem.shift(0);
    		}
    	default:
    		Robot.driveSubsystem.stop();
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
