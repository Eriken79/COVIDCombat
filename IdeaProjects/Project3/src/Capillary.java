import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class Capillary extends Movable{//ActionEntity{
    private PathingStrategy strategy;
    public Capillary(String id, Point position, int actionPeriod,
                     List<PImage> images){
        super(id, position, images, actionPeriod, 0);
        this.strategy = new SingleStepPathingStrategy();
    }


    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fishTarget = world.findNearest(
                this.position, Hero.class);
        long nextPeriod = this.getActionPeriod();
        if (fishTarget.isPresent())
        {
            //System.out.println("present");
            Point tgtPos = fishTarget.get().position;

            if (this.moveTo(world, fishTarget.get(), scheduler))
            {
                nextPeriod += this.getActionPeriod();
            }
        }
        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore),
                nextPeriod);
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

    protected void moveToEntity(WorldModel world,
                                Entity target, EventScheduler scheduler){}

    protected PathingStrategy getStrategy(){return strategy;}

    protected int getAnimationPeriod() {
        /*throw new UnsupportedOperationException(
            String.format("getAnimationPeriod not supported for %s",
                    this.getClass()));}*/
        return 0;}

    /*protected void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore),
                this.getActionPeriod());
    }*/
}