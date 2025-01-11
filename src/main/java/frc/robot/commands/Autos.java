// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
// import frc.robot.subsystems.PWMDrivetrain;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;

public class Autos {
  public static Command driveBackCommand(CANDrivetrain drivetrain) {
    /**
     * RunCommand is a helper class that creates a command from a single method, in this case we
     * pass it the arcadeDrive method to drive straight back at half power. We modify that command
     * with the .withTimeout(1) decorator to timeout after 1 second, and use the .andThen decorator
     * to stop the drivetrain after the first command times out
     */
    return Commands.sequence(
      new DriveForTime(drivetrain, 0.85, 0, 2)
    
    );
  }
  public static Command ShootAutoCommand(CANLauncher launcher){
    return Commands.sequence(
    new ShootForTime(launcher, 12, 2)
    );
  }
  // public static Command shootAndScoot(CANDrivetrain m_drivetrain, CANLauncher m_launcher) {
    // return new RunCommand(() -> m_launcher.getOutakeCommand())
    //     .withTimeout(1)
    //     .andThen(new RunCommand(() -> m_drivetrain.arcadeDrive(-0.5, 0)))
    //     .withTimeout(1)
    //     .andThen(new RunCommand(() -> m_drivetrain.arcadeDrive(0, 0)));
    // return new RunCommand(() ->);
  // }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
  public static Command exampleAuto(CANDrivetrain m_drivetrain, CANLauncher m_launcher) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'exampleAuto'");
  }
  
}
