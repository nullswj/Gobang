package gobang;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface GobangConfig
{
    /** 棋盘左上角距窗体左上角的距离 */
    public static final int X = 50;
    public static final int Y = 50;

    /** 棋盘的行数和列数 */
    public static final int ROW = 15;
    public static final int COLUMN = 15;

    /** 棋盘的单元格大小 */
    public static final int SIZE = 35;

    /** 棋子大小 */
    public static final int CHESS_SIZE = 35;

    /** 游戏菜单按钮文字 */
    public static final String START = "开始新游戏";
    public static final String RETRACT = "悔棋";
    public static final String GIVEUP = "认输";

    /** 对战模式 */
    public static final String PVE = "人机对战";
    public static final String PVP = "人人对战";

    /** AI先手 */
    public static final String AI = "AI先手";

    /** 黑棋胜利 */
    public static final String BLACK_WIN = "黑棋胜利!";

    /** 白棋认输,黑棋胜利 */
    public static final String GIVEUP_BLACK_WIN = "白棋认输,黑棋胜利!";

    /** 白棋胜利 */
    public static final String WHITE_WIN = "白棋胜利!";

    /** 黑棋认输,白棋胜利 */
    public static final String GIVEUP_WHITE_WIN = "黑棋认输,白棋胜利!";

    /** 记录棋盘上是否有棋子 */
    public static final int[][] chessArray = new int[ROW][COLUMN];


    public static final char[][] AIchessArray = new char[ROW][COLUMN];

    /** 计算棋盘上的权值 */
    public static final int[][] valuematrix = new int[ROW][COLUMN];

    /** 记录棋子的下棋顺序 */
    public static final ArrayList<Point> orderRecoder = new ArrayList<Point>();

    /** 定义存储棋子相连情况和对应权值的Map集合对象。 */
    public static final HashMap<String,Integer> situationmap = new HashMap<String,Integer>();
}
