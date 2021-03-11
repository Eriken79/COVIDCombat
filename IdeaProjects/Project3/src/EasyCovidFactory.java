import processing.core.PImage;

import java.util.List;

public class EasyCovidFactory {
    public EasyCovidFactory(){}

    public Entity createEntity(String id, Point position,
                               int actionPeriod, int animationPeriod, List<PImage> images){
        //health would be the last parameter
        return new Covid(id, position, actionPeriod + 1000, animationPeriod, images);
    }
}