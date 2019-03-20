package gobang;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel implements GobangConfig
{
    /**
     * 重写绘制棋盘的方法
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < ROW; i++)
            g.drawLine(X,Y+SIZE*i,X+SIZE*(COLUMN-1),Y+SIZE*i);
        for(int i = 0; i < COLUMN; i++)
            g.drawLine(X+SIZE*i,Y,X+SIZE*i,Y+SIZE*(ROW-1));
        for(int r = 0; r < ROW; r++)
        {
            for(int c = 0; c < COLUMN; c++)
            {
                if(chessArray[r][c] != 0)
                {
                    int x = c*SIZE + X - CHESS_SIZE/2;
                    int y = r*SIZE + Y - CHESS_SIZE/2;

                    if(chessArray[r][c] == 1)
                        g.setColor(Color.BLACK);
                    else
                        g.setColor(Color.WHITE);

                    g.fillOval(x,y,CHESS_SIZE,CHESS_SIZE);
                }
            }
        }
    }
}
