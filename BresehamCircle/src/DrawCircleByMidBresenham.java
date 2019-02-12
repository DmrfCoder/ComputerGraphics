import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawCircleByMidBresenham extends JFrame {


    private JMenuBar mubMain;
    private JMenu muCircle;
    private Point spos, epos;
    private Graphics g;
    private JPanel drawP;
    private int addX = 300, addY = 150;//圆心坐标

    public DrawCircleByMidBresenham() {
        // TODO Auto-generated constructor stub
        setTitle("计算机图形学画圆中点Breseham算法实现");
        setSize(610, 400);
        spos = new Point();
        epos = new Point();

        mubMain = new JMenuBar();
        muCircle = new JMenu("圆绘制算法");
        mubMain.add(muCircle);

        JMenuItem it_midcir = new JMenuItem("中点Bresenham画圆算法");
        muCircle.add(it_midcir);
        it_midcir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String r = JOptionPane.showInputDialog(null, "请输入圆的半径：", "数值输入", JOptionPane.QUESTION_MESSAGE);
                if (!r.trim().equals(""))
                    MidPointCircle(Integer.parseInt(r));
            }
        });
        setJMenuBar(mubMain);

        drawP = new JPanel();
        drawP.setBackground(Color.white);
        add(drawP, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new DrawCircleByMidBresenham();
    }


    private int sign(int num) {
        if (num > 0)
            return 1;
        else if (num == 0)
            return 0;
        else
            return -1;
    }

    private void MidPointCircle(int r) {
        int x, y;
        double d;
        x = 0;
        y = r;
        d = 1.25 - r;
        Circlepoints(x, y);
        // 显示圆弧上的八个对称点
        while (x <= y) {
            if (d < 0) {
                d += 2 * x + 3;
            } else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
            Circlepoints(x, y);
        }
    }

    private void Circlepoints(int x, int y) {
        g = drawP.getGraphics();
        g.drawLine(x + addX, y + addY, x + addX, y + addY);
        g.drawLine(y + addX, x + addY, y + addX, x + addY);
        g.drawLine(-x + addX, y + addY, -x + addX, y + addY);
        g.drawLine(y + addX, -x + addY, y + addX, -x + addY);
        g.drawLine(x + addX, -y + addY, x + addX, -y + addY);
        g.drawLine(-y + addX, x + addY, -y + addX, x + addY);
        g.drawLine(-x + addX, -y + addY, -x + addX, -y + addY);
        g.drawLine(-y + addX, -x + addY, -y + addX, -x + addY);
    }

}