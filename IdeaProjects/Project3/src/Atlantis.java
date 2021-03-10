import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Atlantis extends ActionEntity{
    public Atlantis(String id, Point position, int actionPeriod,
                    List<PImage> images){
        super(id, position, actionPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler) {
        int crabLimit = 0;
        for (Entity e : world.getEntities()) {
            if (e instanceof Crab) {
                crabLimit++;
            }
        }
        Optional<Point> openPt = world.findOpenAround(this.position);

        if (openPt.isPresent() && crabLimit < 17)
        {
            CovidFactory covidFactory = new CovidFactory();
            Entity covid = covidFactory.createEntity(this.getId() + Functions.CRAB_ID_SUFFIX,
                    openPt.get(), this.getActionPeriod() / Functions.CRAB_PERIOD_SCALE,
                    Functions.CRAB_ANIMATION_MIN +
                            Functions.rand.nextInt(Functions.CRAB_ANIMATION_MAX - Functions.CRAB_ANIMATION_MIN),
                    imageStore.getImageList(Functions.CRAB_KEY));
            world.addEntity(covid);
            covid.scheduleActions(scheduler, world, imageStore);
        }

//        Point pos = this.position;  // store current position before removing
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        Crab crab = new Crab(this.getId() + Functions.CRAB_ID_SUFFIX,
//                pos, this.getActionPeriod() / Functions.CRAB_PERIOD_SCALE,
//                Functions.CRAB_ANIMATION_MIN +
//                        Functions.rand.nextInt(Functions.CRAB_ANIMATION_MAX - Functions.CRAB_ANIMATION_MIN),
//                imageStore.getImageList(Functions.CRAB_KEY));
//
//        world.addEntity(crab);
//        crab.scheduleActions(scheduler, world, imageStore);
        }

        protected int getAnimationPeriod () {
            throw new UnsupportedOperationException(
                    String.format("getAnimationPeriod not supported for %s",
                            this.getClass()));
        }

    /*public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore),
                this.actionPeriod);
    }*/

}
