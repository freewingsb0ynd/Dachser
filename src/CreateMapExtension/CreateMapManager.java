package CreateMapExtension;

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

    //check keo tha
    private boolean checkdragConveyorX(int[][] map, int x1, int x2, int y) {
        for (int i = x1 + 1; i <= x2; i++) {
            if (map[i][y] >= 13) {
                return false;
            }
        }
        for (int i = x1; i < x2; i++) {
            if (map[i][y] != MapCodeConst.nothing && map[i + 1][y] != MapCodeConst.nothing) {
                return false;
            }
        }
        return true;
    }

    private boolean checkdragConveyorY(int[][] map, int x, int y1, int y2) {
        for (int i = y1 + 1; i <= y2; i++) {
            if (map[x][i] >= 13) {
                return false;
            }
        }
        for (int i = y1; i < y2; i++) {
            if (map[x][i] != MapCodeConst.nothing && map[x][i + 1] != MapCodeConst.nothing) {
                return false;
            }
        }
        return true;
    }

    //check thao tac de dua vao stack
    public boolean checkValidOperation(Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int x2 = operation.getP2().getLogicX();
        int y2 = operation.getP2().getLogicY();
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > 35 || x2 > 35 || y1 > 35 || y2 > 35) {
            return false;
        }
        switch (operation.getCode()) {
            case OperationConst.none:
                return true;
            case OperationConst.dragConveyor:
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
            default:
                if (map[x1][x2] != MapCodeConst.nothing) {
                    return false;
                } else return true;

        }

    }

    public int[][] getMap() {
        return map;
    }

    private void executeDrag(Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int x2 = operation.getP2().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int y2 = operation.getP2().getLogicY();
        if (y1 == y2) {
            if (x1 < x2) {
                if (map[x1][y1] == MapCodeConst.source) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.conveyorRight;
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
                    map[x1][y1] = MapCodeConst.conveyorLeft;
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
                    map[x1][y1] = MapCodeConst.conveyorDown;
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
                    map[x1][y1] = MapCodeConst.conveyorUp;
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
                    map[x2][y1] = MapCodeConst.endUp;
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
        if (stack == null) {
            return;
        } else {
            Operation op;
            for (int i = 0; i < stack.size(); i++) {
                op = stack.elementAt(i);
                this.newMapFromValidOperation(op);
            }
        }
    }

    private void newMapFromValidOperation(Operation operation) {
        //int[][] buffermap = new int[36][36];
        int code = operation.getCode();
        int x1 = operation.getP1().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int x2 = operation.getP2().getLogicX();
        int y2 = operation.getP2().getLogicY();
        // sinh ra map tu map cu
        if (operationStack == null) {
            if (code != OperationConst.setSource) {
                return;
            } else {
                map[x1][y1] = MapCodeConst.source;
            }
        } else {
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
                    operationStack.pop();
                    this.executeUndo(operationStack);
                    return;
                default:
                    map[x1][y1] = code;
                    return;
            }
        }
    }

    public void execute(Operation operation) {
        if(checkValidOperation(operation) == true) {
            int code = operation.getCode();
            newMapFromValidOperation(operation);
            if (code == OperationConst.clickSave || code == OperationConst.clickUndo || code == OperationConst.none) {
                operationStack.push(operation);
            }
            return;
        }
    }
}
