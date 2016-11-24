package CreateMapExtension;

import java.util.Stack;

/**
 * Created by Admin on 11/24/2016.
 */
public class OperationToMapExtensions {

    private static int[][] excuteDrag(int[][] map, Operation operation) {
        int x1 = operation.getP1().logicX;
        int x2 = operation.getP2().logicX;
        int y1 = operation.getP1().logicY;
        int y2 = operation.getP2().logicY;
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

    public static int[][] execute(Stack<Operation> stack) {
        int[][] map = new int[36][36];
        // lam sao de tu mot loat cac thao tac sinh ra
        Operation operation;
        for (int i = 0; i < stack.size(); i++) {
            operation = stack.elementAt(i);
            if (operation.getCode() == OperationConst.dragConveyor) {       //truong hop keo tha
               excuteDrag(map, operation);
            }
            else {      //truong hop dat hinh anh
                map[operation.getP1().logicX][operation.getP1().logicY] = operation.getCode();
            }

        }

        return map;
    }
}
