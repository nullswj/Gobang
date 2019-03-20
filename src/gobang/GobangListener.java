package gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gobang.WeightedMethod.calculateValue;

public class GobangListener extends MouseAdapter implements GobangConfig, ActionListener
{
    /** 窗体控件 */
    private JFrame mainframe;

    /** 棋盘控件 */
    private ChessBoard chessboard;

    /** 棋盘面板画笔*/
    private Graphics g;

    /** 菜单栏的开始、悔棋、认输按钮*/
    private JButton start,retract_the_false,give_up;

    /** 游戏模式选项 */
    private JRadioButton pve, pvp;

    /** 先手选项 */
    private JCheckBox AI_offensive;

    /** 游戏模式标识 true 代表人人对战；false 代表人机对战*/
    private boolean mode = true;

    /** 先手标识 true 代表 AI先手；false 代表 人先手*/
    public static boolean offensive = false;

    /** 黑白棋标志位 true 表示黑棋； false 表示白棋 */
    private boolean flag_chess = true;

    /** 比赛结果 */
    private String result_message;


    /**
     * 构造函数，初始化窗口控件
     * @param chessboard
     * @param start
     * @param retract_the_false
     * @param give_up
     * @param pvp
     * @param pve
     * @param AI_offensive
     */
    public GobangListener(JFrame frame,ChessBoard chessboard,JButton start, JButton retract_the_false,JButton give_up,JRadioButton pvp, JRadioButton pve,JCheckBox AI_offensive)
    {
        this.mainframe = frame;
        this.chessboard = chessboard;
        this.start = start;
        this.retract_the_false = retract_the_false;
        this.give_up = give_up;
        this.pvp = pvp;
        this.pve = pve;
        this.AI_offensive = AI_offensive;
        g = chessboard.getGraphics();
    }

