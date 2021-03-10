import processing.core.PImage;
import java.util.List;
public class CovidFactory implements EntityFactory {
    public Entity createEntity(String id, Point position,
                               int actionPeriod, int animationPeriod, List<PImage > images){
        Entity covid;
        if (VirtualWorld.Difficulty.equals("hard")){
            HardCovidFactory hardCovid = new HardCovidFactory();
            covid = hardCovid.createEntity(id, position, actionPeriod, animationPeriod, images);
        }
        else{
            EasyCovidFactory easyCovid = new EasyCovidFactory();
            covid = easyCovid.createEntity(id, position, actionPeriod, animationPeriod, images);
        }
        return covid;
    }
}
