import processing.core.PImage;

import java.util.List;

public class Hero extends Movable{
    public Hero(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }

    @Override
    protected Point nextPosition(WorldModel world, Point destPos) {
        return null;
    }

    @Override
    protected void moveToEntity(WorldModel world, Entity target, EventScheduler scheduler) {

    }
}
