package GamePlay.GameObject;

/**
 * Created by Hoangelato on 22/11/2016.
 */
public class ConveyorFixedSwitch extends ConveyorFixed {
    Direction direction;

    public ConveyorFixedSwitch(int posX, int posY) {
        super(posX, posY);
    }


    public Conveyor getConveyorNonSwitchByDirection(Direction d){
        switch (d){
            case UP:
                return new ConveyorSwitchUp(posX,posY);
            case DOWN:
                return new ConveyorSwitchDown(posX,posY);
            case LEFT:
                return new ConveyorSwitchLeft(posX,posY);
            case RIGHT:
                return new ConveyorSwitchRight(posX,posY);
            default:
                return new Conveyor(posX,posY);
        }

    }

}
