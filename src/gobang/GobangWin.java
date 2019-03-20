package gobang;

public class GobangWin implements GobangConfig
{
    /**
     * 判断输赢
     * @param r
     * @param c
     * @return
     */
    public static boolean judge(int r, int c)
    {
        if(countH(r,c) >= 5 || countV(r,c) >= 5 || countLB_RU(r,c) >= 5 || countLU_RB(r,c) >= 5)
            return true;
        return false;
    }

    /**
     * 水平方向判断
     * @param r
     * @param c
     * @return
     */
    private static int countH(int r, int c)
    {
        int count = 1;

        for(int c1 = c-1; c1 >= 0; c1--)
        {
            if(chessArray[r][c] == chessArray[r][c1])
                count++;
            else
                break;
        }

        for(int c1 = c+1; c1 < ROW; c1++)
        {
            if(chessArray[r][c] == chessArray[r][c1])
                count++;
            else
                break;
        }

        return count;
    }

    /**
     * 垂直方向判断
     * @param r
     * @param c
     * @return
     */
    private static int countV(int r, int c)
    {
        int count = 1;

        for(int r1 = r-1; r1 >= 0; r1--)
        {
            if(chessArray[r][c] == chessArray[r1][c])
                count++;
            else
                break;
        }

        for(int r1 = r+1; r1 < COLUMN; r1++)
        {
            if(chessArray[r][c] == chessArray[r1][c])
                count++;
            else
                break;
        }
        return count;
    }

    /**
     * 左上方至右下方判断
     * @param r
     * @param c
     * @return
     */
    private static int countLU_RB(int r, int c)
    {
        int count = 1;

        for(int r1 = r-1,c1 = c-1; r1 >= 0 && c1 >= 0; r1--,c1--)
        {
            if(chessArray[r][c] == chessArray[r1][c1])
                count++;
            else
                break;
        }

        for(int r1 = r+1,c1 = c+1; r1 < COLUMN && c1 < COLUMN; r1++, c1++)
        {
            if(chessArray[r][c] == chessArray[r1][c1])
                count++;
            else
                break;
        }
        return count;
    }

    /**
     * 左下方至右上方判断
     * @param r
     * @param c
     * @return
     */
    private static int countLB_RU(int r, int c)
    {
        int count = 1;

        for(int r1 = r-1,c1 = c+1; r1 >= 0 && c1 <= ROW; r1--,c1++)
        {
            if(chessArray[r][c] == chessArray[r1][c1])
                count++;
            else
                break;
        }

        for(int r1 = r+1,c1 = c-1; r1 < COLUMN && c1 >= 0; r1++,c1--)
        {
            if(chessArray[r][c] == chessArray[r1][c1])
                count++;
            else
                break;
        }
        return count;
    }
}
