// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
// import frc.robot.subsystems.PWMDrivetrain;
// import frc.robot.subsystems.PWMLauncher;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANIntake;
import frc.robot.subsystems.CANLauncher;

/**
 * 1 This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
  // private final PWMDrivetrain m_drivetrain = new PWMDrivetrain();
  public final CANDrivetrain m_drivetrain = new CANDrivetrain();
  // private final PWMLauncher m_launcher = new PWMLauncher();
  public final CANLauncher m_launcher = new CANLauncher();
  public final CANIntake m_intake = new CANIntake();

  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  // private final GenericHID m_strafeGenericHID =
  //     new GenericHID(1);
  // private final JoystickButton m_Trigger =
  //     new JoystickButton(m_strafeGenericHID, 1);
  // private final JoystickButton m_button2 =
  //     new JoystickButton(m_strafeGenericHID, 2);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {
    // Set the default command for the drivetrain to drive using the joysticks
    m_drivetrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_drivetrain.arcadeDrive(
                    m_driverController.getLeftY(), m_driverController.getRightX()),
            m_drivetrain));
    m_launcher.setDefaultCommand(
        new RunCommand(
            () ->
                m_launcher.stop(),
            m_launcher));
    m_intake.setDefaultCommand(
        new RunCommand(
            () ->
            m_intake.stop(),
            m_intake)
    );

    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    m_operatorController
        .a()
        .whileTrue(
            new PrepareLaunch(m_launcher)
                .withTimeout(LauncherConstants.kLauncherDelay)
                .andThen(new LaunchNote(m_launcher, 12))
                .handleInterrupt(() -> m_launcher.stop()));
    m_operatorController
        .b().whileTrue(new PrepareLaunch(m_launcher)
                            .withTimeout(LauncherConstants.kLauncherDelay)
                            .andThen(new LaunchNote(m_launcher, 0.4))
                            .handleInterrupt(() -> m_launcher.stop()));
    m_operatorController
        .y()
        .whileTrue(
            new RunCommand(() -> m_intake.setIntakeWheel(-3), m_intake));

    m_operatorController
        .x()
        .whileTrue(
            new RunCommand(() -> m_intake.setIntakeWheel(6), m_intake));
            

    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
    m_operatorController.leftBumper().whileTrue(m_launcher.getIntakeCommand());
    // m_Trigger.whileTrue(
    //         new PrepareLaunch(m_launcher)
    //             .withTimeout(LauncherConstants.kLauncherDelay)
    //             .andThen(new LaunchNote(m_launcher))
    //             .handleInterrupt(() -> m_launcher.stop()));
    // m_button2.whileTrue(m_launcher.getIntakeCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(String m_chooser) {
    // An example command will be run in autonomous
    if(m_chooser == "DriveBack"){
        return Autos.driveBackCommand(m_drivetrain);
    }else if(m_chooser == "ShootAuto"){
        return Autos.ShootAutoCommand(m_launcher);
    }
    else{
    return Autos.exampleAuto(m_drivetrain, m_launcher);
    }
  }
}
