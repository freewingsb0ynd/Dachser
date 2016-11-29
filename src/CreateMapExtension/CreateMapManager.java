package CreateMapExtension;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Nhat on 24/11/2016.
 */
// Quan ly phan create map
public class CreateMapManager {
    private Stack<Operation> operationStack;
    private int[][] map;

    public CreateMapManager() {
        operationStack = new Stack<>();
        map = new int[36][36];
    }

    public int[][] getMap() {
        return map;
    }

    //check keo tha
    private boolean checkdragConveyor(int[][] map, int x1, int x2, int y1, int y2) {
        if (y1 == y2) {
            if (x1 < x2) {
                for (int i = x1 + 1; i <= x2; i++) {
                    if (map[i][y1] >= 13) {
                        return false;
                    }
                }
                for (int i = x1; i < x2; i++) {
                    if (map[i][y1] != MapCodeConst.nothing && map[i + 1][y1] != MapCodeConst.nothing) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = x1 - 1; i >= x2; i--) {
                    if (map[i][y1] >= 13) {
                        return false;
                    }
                }
                for (int i = x1; i > x2; i--) {
                    if (map[i][y1] != MapCodeConst.nothing && map[i - 1][y1] != MapCodeConst.nothing) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            if (y1 < y2) {
                for (int i = y1 + 1; i <= y2; i++) {
                    if (map[x1][i] >= 13) {
                        return false;
                    }
                }
                for (int i = y1; i < y2; i++) {
                    if (map[x1][i] != MapCodeConst.nothing && map[x1][i + 1] != MapCodeConst.nothing) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = y1 - 1; i >= y2; i--) {
                    if (map[x1][i] >= 13) {
                        return false;
                    }
                }
                for (int i = y1; i > y2; i--) {
                    if (map[x1][i] != MapCodeConst.nothing && map[x1][i - 1] != MapCodeConst.nothing) {
                        return false;
                    }
                }
                return true;
            }
        }

    }

    //check thao tac de dua vao stack
    public boolean checkValidOperation(Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int x2 = operation.getP2().getLogicX();
        int y2 = operation.getP2().getLogicY();
        if (operationStack.isEmpty()) {
            return operation.getCode() == OperationConst.setSource;
        }
        switch (operation.getCode()) {
            case OperationConst.none:
                return true;
            case OperationConst.dragConveyor:
                if (map[x1][y1] == 0 || map[x1][y1] > 17) {
                    return false;
                } else {
                    return checkdragConveyor(map, x1, x2, y1, y2);
                }
            case OperationConst.clickDelete:
                return true;
            case OperationConst.clickSave:
                return true;
            case OperationConst.clickUndo:
                return true;
            case OperationConst.clickEraser:
                if (map[x1][y1] != MapCodeConst.nothing && map[x1][y1] <= 17) {
                    return false;
                } else return true;
            case OperationConst.setPlane:
            case OperationConst.setShip:
            case OperationConst.setTruck:
                if (map[x1][y1] != MapCodeConst.nothing || map[x1 + 1][y1] != MapCodeConst.nothing
                        || map[x1][y1 - 1] != MapCodeConst.nothing || map[x1 + 1][y1 - 1] != MapCodeConst.nothing) {
                    return false;
                }
                if (map[x1][y1 + 1] == MapCodeConst.endUp || map[x1 + 1][y1 + 1] == MapCodeConst.endUp) {
                    return true;
                }
                if (map[x1][y1 - 2] == MapCodeConst.endDown || map[x1 + 1][y1 - 2] == MapCodeConst.endDown) {
                    return true;
                }
                if (map[x1 - 1][y1] == MapCodeConst.endRight || map[x1 - 1][y1 - 1] == MapCodeConst.endRight) {
                    return true;
                }
                if (map[x1 + 2][y1] == MapCodeConst.endLeft || map[x1 + 2][y1 - 1] == MapCodeConst.endLeft) {
                    return true;
                }
                return false;
            case OperationConst.setSource:
                return false;

            default:
                if (map[x1][y1] != MapCodeConst.nothing) {
                    return false;
                } else return true;

        }

    }

    private void executeDrag(Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int x2 = operation.getP2().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int y2 = operation.getP2().getLogicY();
        if (y1 == y2) {
            if (x1 < x2) {
                if (map[x1][y1] == MapCodeConst.source) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.source;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchRight;
                } else {
                    map[x1][y1] = MapCodeConst.switchRight;
                }
                if (x2 - x1 > 1) {                          //xet diem giua
                    for (int i = x1 + 1; i < x2; i++) {
                        if (map[i][y1] == MapCodeConst.nothing) {
                            map[i][y1] = MapCodeConst.conveyorRight;
                        } else {
                            map[i][y1] = MapCodeConst.switchRight;
                        }
                    }
                }

                if (map[x2][y1] == MapCodeConst.nothing) {  //xet diem cuoi
                    map[x2][y1] = MapCodeConst.endRight;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else {
                    map[x2][y1] = map[x2][y1];
                }

            } else {
                if (map[x1][y1] == MapCodeConst.source) {    //xet diem dau
                    map[x1][y1] = MapCodeConst.source;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchLeft;
                } else {
                    map[x1][y1] = MapCodeConst.switchLeft;
                }

                if (x1 - x2 > 1) {                          //xet diem giua
                    for (int i = x1 - 1; i > x2; i--) {
                        if (map[i][y1] == MapCodeConst.nothing) {
                            map[i][y1] = MapCodeConst.conveyorLeft;
                        } else {
                            map[i][y1] = MapCodeConst.switchLeft;
                        }
                    }
                }

                if (map[x2][y1] == MapCodeConst.nothing) {  //xet diem cuoi
                    map[x2][y1] = MapCodeConst.endLeft;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else {
                    map[x2][y1] = map[x2][y1];
                }
            }
        } else {
            if (y1 < y2) {
                if (map[x1][y1] == MapCodeConst.source) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.source;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchDown;
                } else {
                    map[x1][y1] = MapCodeConst.switchDown;
                }

                if (y2 - y1 > 1) {                          //xet diem giua
                    for (int i = y1 + 1; i < y2; i++) {
                        if (map[x1][i] == MapCodeConst.nothing) {
                            map[x1][i] = MapCodeConst.conveyorDown;
                        } else {
                            map[x1][i] = MapCodeConst.switchDown;
                        }
                    }
                }

                if (map[x1][y2] == MapCodeConst.nothing) {  //xet diem cuoi
                    map[x1][y2] = MapCodeConst.endDown;
                } else if (map[x1][y2] >= 1 && map[x1][y2] <= 4) {
                    map[x1][y2] += 8;
                } else {
                    map[x1][y2] = map[x1][y2];
                }

            } else {
                if (map[x1][y1] == MapCodeConst.source) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.source;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchUp;
                } else {
                    map[x1][y1] = MapCodeConst.switchUp;
                }

                if (y1 - y2 > 1) {                          //xet diem giua
                    for (int i = y1 - 1; i > y2; i--) {
                        if (map[x1][i] == MapCodeConst.nothing) {
                            map[x1][i] = MapCodeConst.conveyorUp;
                        } else {
                            map[x1][i] = MapCodeConst.switchUp;
                        }
                    }
                }

                if (map[x1][y2] == MapCodeConst.nothing) {  //xet diem cuoi
                    map[x1][y2] = MapCodeConst.endUp;
                } else if (map[x1][y2] >= 1 && map[x1][y2] <= 4) {
                    map[x1][y2] += 8;
                } else {
                    map[x1][y2] = map[x1][y2];
                }

            }
        }

    }

    private void executeUndo(Stack<Operation> stack) {
        map = new int[36][36];
        Operation op;
        if (stack.empty()) {
            return;
        }

        for (int i = 0; i < stack.size(); i++) {
            op = stack.elementAt(i);
            this.newMapFromValidOperation(op);
        }
    }

    private void newMapFromValidOperation(Operation operation) {
        //int[][] buffermap = new int[36][36];
        int code = operation.getCode();
        int x1 = operation.getP1().getLogicX();
        int y1 = operation.getP1().getLogicY();
//        int x2 = operation.getP2().getLogicX();
//        int y2 = operation.getP2().getLogicY();
        // sinh ra map tu map cu
        switch (code) {
            case OperationConst.none:
                return;
            case OperationConst.dragConveyor:
                executeDrag(operation);
                return;
            case OperationConst.clickDelete:
                map = new int[36][36];
                return;
            case OperationConst.clickEraser:
                map[x1][y1] = MapCodeConst.nothing;
                return;
            case OperationConst.clickSave:
                //de sau lam
                return;
            case OperationConst.clickUndo:
                try {
                    operationStack.pop();
                    executeUndo(operationStack);
                } catch (EmptyStackException e) {

                }

                return;
            default:
                map[x1][y1] = code;
                return;
        }
    }

    public void execute(Operation operation) {
        if (checkValidOperation(operation) == true) {
            int code = operation.getCode();
            newMapFromValidOperation(operation);
            if (code != OperationConst.clickSave && code != OperationConst.clickUndo && code != OperationConst.none) {
                operationStack.push(operation);
            }
            return;
        }
    }
}
