import processing.core.PImage;

import java.util.List;

public class HardCovidFactory {
    public HardCovidFactory(){}

    public Entity createEntity(String id, Point position,
                               int actionPeriod, int animationPeriod, List<PImage> images){
        return new Covid(id, position, actionPeriod + 1000, animationPeriod, images);
    }
}
