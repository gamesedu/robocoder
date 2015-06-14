package com.github.drxaos.robocoder.levels.joints;

import com.github.drxaos.robocoder.game.Runner;
import com.github.drxaos.robocoder.levels.waypoints.WaypointsProgram;

public class JointsTestRunner {
    public static void main(String[] args) {
        Runner.run(JointsTest.class, WaypointsProgram.class);
    }
}
