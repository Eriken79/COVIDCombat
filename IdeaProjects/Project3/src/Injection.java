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
        int antibodyLimit = 0;
        for (Entity e : world.getEntities()) {
            if (e instanceof Antibody) {
                antibodyLimit++;
            }
        }
        if(antibodyLimit < 4) {
            Injection injection = new Injection(position, imageStore.getImageList(Functions.INJECTION_KEY));
            world.addEntity(injection);
            injection.scheduleActions(scheduler, world, imageStore);
            long nextPeriod = this.getActionPeriod();
        }
        //add injection, then consume injection to add antibody , add blood around injection,
        int i = 0;
        while (i < 9) {
            Optional<Entity> InjectionTarget = world.findNearest(
                    this.position, Covid.class);
            Optional<Point> openPt = world.findOpenAround(this.position);

            if (openPt.isPresent() && antibodyLimit < 4) {
                Freeze freeze = new Freeze(Functions.FISH_ID_PREFIX + this.getId(),
                        openPt.get(),
                        imageStore.getImageList(Functions.FREEZE_KEY));
                world.addEntity(freeze);
                freeze.scheduleActions(scheduler, world, imageStore);
            }
            i++;
        }
        if(antibodyLimit < 4) {
            Point pos = this.position;
            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);
            Antibody octo = new Antibody(this.getId() + Functions.OCTO_ID_SUFFIX, 5, pos, 3000, this.getAnimationPeriod(), imageStore.getImageList(Functions.OCTO_KEY));
            world.addEntity(octo);
            octo.scheduleActions(scheduler, world, imageStore);
        }

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
