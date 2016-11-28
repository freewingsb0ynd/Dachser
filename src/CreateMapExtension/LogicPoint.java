package CreateMapExtension;

import java.awt.*;

/**
 * Created by Nhat on 23/11/2016.
 */
public class LogicPoint {
    public static final int baseX = 578;
    public static final int baseY = -319;
    public int logicX;
    public int logicY;

    public static LogicPoint convertPointToLogicPoint(Point point) {
        int x = point.x;
        int y = point.y;
        LogicPoint logicPoint = new LogicPoint(point.x, point.y);
        logicPoint.logicX = (2 * (y - baseY) + (x - baseX))/72;
        logicPoint.logicY = (2 * (y - baseY) - (x - baseX))/72;
        return logicPoint;
    }

    public LogicPoint(){
        this.logicX = 0;
        this.logicY = 0;
    }
    public LogicPoint(int logicX, int logicY) {
        this.logicX = logicX;
        this.logicY = logicY;
    }

    public Point convertToPoint(){
        Point point = new Point();
        point.x = baseX + 36 * (logicX - logicY);
        point.y = baseY + 18 * (logicX + logicY);
        return point;
    }
    public int getLogicX() {
        return logicX;
    }

    public int getLogicY() {
        return logicY;
    }

//    public static void main(String[] args) {
//        int a = 200;
//        int b = 300;
//        Point p = new Point(a,b);
//        LogicPoint lp = LogicPoint.convertPointToLogicPoint(p);
//        System.out.println("convert(" + a + "," + b + ")" + lp.logicX + "," + lp.logicY);
//    }
}
