package CreateMapExtension;

import java.awt.*;

/**
 * Created by Admin on 11/24/2016.
 */
public class Operation {
    private int code;
    private Point p1;
    private Point p2;
    Operation(int code, Point p1, Point p2){
        this.code = code;
        this.p1 = p1;
        this.p2 = p2;
    }
    public int getCode(){
        return this.code;
    }
    public Point getP1(){
        return this.p1;
    }
    public Point getP2(){
        return this.p2;
    }
}
