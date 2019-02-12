import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawLineByBresenham extends JFrame {

    private JMenuBar mubMain;
    private JMenu muLine, muCircle;
    private JMenuItem itBre;
    private JLabel lbBeg, lbEnd;
    private JTextField x1;
    private JTextField x2;
    private JTextField y1;
    private JTextField y2;
    private Graphics g;
    private JPanel drawP;


    public DrawLineByBresenham() {
        // TODO Auto-generated constructor stub
        setTitle("计算机图形学Bresenham算法实现生成直线");
        setSize(610, 400);

        mubMain = new JMenuBar();
        muLine = new JMenu("直线绘制算法");
        mubMain.add(muLine);


        itBre = new JMenuItem("Bresenham算法");
        itBre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        muLine.add(itBre);


        setJMenuBar(mubMain);

        drawP = new JPanel();
        drawP.setBackground(Color.white);
        add(drawP, BorderLayout.CENTER);

        JPanel Msg_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        lbBeg = new JLabel("起点坐标：");
        Msg_p.add(lbBeg);


        x1 = new JTextField(3);
        x1.setEditable(true);
        y1 = new JTextField(3);
        y1.setEditable(true);
        Msg_p.add(x1);
        Msg_p.add(y1);

        lbEnd = new JLabel("终点坐标：");
        Msg_p.add(lbEnd);

        x2 = new JTextField(3);
        x2.setEditable(true);

        y2 = new JTextField(3);
        y2.setEditable(true);
        Msg_p.add(x2);
        Msg_p.add(y2);

        JButton Draw = new JButton("确定");
        Msg_p.add(Draw);

        add(Msg_p, BorderLayout.SOUTH);


        Draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = x1.getText();
                String b = y1.getText();
                String c = x2.getText();
                String d = y2.getText();
                int q = Integer.parseInt(a);
                int w = Integer.parseInt(b);
                int r = Integer.parseInt(c);
                int t = Integer.parseInt(d);

                BresenhamLine(q, w, r, t);
            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new DrawLineByBresenham();
    }


    void BresenhamLine(int x0, int y0, int x1, int y1) {
        int i, x, y, dx, dy;
        double k, e;
        dx = x1 - x0;
        dy = y1 - y0;
        k = dy / dx;
        e = -0.5;
        x = x0;
        y = y0;
        g = drawP.getGraphics();
        for (i = 0; i <= dy; i++) {
            g.drawLine(x, y, x, y);
            y += 1;
            e = e + k;
            if (e >= 0) {
                x = x + 1;
                e = e - 1;
            }
        }
    }


}