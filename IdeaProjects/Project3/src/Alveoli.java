import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Alveoli extends ActionEntity{
    public Alveoli(String id, Point position, int actionPeriod,
                   List<PImage> images){
        super(id, position, actionPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                      ImageStore imageStore, EventScheduler scheduler)
    {
        int capillarylimit = 0;
        for (Entity e : world.getEntities()) {
            if (e instanceof Covid) {
                capillarylimit++;
            }
        }
        Optional<Point> openPt = world.findOpenAround(this.position);

        if (openPt.isPresent())
        {
            Capillary fish = new Capillary(Functions.FISH_ID_PREFIX + this.getId(),
                    openPt.get(), Functions.FISH_CORRUPT_MIN +
                    Functions.rand.nextInt(Functions.FISH_CORRUPT_MAX - Functions.FISH_CORRUPT_MIN),
                    imageStore.getImageList(Functions.FISH_KEY));
            world.addEntity(fish);
            fish.scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore),
                this.getActionPeriod());
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

    protected int getAnimationPeriod() {
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
