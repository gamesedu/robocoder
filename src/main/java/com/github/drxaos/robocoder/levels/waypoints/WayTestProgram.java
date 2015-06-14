package com.github.drxaos.robocoder.levels.waypoints;

import com.github.drxaos.robocoder.game.Runner;
import com.github.drxaos.robocoder.program.AbstractProgram;
import com.github.drxaos.robocoder.program.api.BasicMovement;
import com.github.drxaos.robocoder.program.api.RadarDriver;
import com.github.drxaos.robocoder.program.api.RobotDriver;
import straightedge.geom.KPoint;

import java.util.ArrayList;


public class WayTestProgram extends AbstractProgram {
    public static void main(String[] args) {
        Runner.run(WayTest.class, WayTestProgram.class);
    }

    public void run() {
        while (true) {

            BasicMovement basicMovement = new BasicMovement(bus);
            RobotDriver robotDriverDriver = new RobotDriver(bus);
            RadarDriver radarDriver = new RadarDriver(bus);

            ArrayList<KPoint> way = new ArrayList<KPoint>();
            way.add(new KPoint(0, -10));
            way.add(new KPoint(12, -5));
            way.add(new KPoint(12, 0));
            way.add(new KPoint(0, 0));
            way.add(new KPoint(-12, 0));
            way.add(new KPoint(-12, 5));
            way.add(new KPoint(0, 10));
            way.add(new KPoint(15, 5));
            way.add(new KPoint(15, -5));
            way.add(new KPoint(0, -10));

            for (KPoint wayPoint : way) {
                while (!basicMovement.move(wayPoint, 0.5, 10000)) {
                    basicMovement.stop();
                    KPoint failPoint = radarDriver.getPosition();
                    robotDriverDriver.point(wayPoint);
                    robotDriverDriver.say("Please drag me to that point!");
                    while (true) {
                        KPoint newPoint = radarDriver.getPosition();
                        if (basicMovement.distance(newPoint, failPoint) > 0.2) {
                            break;
                        }
                    }
                }
                robotDriverDriver.say(null);

                if (basicMovement.distance(radarDriver.getPosition(), new KPoint(0, 0)) < 0.3) {
                    basicMovement.stop();
                }
            }
            basicMovement.stop();
        }
    }
}
