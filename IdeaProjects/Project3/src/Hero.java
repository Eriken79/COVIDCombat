import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Hero extends Movable{
    public Hero(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)     {
        Optional<Entity> heroTarget = world.findNearest(
                this.position, Crab.class);
        long nextPeriod = this.getActionPeriod();

        if (heroTarget.isPresent())
        {
            Point tgtPos = heroTarget.get().position;

            if (this.moveTo(world, heroTarget.get(), scheduler))
            {
//                Quake quake = new Quake(tgtPos,
//                        imageStore.getImageList(Functions.QUAKE_KEY));
//
//                world.addEntity(quake);
                nextPeriod += this.getActionPeriod();
                //quake.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore),
                nextPeriod);
    }

    @Override
    protected Point nextPosition(WorldModel world, Point destPos) {
        return position;
    }

    @Override
    protected void moveToEntity(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
    }
}
