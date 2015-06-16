package com.github.drxaos.robocoder.game.equipment;

import com.github.drxaos.robocoder.game.Game;
import com.github.drxaos.robocoder.game.actors.Robot;
import straightedge.geom.KPoint;

public class RadarEquipment implements Equipment {

    public Double getAngle(Robot robot, Game game) {
        return robot.getModel().getAngle();
    }

    public KPoint getPosition(Robot robot, Game game) {
        return robot.getModel().getPosition();
    }

    public void communicate(Robot robot, Game game) {
        String req = robot.getBus().getRequest();
        if ("radar::angle".equals(req)) {
            robot.getBus().removeRequest();
            robot.getBus().writeResponse("" + getAngle(robot, game));
        } else if ("radar::position".equals(req)) {
            robot.getBus().removeRequest();
            KPoint position = getPosition(robot, game);
            robot.getBus().writeResponse("" + position.getX() + ":" + position.getY());
        } else if ("radar::time".equals(req)) {
            robot.getBus().removeRequest();
            Long time = game.getTime();
            robot.getBus().writeResponse("" + time);
        }
    }

    public void applyPhysics(Robot robot, Game game) {

    }
}
