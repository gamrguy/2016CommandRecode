
package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2186.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2186.robot.commands.IntakeCommand;
import org.usfirst.frc.team2186.robot.commands.ShiftCommand;
import org.usfirst.frc.team2186.robot.commands.TeleopCommand;
import org.usfirst.frc.team2186.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2186.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2186.robot.subsystems.GearboxSubsystem;
import org.usfirst.frc.team2186.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final GearboxSubsystem gearboxSubsystem = new GearboxSubsystem();
	public static OI oi;

    Command autonomousCommand;
    Command teleopCommand;
    Compressor compressor;
    DigitalOutput ledRing;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	ledRing = new DigitalOutput(5);
    	compressor = new Compressor();
    	compressor.start();
    	
		oi = new OI();
//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);
        

    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	driveSubsystem.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * 	Grabs the autonomous script from NetworkTables, and starts the autonomous.
	 */
    public void autonomousInit() {
        if (autonomousCommand == null)
        	autonomousCommand = new AutonomousCommand(SmartDashboard.getString("AutoCode", "stop"));
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        if (teleopCommand == null) teleopCommand = new TeleopCommand();
        teleopCommand.start();
        
        oi.intakeInputButton.whileActive(new IntakeCommand(IntakeSubsystem.INPUT));
        oi.intakeOutputButton.whileActive(new IntakeCommand(IntakeSubsystem.OUTPUT));
        oi.gearShiftButton.whenPressed(new ShiftCommand());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
