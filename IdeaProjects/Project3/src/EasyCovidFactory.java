import processing.core.PImage;

import java.util.List;

public class EasyCovidFactory {
    public EasyCovidFactory(){}

    public Entity createEntity(String id, Point position,
                               int actionPeriod, int animationPeriod, List<PImage> images){
        System.out.println("easyCovid");
        //health would be the last parameter
        return new Crab(id, position, actionPeriod + 3000, animationPeriod, images);
    }
}