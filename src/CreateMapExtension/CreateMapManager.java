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
    public boolean checkOperation(Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int x2 = operation.getP2().getLogicX();
        int y2 = operation.getP2().getLogicY();
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > 35 || x2 > 35 || y1 > 35 || y2 > 35) {
            return false;
        }
        switch (operation.getCode()) {
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
//            case OperationConst.clickEraser:
//                if (map[operation.getP1().getLogicX()][operation.getP1().getLogicY()] ){
//
//                }
        }
        return true;
    }

    public int[][] getMap() {
        map = execute(operationStack);
        return map;
    }

    private static int[][] excuteDrag(int[][] map, Operation operation) {
        int x1 = operation.getP1().getLogicX();
        int x2 = operation.getP2().getLogicX();
        int y1 = operation.getP1().getLogicY();
        int y2 = operation.getP2().getLogicY();
        if (y1 == y2) {
            if (x1 < x2) {
                if (x2 - x1 > 1) {
                    for (int i = x1 + 1; i < x2; i++) {
                        if (map[i][y1] == MapCodeConst.nothing) {
                            map[i][y1] = MapCodeConst.conveyorRight;
                        } else {
                            map[i][y1] = MapCodeConst.switchRight;
                        }
                    }
                }

                if (map[x1][y1] == 17) {
                    map[x1][y1] = MapCodeConst.conveyorRight;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchRight;
                } else {
                    map[x1][y1] = MapCodeConst.switchRight;
                }

                if (map[x2][y1] == MapCodeConst.nothing) {
                    map[x2][y1] = MapCodeConst.endRight;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else if (map[x2][y1] >= 9 && map[x2][y1] <= 12) {
                    map[x2][y1] -= 4;
                } else {
                    map[x2][y1] = map[x2][y1];
                }

            }
            if (x1 > x2) {
                if (x1 - x2 > 1) {
                    for (int i = x1 - 1; i > x2; i--) {
                        if (map[i][y1] == MapCodeConst.nothing) {
                            map[i][y1] = MapCodeConst.conveyorLeft;
                        } else {
                            map[i][y1] = MapCodeConst.switchLeft;
                        }
                    }
                }

                if (map[x1][y1] == 17) {
                    map[x1][y1] = MapCodeConst.conveyorLeft;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchLeft;
                } else {
                    map[x1][y1] = MapCodeConst.switchLeft;
                }

                if (map[x2][y1] == MapCodeConst.nothing) {
                    map[x2][y1] = MapCodeConst.endLeft;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else if (map[x2][y1] >= 9 && map[x2][y1] <= 12) {
                    map[x2][y1] -= 4;
                } else {
                    map[x2][y1] = map[x2][y1];
                }

            }
        } else {
            if (y1 < y2) {
                if(y2 - y1 > 1) {
                    for (int i = y1 + 1; i < y2; i++) {
                        if (map[x1][i] == MapCodeConst.nothing) {
                            map[x1][i] = MapCodeConst.conveyorDown;
                        } else {
                            map[x1][i] = MapCodeConst.switchDown;
                        }
                    }
                }

                if (map[x1][y1] == 17) {
                    map[x1][y1] = MapCodeConst.conveyorDown;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchDown;
                } else {
                    map[x1][y1] = MapCodeConst.switchDown;
                }
                if (map[x1][y2] == MapCodeConst.nothing) {
                    map[x1][y2] = MapCodeConst.endDown;
                } else if (map[x1][y2] >= 1 && map[x1][y2] <= 4) {
                    map[x1][y2] += 8;
                } else if (map[x1][y2] >= 9 && map[x1][y2] <= 12) {
                    map[x1][y2] -= 4;
                } else {
                    map[x1][y2] = map[x1][y2];
                }

            }
            if (y1 > y2) {
                if(y1 - y2 > 1) {
                    for (int i = y1 - 1; i > y2; i--) {
                        if (map[x1][i] == MapCodeConst.nothing) {
                            map[x1][i] = MapCodeConst.conveyorUp;
                        } else {
                            map[x1][i] = MapCodeConst.switchUp;
                        }
                    }
                }
                if (map[x1][y1] == 17) {
                    map[x1][y1] = MapCodeConst.conveyorUp;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.nonswitchUp;
                } else {
                    map[x1][y1] = MapCodeConst.switchUp;
                }
                if (map[x1][y2] == MapCodeConst.nothing) {
                    map[x2][y1] = MapCodeConst.endUp;
                } else if (map[x1][y2] >= 1 && map[x1][y2] <= 4) {
                    map[x1][y2] += 8;
                } else if (map[x1][y2] >= 9 && map[x1][y2] <= 12) {
                    map[x1][y2] -= 4;
                } else {
                    map[x1][y2] = map[x1][y2];
                }

            }
        }
        return map;

    }

    private static int[][] execute(Stack<Operation> stack) {
        int[][] map = new int[36][36];
        // lam sao de tu mot loat cac thao tac sinh ra
        Operation operation;
        for (int i = 0; i < stack.size(); i++) {
            operation = stack.elementAt(i);
            if (operation.getCode() == OperationConst.dragConveyor) {       //truong hop keo tha
                excuteDrag(map, operation);
            }
            else {      //truong hop dat hinh anh
                map[operation.getP1().getLogicX()][operation.getP1().getLogicY()] = operation.getCode();
            }

        }

        return map;
    }

    public boolean pushOprationToStack(Operation operation) {
        if (checkOperation(operation) == true) {
            operationStack.push(operation);
            this.getMap();
            return true;
        } else {
            return false;
        }
    }

}
