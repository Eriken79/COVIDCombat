import processing.core.PImage;

import java.util.List;

public class Injection extends Stationary{
//    public Injection(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images, int animationRepeatCount) {
//        super(id, position, actionPeriod, animationPeriod, images, animationRepeatCount);
//    }
    public Injection(Point position, List<PImage> images){
        super(Functions.INJECTION_ID, position, Functions.INJECTION_ACTION_PERIOD, Functions.INJECTION_ANIMATION_PERIOD, images, Functions.INJECTION_ANIMATION_REPEAT_COUNT);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Injection injection = new Injection(position, imageStore.getImageList(Functions.INJECTION_KEY));
        world.addEntity(injection);
        injection.scheduleActions(scheduler, world, imageStore);
        //add injection, then consume injection to add antibody, add blood around injection,
        Point pos = this.position;
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);
        OctoNotFull octo = new OctoNotFull(this.getId(),  );
        world.addEntity(octo);

    }
}
