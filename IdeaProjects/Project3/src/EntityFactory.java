import processing.core.PImage;

import java.util.List;

public interface EntityFactory {
    public Entity createEntity(String id, Point position,
                               int actionPeriod, int animationPeriod, List<PImage> images);
}
