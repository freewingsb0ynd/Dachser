package GameObject;

import java.awt.*;

/**
 * Created by admin on 11/5/2016.
 */
public class BoxReceiver {
    protected Color color;

    public boolean Finish(Color boxColor){
        if(boxColor != this.color){
            //tru diem
            return false;
        }
        //cong diem
        return true;
    }
}
