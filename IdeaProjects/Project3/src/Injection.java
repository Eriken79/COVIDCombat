import processing.core.PImage;

import java.util.List;
import java.util.Optional;

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
        long nextPeriod = this.getActionPeriod();
        //add injection, then consume injection to add antibody, add blood around injection,
        Point pos = this.position;
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);
        OctoNotFull octo = new OctoNotFull(this.getId() + Functions.OCTO_ID_SUFFIX, 5, pos, this.getActionPeriod(), this.getAnimationPeriod(), imageStore.getImageList(Functions.OCTO_KEY));
        world.addEntity(octo);
        octo.scheduleActions(scheduler, world, imageStore);

//        Optional<Point> openPt = world.findOpenAround(this.position);
//
//        if (openPt.isPresent())
//        {
//            Crab crab = new Crab(this.getId() + Functions.CRAB_ID_SUFFIX,
//                    openPt.get(), this.getActionPeriod() / Functions.CRAB_PERIOD_SCALE,
//                    Functions.CRAB_ANIMATION_MIN +
//                            Functions.rand.nextInt(Functions.CRAB_ANIMATION_MAX - Functions.CRAB_ANIMATION_MIN),
//                    imageStore.getImageList(Functions.CRAB_KEY));
//            world.addEntity(crab);
//            crab.scheduleActions(scheduler, world, imageStore);
//        }
//
//        scheduler.scheduleEvent(this,
//                new Activity(this, world, imageStore),
//                this.getActionPeriod());
//    }
    }
}
