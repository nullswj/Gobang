package gobang;

import javax.swing.*;
import java.awt.*;

import static gobang.WeightedMethod.initMap;

public class Gobang extends JFrame implements GobangConfig
{
    /** 左侧棋盘面板 */
    public static ChessBoard chessboard;

    public static void main(String[] args)
    {
        Gobang gobang = new Gobang();
        gobang.initUI();
        initMap();
    }

    private void initUI()
    {
        /** 窗体初始化 */
        this.setTitle("Gobang");
        this.setSize(760, 630);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //设置窗体的关闭动作
        this.setLocationRelativeTo(null);       //让窗体位于屏幕中央
        this.setResizable(false);               //让窗体不可改变大小
        this.setLayout(new BorderLayout());     //设置窗体的边界布局

        /** 左侧棋盘初始化 */
        chessboard = new ChessBoard();
        chessboard.setBackground(Color.GRAY);
        this.add(chessboard, BorderLayout.CENTER);      //将棋盘面板添加在边界布局的中央

        /** 右侧菜单初始化 */
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));    //面板设置流式布局
        menu.setPreferredSize(new Dimension(150, 0));   //面板设置大小

        /** 菜单上的控件声明 */
        JButton start = new JButton(START);
        JButton retract_the_false = new JButton(RETRACT);
        JButton give_up = new JButton(GIVEUP);

        /** 菜单上的按钮初始化 */
        start.setPreferredSize(new Dimension(140, 60));
        retract_the_false.setPreferredSize(new Dimension(140, 60));
        give_up.setPreferredSize(new Dimension(140, 60));
        menu.add(start);
        menu.add(retract_the_false);
        menu.add(give_up);

        /** 菜单上的模式选项初始化 */
        JRadioButton pvp = new JRadioButton(PVP);
        JRadioButton pve = new JRadioButton(PVE);
        pvp.setPreferredSize(new Dimension(140, 60));
        pve.setPreferredSize(new Dimension(140, 60));
        pvp.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(pvp);
        bg.add(pve);

        menu.add(pvp);
        menu.add(pve);

        JCheckBox AI_offensive = new JCheckBox(AI);
        menu.add(AI_offensive);

        this.add(menu, BorderLayout.EAST);      //将菜单面板添加在边界布局的右边

        this.setVisible(true);

        GobangListener gl = new GobangListener(this,chessboard,start,retract_the_false,give_up,pvp,pve,AI_offensive);    //事件处理

        start.addActionListener(gl);
        retract_the_false.addActionListener(gl);
        give_up.addActionListener(gl);
        pve.addActionListener(gl);
        pvp.addActionListener(gl);
        AI_offensive.addActionListener(gl);
    }
}
