package CreateMapExtension;

import java.awt.*;

/**
 * Created by Nhat on 23/11/2016.
 */
public class LogicPoint extends Point {
    public static final int baseX = 535;
    public static final int baseY = -282;
    public int logicX;
    public int logicY;

    public static LogicPoint convertPointToLogicPoint(Point point) {
        int x = point.x;
        int y = point.y;
        LogicPoint logicPoint = new LogicPoint(point.x, point.y);
        logicPoint.x = point.x;
        logicPoint.y = point.y;
        logicPoint.logicX = (2 * (y - baseY) + (x - baseX))/72;
        logicPoint.logicY = (2 * (y - baseY) - (x - baseX))/72;
        return logicPoint;
    }

    public LogicPoint() {
    }

    public LogicPoint(Point p) {
        super(p);
    }

    public LogicPoint(int x, int y) {
        super(x, y);
    }

//    public static void main(String[] args) {
//        int a = 200;
//        int b = 300;
//        Point p = new Point(a,b);
//        LogicPoint lp = LogicPoint.convertPointToLogicPoint(p);
//        System.out.println("convert(" + a + "," + b + ")" + lp.logicX + "," + lp.logicY);
//    }
}
