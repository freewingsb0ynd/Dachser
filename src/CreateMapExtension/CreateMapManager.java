package CreateMapExtension;

import java.util.Stack;

/**
 * Created by Nhat on 24/11/2016.
 */
// Quan ly phan create map
public class CreateMapManager {
    private Stack<Operation> operationStack;
    private int[][] map;

    private CreateMapManager() {
        operationStack = new Stack<>();
        map = new int[36][36];
    }

    //check keo tha
    private boolean checkdragConveyorX(int[][] map, int x1, int x2, int y) {
        for (int i = x1 + 1 ; i <= x2; i++) {
            if (map[i][y] >= 18 ) {
                return false;
            }
            if (map[i][y] >= 13 && map[i][y] <= 16) {
                return false;
            }
        }
        for (int i = x1 + 1; i <= x2; i++) {
            if (map[i][y] == MapCodeConst.nothing) {
                return true;
            }
        }
        return false;
    }

    private boolean checkdragConveyorY(int[][] map, int x, int y1, int y2) {
        for (int i = y1; i <= y2; i++) {
            if (map[x][i] >= 18) {
                return false;
            }
            if (map[x][i] >= 13 && map[x][i] <= 16) {
                return false;
            }
        }
        for (int i = y1; i <= y2; i++) {
            if (map[x][i] == MapCodeConst.nothing) {
                return true;
            }
        }
        return false;
    }

    //check thao tac de dua vao stack
    public boolean checkOperation(Operation operation, int[][] map) {
        int x1 = operation.getP1().logicX;
        int y1 = operation.getP1().logicY;
        int x2 = operation.getP2().logicX;
        int y2 = operation.getP2().logicY;
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > 35 || x2 > 35 || y1 > 35 || y2 > 35) {
            return false;
        }
        if (operation.getCode() == OperationConst.dragConveyor) {
            if (map[x1][y1] == 0 || map[x1][y1] > 17) {
                return false;
            }
            if (y1 == y2) {
                if (x1 < x2) {
                    return checkdragConveyorX(map, x1, x2, y1);
                } else {
                    return checkdragConveyorX(map, x2, x1, y1);
                }
            } else {
                if (y1 < y2) {
                    return checkdragConveyorY(map, x1, y1, y2);
                } else {
                    return checkdragConveyorY(map, x1, y2, y1);
                }
            }
        } else if (map[x1][y1] != MapCodeConst.nothing) {
            return false;
        } else {
            return true;
        }
    }

//    public boolean pushOprationToStack(Operation operation, int[][] map) {
//        if (checkOperation(operation, map) == true) {
//            operationStack.push(operation);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
