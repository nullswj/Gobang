package gobang;

public class WeightedMethod implements GobangConfig
{

    /**
     * 初始化棋局情况权值表
     */
    public static void initMap()
    {
        /** 己方 眠 1 连 */
        situationmap.put("H00",2);//0
        situationmap.put("00H",2);
        situationmap.put("00",2);
        situationmap.put("0",2);

        /** 己方 活 1 连 */
        situationmap.put("000",7);

        /** 己方 眠 2 连 */
        situationmap.put("HA00",8);
        situationmap.put("00AH",8);

        /** 己方 眠 3 连 */
        situationmap.put("HAA00",15);
        situationmap.put("00AAH",15);
        situationmap.put("HA0A0",15);
        situationmap.put("0A0AH",15);

        /** 己方 活 2 连 */
        situationmap.put("0A00",31);
        situationmap.put("00A0",31);



        /** 己方 活 3 连 */
        situationmap.put("0AA00",44);
        situationmap.put("00AA0",44);
        situationmap.put("0A0A0",44);

        /** 己方 眠 4 连 */
        situationmap.put("HAAA00",250);
        situationmap.put("00AAAH",250);
        situationmap.put("HA0AA0",250);
        situationmap.put("0AA0AH",250);
        situationmap.put("HAA0A0",250);
        situationmap.put("0A0AAH",250);

        /** 己方 活 4 连 */
        situationmap.put("00AAA0",1025);
        situationmap.put("0AAA00",1025);
        situationmap.put("0A0AA0",1025);
        situationmap.put("0AA0A0",1025);

        /** 己方 眠 5 连 */
        situationmap.put("HAAAA00",16405);
        situationmap.put("00AAAAH",16405);
        situationmap.put("HAAAA0H",16405);
        situationmap.put("H0AAAAH",16405);
        situationmap.put("HA0AAA0",16405);
        situationmap.put("0AAA0AH",16405);
        situationmap.put("HA0AAAH",16405);
        situationmap.put("HAAA0AH",16405);
        situationmap.put("HAA0AA0",16405);
        situationmap.put("0AA0AAH",16405);
        situationmap.put("HAA0AAH",16405);

        /** 己方 活 5 连 */
        situationmap.put("00AAAA0",16405);
        situationmap.put("0AAAA00",16405);
        situationmap.put("0A0AAA0",16405);
        situationmap.put("0AAA0A0",16405);
        situationmap.put("0AA0AA0",16405);


        /** 对方 眠 1 连 */
        situationmap.put("AH00",2);
        situationmap.put("00HA",2);
        situationmap.put("AH0A",2);
        situationmap.put("A0HA",2);


        /** 对方 活 1 连 */
        situationmap.put("00H0",3);
        situationmap.put("0H00",3);

        /** 对方 眠 2 连 */
        situationmap.put("AHH00",8);
        situationmap.put("00HHA",8);
        situationmap.put("AH0H0",8);
        situationmap.put("0H0HA",8);

        /** 对方 活 2 连 */
        situationmap.put("0HH00",13);
        situationmap.put("00HH0",13);
        situationmap.put("0H0H0",13);

        /** 对方 眠 3 连 */
        situationmap.put("AHHH00",15);
        situationmap.put("00HHHA",15);
        situationmap.put("AH0HH0",15);
        situationmap.put("0HH0HA",15);
        situationmap.put("AHH0H0",15);
        situationmap.put("0H0HHA",15);

        /** 对方 活 3 连 */
        situationmap.put("00HHH0",256);
        situationmap.put("0HHH00",256);
        situationmap.put("0H0HH0",256);
        situationmap.put("0HH0H0",256);

        /** 对方 眠 4 连 */
        situationmap.put("AHHHH00",4101);
        situationmap.put("00HHHHA",4101);
        situationmap.put("AHHHH0A",4101);
        situationmap.put("A0HHHHA",4101);

        situationmap.put("AH0HHH0",4101);
        situationmap.put("0HHH0HA",4101);
        situationmap.put("AH0HHHA",4101);
        situationmap.put("AHHH0HA",4101);

        situationmap.put("AHH0HH0",4101);
        situationmap.put("0HH0HHA",4101);
        situationmap.put("AHH0HHA",4101);

        /** 对方 活 4 连 */
//        situationmap.put("0HHHH00",2);
//        situationmap.put("00HHHH0",2);
//        situationmap.put("0H0HHH0",2);
//        situationmap.put("A0HHHHA",2);
    }
    public static void calculateValue()
    {
        sweepMatrix();
        for(int i = 0; i  < ROW; i++)
        {
            for(int j = 0; j < COLUMN; j++)
            {
                int score = 0;
                if(chessArray[i][j] == 0)
                {
                    /** 斜向下找自己 */
                    String hvs = hvSweepSelf(i,j);
                    /** 斜向下找对方 */
                    String hvo = hvSweepOpponent(i,j);
                    /** 斜向上找自己 */
                    String vhs = vhSweepSelf(i,j);
                    /** 斜向上找对方 */
                    String vho = vhSweepOpponent(i,j);
                    /** 竖直方向找对方 */
                    String vo = verticalSweepOpponent(i,j);
                    /** 竖直方向找自己 */
                    String vs = verticalSweepSelf(i,j);
                    /** 水平方向找对方 */
                    String ho = horizontalSweepOpponent(i,j);
                    /** 水平方向找自己 */
                    String hs = horizontalSweepSelf(i,j);

                    if(situationmap.get(hvs) != null)
                        score += situationmap.get(hvs);
                    if(situationmap.get(hvo) != null)
                        score += situationmap.get(hvo);
                    if(situationmap.get(vhs) != null)
                        score += situationmap.get(vhs);
                    if(situationmap.get(vho) != null)
                        score += situationmap.get(vho);
                    if(situationmap.get(vo) != null)
                        score += situationmap.get(vo);
                    if(situationmap.get(vs) != null)
                        score += situationmap.get(vs);
                    if(situationmap.get(ho) != null)
                        score += situationmap.get(ho);
                    if(situationmap.get(hs) != null)
                        score += situationmap.get(hs);
                }
                valuematrix[i][j] = score;
                //System.out.print(valuematrix[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println();
    }

    /**
     * 斜向下找自己
     * @param i
     * @param j
     * @return
     */
    public static String hvSweepSelf(int i, int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        int t = j-1;
        int b = j+1;

        while (l >= 0 && t >= 0)
        {
            if(AIchessArray[l][t] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[l][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][t] == 'A')
            {
                result += 'A';
                l--;
                t--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (b < COLUMN  && r < ROW)
        {
            if(AIchessArray[r][b] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[r][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][b] == 'A')
            {
                result += 'A';
                r++;
                b++;
            }
        }
        return result;
    }

    /**
     * 斜向下找对方
     * @param i
     * @param j
     * @return
     */
    public static String hvSweepOpponent(int i, int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        int t = j-1;
        int b = j+1;

        while (l >= 0 && t >= 0)
        {
            if(AIchessArray[l][t] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[l][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][t] == 'H')
            {
                result += 'H';
                l--;
                t--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (b < COLUMN  && r < ROW)
        {
            if(AIchessArray[r][b] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[r][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][b] == 'H')
            {
                result += 'H';
                r++;
                t++;
            }
        }
        return result;
    }

    /**
     * 斜向上找自己
     * @param i
     * @param j
     * @return
     */
    public static String vhSweepSelf(int i, int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        int t = j-1;
        int b = j+1;

        while (l >= 0 && b < COLUMN)
        {
            if(AIchessArray[l][b] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[l][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][b] == 'A')
            {
                result += 'A';
                l--;
                b++;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (t >= 0 && r < ROW)
        {
            if(AIchessArray[r][t] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[r][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][t] == 'A')
            {
                result += 'A';
                r++;
                t--;
            }
        }

        return result;
    }
    /**
     * 斜向上找对方
     * @param i
     * @param j
     * @return
     */
    public static String vhSweepOpponent(int i, int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        int t = j-1;
        int b = j+1;

        while (l >= 0 && b < COLUMN)
        {
            if(AIchessArray[l][b] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[l][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][b] == 'H')
            {
                result += 'H';
                l--;
                b++;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (t >= 0 && r < ROW)
        {
            if(AIchessArray[r][t] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[r][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][t] == 'H')
            {
                result += 'H';
                r++;
                t--;
            }
        }
        return result;
    }

    /**
     * 竖直方向找对方
     * @param i
     * @param j
     * @return
     */
    public static String verticalSweepOpponent(int i, int j)
    {
        String result = "";
        int t = j-1;
        int b = j+1;

        while (t > 0)
        {
            if(AIchessArray[i][t] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[i][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[i][t] == 'H')
            {
                result += 'H';
                t--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (b < COLUMN)
        {
            if(AIchessArray[i][b] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[i][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[i][b] == 'H')
            {
                result += 'H';
                b++;
            }
        }

        return result;
    }

    /**
     * 竖直方向找自己
     * @param i
     * @param j
     * @return
     */
    public static String verticalSweepSelf(int i, int j)
    {
        String result = "";
        int t = j-1;
        int b = j+1;
        while (t > 0)
        {
            if(AIchessArray[i][t] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[i][t] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[i][t] == 'A')
            {
                result += 'A';
                t--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (b < COLUMN)
        {
            if(AIchessArray[i][b] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[i][b] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[i][b] == 'A')
            {
                result += 'A';
                b++;
            }
        }
        return result;
    }

    /**
     * 水平方向找对方
     * @param i
     * @param j
     * @return
     */
    public static String horizontalSweepOpponent(int i, int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        while (l >= 0)
        {
            if(AIchessArray[l][j] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[l][j] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][j] == 'H')
            {
                result += 'H';
                l--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (r < COLUMN)
        {
            if(AIchessArray[r][j] == 'A')
            {
                result +='A';
                break;
            }
            if(AIchessArray[r][j] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][j] == 'H')
            {
                result += 'H';
                r++;
            }
        }

        return result;
    }

    /**
     * 水平方向找自己
     * @param i
     * @param j
     * @return
     */
    public static String horizontalSweepSelf(int i,int j)
    {
        String result = "";
        int l = i-1;
        int r = i+1;
        while (l >= 0)
        {
            if(AIchessArray[l][j] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[l][j] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[l][j] == 'A')
            {
                result += 'A';
                l--;
            }
        }

        result = new StringBuilder(result).reverse().toString();

        result += AIchessArray[i][j];

        while (r < COLUMN)
        {
            if(AIchessArray[r][j] == 'H')
            {
                result +='H';
                break;
            }
            if(AIchessArray[r][j] == '0')
            {
                result += '0';
                break;
            }
            if(AIchessArray[r][j] == 'A')
            {
                result += 'A';
                r++;
            }
        }
        return result;
    }

    public static void sweepMatrix()
    {
        if(gobang.GobangListener.offensive == true)
        {
            for(int i = 0; i < ROW; i++)
            {
                for(int j = 0; j < COLUMN; j++)
                {
                    if(chessArray[i][j] == 1)
                        AIchessArray[i][j] = 'A';
                    else if(chessArray[i][j] == 2)
                        AIchessArray[i][j] = 'H';
                    else
                        AIchessArray[i][j] = '0';
                }
            }
        }
        else
        {
            for(int i = 0; i < ROW; i++)
            {
                for(int j = 0; j < COLUMN; j++)
                {
                    if(chessArray[i][j] == 1)
                        AIchessArray[i][j] = 'H';
                    else if(chessArray[i][j] == 2)
                        AIchessArray[i][j] = 'A';
                }
            }
        }
    }
}
