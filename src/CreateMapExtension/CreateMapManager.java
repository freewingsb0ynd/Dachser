package CreateMapExtension;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * Created by Nhat on 24/11/2016.
 */
// Quan ly phan create map
public class CreateMapManager {
    private Stack<Operation> operationStack;
    private int[][] map;
    private int time;
    private int numBox;

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
                    if (map[i][y1] != MapCodeConst.NOTHING && map[i + 1][y1] != MapCodeConst.NOTHING) {
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
                    if (map[i][y1] != MapCodeConst.NOTHING && map[i - 1][y1] != MapCodeConst.NOTHING) {
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
                    if (map[x1][i] != MapCodeConst.NOTHING && map[x1][i + 1] != MapCodeConst.NOTHING) {
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
                    if (map[x1][i] != MapCodeConst.NOTHING && map[x1][i - 1] != MapCodeConst.NOTHING) {
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
            return operation.getCode() == OperationConst.SET_SOURCE;
        }
        switch (operation.getCode()) {
            case OperationConst.NONE:
                return true;
            case OperationConst.DRAG_CONVEYOR:
                if (map[x1][y1] == 0 || map[x1][y1] > 17) {
                    return false;
                } else {
                    return checkdragConveyor(map, x1, x2, y1, y2);
                }
            case OperationConst.CLICK_DELETE:
                return true;
            case OperationConst.CLICK_SAVE:
                return true;
            case OperationConst.CLICK_UNDO:
                return true;
            case OperationConst.CLICK_ERASER:
                if (map[x1][y1] != MapCodeConst.NOTHING && map[x1][y1] <= 17) {
                    return false;
                } else return true;
            case OperationConst.SET_PLANE:
            case OperationConst.SET_SHIP:
            case OperationConst.SET_TRUCK:
                if (map[x1][y1] != MapCodeConst.NOTHING || map[x1 + 1][y1] != MapCodeConst.NOTHING
                        || map[x1][y1 - 1] != MapCodeConst.NOTHING || map[x1 + 1][y1 - 1] != MapCodeConst.NOTHING) {
                    return false;
                }
                if (map[x1][y1 + 1] == MapCodeConst.END_UP || map[x1 + 1][y1 + 1] == MapCodeConst.END_UP) {
                    return true;
                }
                if (map[x1][y1 - 2] == MapCodeConst.END_DOWN || map[x1 + 1][y1 - 2] == MapCodeConst.END_DOWN) {
                    return true;
                }
                if (map[x1 - 1][y1] == MapCodeConst.END_RIGHT || map[x1 - 1][y1 - 1] == MapCodeConst.END_RIGHT) {
                    return true;
                }
                if (map[x1 + 2][y1] == MapCodeConst.END_LEFT || map[x1 + 2][y1 - 1] == MapCodeConst.END_LEFT) {
                    return true;
                }
                return false;
            case OperationConst.SET_SOURCE:
                return false;

            default:
                if (map[x1][y1] != MapCodeConst.NOTHING) {
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
                if (map[x1][y1] == MapCodeConst.SOURCE) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.SOURCE;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.NONSWITCH_RIGHT;
                } else {
                    map[x1][y1] = MapCodeConst.SWITCH_RIGHT;
                }
                if (x2 - x1 > 1) {                          //xet diem giua
                    for (int i = x1 + 1; i < x2; i++) {
                        if (map[i][y1] == MapCodeConst.NOTHING) {
                            map[i][y1] = MapCodeConst.CONVEYOR_RIGHT;
                        } else {
                            map[i][y1] = MapCodeConst.SWITCH_RIGHT;
                        }
                    }
                }

                if (map[x2][y1] == MapCodeConst.NOTHING) {  //xet diem cuoi
                    map[x2][y1] = MapCodeConst.END_RIGHT;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else {
                    map[x2][y1] = map[x2][y1];
                }

            } else {
                if (map[x1][y1] == MapCodeConst.SOURCE) {    //xet diem dau
                    map[x1][y1] = MapCodeConst.SOURCE;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.NONSWITCH_LEFT;
                } else {
                    map[x1][y1] = MapCodeConst.SWITCH_LEFT;
                }

                if (x1 - x2 > 1) {                          //xet diem giua
                    for (int i = x1 - 1; i > x2; i--) {
                        if (map[i][y1] == MapCodeConst.NOTHING) {
                            map[i][y1] = MapCodeConst.CONVEYOR_LEFT;
                        } else {
                            map[i][y1] = MapCodeConst.SWITCH_LEFT;
                        }
                    }
                }

                if (map[x2][y1] == MapCodeConst.NOTHING) {  //xet diem cuoi
                    map[x2][y1] = MapCodeConst.END_LEFT;
                } else if (map[x2][y1] >= 1 && map[x2][y1] <= 4) {
                    map[x2][y1] += 8;
                } else {
                    map[x2][y1] = map[x2][y1];
                }
            }
        } else {
            if (y1 < y2) {
                if (map[x1][y1] == MapCodeConst.SOURCE) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.SOURCE;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.NONSWITCH_DOWN;
                } else {
                    map[x1][y1] = MapCodeConst.SWITCH_DOWN;
                }

                if (y2 - y1 > 1) {                          //xet diem giua
                    for (int i = y1 + 1; i < y2; i++) {
                        if (map[x1][i] == MapCodeConst.NOTHING) {
                            map[x1][i] = MapCodeConst.CONVEYOR_DOWN;
                        } else {
                            map[x1][i] = MapCodeConst.SWITCH_DOWN;
                        }
                    }
                }

                if (map[x1][y2] == MapCodeConst.NOTHING) {  //xet diem cuoi
                    map[x1][y2] = MapCodeConst.END_DOWN;
                } else if (map[x1][y2] >= 1 && map[x1][y2] <= 4) {
                    map[x1][y2] += 8;
                } else {
                    map[x1][y2] = map[x1][y2];
                }

            } else {
                if (map[x1][y1] == MapCodeConst.SOURCE) {   //xet diem dau
                    map[x1][y1] = MapCodeConst.SOURCE;
                } else if (map[x1][y1] >= 13 && map[x1][y1] <= 16) {
                    map[x1][y1] = MapCodeConst.NONSWITCH_UP;
                } else {
                    map[x1][y1] = MapCodeConst.SWITCH_UP;
                }

                if (y1 - y2 > 1) {                          //xet diem giua
                    for (int i = y1 - 1; i > y2; i--) {
                        if (map[x1][i] == MapCodeConst.NOTHING) {
                            map[x1][i] = MapCodeConst.CONVEYOR_UP;
                        } else {
                            map[x1][i] = MapCodeConst.SWITCH_UP;
                        }
                    }
                }

                if (map[x1][y2] == MapCodeConst.NOTHING) {  //xet diem cuoi
                    map[x1][y2] = MapCodeConst.END_UP;
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
            case OperationConst.NONE:
                return;
            case OperationConst.DRAG_CONVEYOR:
                executeDrag(operation);
                return;
            case OperationConst.CLICK_DELETE:
                map = new int[36][36];
                return;
            case OperationConst.CLICK_ERASER:
                map[x1][y1] = MapCodeConst.NOTHING;
                return;
            case OperationConst.CLICK_SAVE:
                numBox = Integer.parseInt(JOptionPane.showInputDialog("Input number of boxes:"));
                time = Integer.parseInt(JOptionPane.showInputDialog("Input time(seconds):"));
                File file = new File("map1.txt");
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(time+"\r\n");
                    bw.write(numBox+"\r\n");
                    for(int i = 0; i < 36; i++){
                        for(int j = 0; j < 36; j++){
                            bw.write(map[i][j]+",");
                        }
                        bw.newLine();
                    }
                    bw.close();

                } catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("abc");
                return;
            case OperationConst.CLICK_UNDO:
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
            if (code != OperationConst.CLICK_SAVE && code != OperationConst.CLICK_UNDO && code != OperationConst.NONE) {
                operationStack.push(operation);
            }
            return;
        }
    }
}
