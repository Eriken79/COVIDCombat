import processing.core.PImage;

import java.util.List;

public class Injection extends Stationary{
    public Injection(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images, int repeatCount) {
        super(id, position, actionPeriod, animationPeriod, images, repeatCount);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }
}