    /**
     * 处理鼠标在棋盘面板的操作
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();

        if(mode == true)
        {
            /** 点击棋盘内部 */
            if(x > X && x <= X + SIZE*(COLUMN-1) && y >= Y && y <= Y + SIZE*(ROW-1))
            {
                /** 计算最近的交叉点的行数和列数 */
                int r = (y - Y + SIZE/2)/SIZE;
                int c = (x - X + SIZE/2)/SIZE;

                /** 判断该位置是否已经有棋子 */
                if(chessArray[r][c] == 0)
                {
                    /** 计算交叉点坐标值 */
                    x = c*SIZE + X - CHESS_SIZE/2;
                    y = r*SIZE + Y - CHESS_SIZE/2;

                    /** 存储黑棋 */
                    if(flag_chess)
                    {
                        g.setColor(Color.BLACK);
                        chessArray[r][c] = 1;
                    }
                    /** 存储白棋 */
                    else
                    {
                        g.setColor(Color.WHITE);
                        chessArray[r][c] = 2;
                    }
                    /** 画棋子 */
                    g.fillOval(x,y,CHESS_SIZE,CHESS_SIZE);

                    /** 记录棋子顺序 */
                    orderRecoder.add(new Point(r,c));

                    /** 判断胜负 */
                    if(GobangWin.judge(r,c))
                    {
                        result_message = flag_chess ? BLACK_WIN : WHITE_WIN;
                        gameOver();
                        return;
                    }
                    /** 交换选手 */
                    flag_chess = !flag_chess;
                }
            }
        }
        else
        {
            //JOptionPane.showMessageDialog(mainframe,"人机对战尚未实现");
            if(offensive == true)
            {
                /** 点击棋盘内部 */
                if(x > X && x <= X + SIZE*(COLUMN-1) && y >= Y && y <= Y + SIZE*(ROW-1))
                {
                    /** 计算最近的交叉点的行数和列数 */
                    int r = (y - Y + SIZE/2)/SIZE;
                    int c = (x - X + SIZE/2)/SIZE;

                    /** 判断该位置是否已经有棋子 */
                    if(chessArray[r][c] == 0)
                    {
                        /** 计算交叉点坐标值 */
                        x = c*SIZE + X - CHESS_SIZE/2;
                        y = r*SIZE + Y - CHESS_SIZE/2;

                        /** 存储白棋 */
                        chessArray[r][c] = 2;
                        System.out.println("r:"+r+" c:"+c +" "+ chessArray[r][c]);
                        g.setColor(Color.WHITE);


                        /** 画棋子 */
                        g.fillOval(x,y,CHESS_SIZE,CHESS_SIZE);

                        /** 记录棋子顺序 */
                        orderRecoder.add(new Point(r,c));

                        /** 判断胜负 */
                        if(GobangWin.judge(r,c))
                        {
                            result_message = flag_chess ? BLACK_WIN : WHITE_WIN;
                            gameOver();
                            return;
                        }
                        calculateValue();
                        for(int i = 0; i < ROW; i++)
                        {
                            for(int j = 0; j < ROW; j++)
                            {
                                System.out.print(chessArray[i][j] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                        int maxI = 0, maxJ = 0;
                        for(int i = 0; i < ROW; i++)
                        {
                            for(int j = 0; j < COLUMN; j++)
                            {
                                if((valuematrix[i][j] > valuematrix[maxI][maxJ]) && (chessArray[i][j] == 0))
                                {
                                    maxI = i;
                                    maxJ = j;
                                }
                            }
                        }
                        System.out.println("r:"+ maxI + "c:" + maxJ);
                        /** 计算交叉点坐标值 */
                        x = maxJ*SIZE + X - CHESS_SIZE/2;
                        y = maxI*SIZE + Y - CHESS_SIZE/2;

                        /** 存储黑棋 */
                        g.setColor(Color.BLACK);
                        chessArray[maxI][maxJ] = 1;

                        /** 画棋子 */
                        g.fillOval(x,y,CHESS_SIZE,CHESS_SIZE);

                        /** 记录棋子顺序 */
                        orderRecoder.add(new Point(maxI,maxJ));

                        /** 判断胜负 */
                        if(GobangWin.judge(maxI,maxJ))
                        {
                            result_message = flag_chess ? BLACK_WIN : WHITE_WIN;
                            gameOver();
                            return;
                        }
                    }
                }
            }
        }
    }


    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        /** 获取按钮上的文字信息 */
        String actioncommand = e.getActionCommand();
        //System.out.println(actioncommand);

        /** 开始新游戏 */
        if(actioncommand.equals(START))
        {
            //System.out.println("开始新游戏");
            /** 设置菜单状态 */
            start.setEnabled(false);
            retract_the_false.setEnabled(true);
            give_up.setEnabled(true);

            pvp.setEnabled(false);
            pve.setEnabled(false);
            AI_offensive.setEnabled(false);

            /** 给棋盘面板添加鼠标监听方法，指定事件处理对象为当前类的对象。 */
            chessboard.addMouseListener(this);

            /** 清空存储棋子的数组 */
            for(int r = 0; r < ROW;r++)
            {
                for(int c = 0; c < COLUMN; c++)
                {
                    chessArray[r][c] = 0;
                }
            }

            /** 界面重绘 */
            chessboard.repaint();

            /** 黑白棋的标志位回到初始状态 */
            flag_chess = true;

            if(mode == false && offensive == true)
            {
                int x, y;
                /** 计算交叉点坐标值 */
                x = 7*SIZE + X - CHESS_SIZE/2;
                y = 7*SIZE + Y - CHESS_SIZE/2;

                g.setColor(Color.BLACK);
                chessArray[7][7] = 1;

                /** 画棋子 */
                g.fillOval(x,y,CHESS_SIZE,CHESS_SIZE);

                /** 记录棋子顺序 */
                orderRecoder.add(new Point(7,7));

                //flag_chess = !flag_chess;
            }
        }

        /** 悔棋 */
        else if(actioncommand.equals(RETRACT))
        {
            /** 前两步不允许悔棋 */
            if(orderRecoder.size() > 2)
            {
                /** 获取最近的棋子 */
                Point point = orderRecoder.remove(orderRecoder.size()-1);

                /** 清除存储棋子数组中对应的棋子 */
                chessArray[point.x][point.y] = 0;

                /** 改变黑白棋的标志 */
                flag_chess = !flag_chess;

                /** 界面重绘 */
                chessboard.repaint();
            }
        }

        /** 认输 */
        else if(actioncommand.equals(GIVEUP))
        {
            result_message = flag_chess ? GIVEUP_WHITE_WIN : GIVEUP_BLACK_WIN;
            gameOver();
        }
        /** 选中人人对战 */
        else if(actioncommand.equals(PVP))
        {
            AI_offensive.setEnabled(false);
            mode = true;
        }
        /** 选中人机对战 */
        else if(actioncommand.equals(PVE))
        {
            AI_offensive.setEnabled(true);
            mode = false;
        }
        /** 选中AI先手 */
        else if(actioncommand.equals(AI))
        {
            offensive = !offensive;
        }
    }

    /**
     * 游戏结束
     */
    private void gameOver()
    {
        JOptionPane.showMessageDialog(mainframe,result_message);

        /** 菜单面板处理 */
        chessboard.removeMouseListener(this);

        start.setEnabled(true);
        retract_the_false.setEnabled(false);
        give_up.setEnabled(false);

        pvp.setEnabled(true);
        pve.setEnabled(true);

        AI_offensive.setEnabled(offensive ? true : false);
    }
}
