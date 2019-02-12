import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TailoringLineByCohenSutherland extends JFrame {

    JMenu mu_line, mu_circle;
    JLabel lb_x, lb_y, lb_y1, lb_y2, lb_x1, lb_x2, lb_w, lb_h;
    JTextField x, y, w, h, x1, x2, y1, y2;
    Graphics g;
    JPanel Draw_p;

    private static final int INSIDE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    private static int xMin;
    private static int yMin;
    private static int xMax;
    private static int yMax;


    public TailoringLineByCohenSutherland() {
        // TODO Auto-generated constructor stub
        setTitle("计算机图形学ChohenSutherland算法实现线段裁剪");
        setSize(1200, 600);

        Draw_p = new JPanel();
        Draw_p.setBackground(Color.white);
        add(Draw_p, BorderLayout.CENTER);

        JPanel Msg_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        lb_x = new JLabel("裁剪框起点坐标：");
        Msg_p.add(lb_x);


        x = new JTextField(3);
        x.setEditable(true);
        y = new JTextField(3);
        y.setEditable(true);
        Msg_p.add(x);
        Msg_p.add(y);


        lb_w = new JLabel("裁剪框宽：");
        Msg_p.add(lb_w);


        w = new JTextField(3);
        x.setEditable(true);
        Msg_p.add(w);

        lb_h = new JLabel("裁剪框高：");
        Msg_p.add(lb_h);

        h = new JTextField(3);
        h.setEditable(true);
        Msg_p.add(h);

        lb_x1 = new JLabel("线段起点坐标：");
        Msg_p.add(lb_x1);


        x1 = new JTextField(3);
        x1.setEditable(true);
        y1 = new JTextField(3);
        y1.setEditable(true);
        Msg_p.add(x1);
        Msg_p.add(y1);

        lb_x2 = new JLabel("线段终点坐标：");
        Msg_p.add(lb_x2);


        x2 = new JTextField(3);
        x2.setEditable(true);
        y2 = new JTextField(3);
        y2.setEditable(true);
        Msg_p.add(x2);
        Msg_p.add(y2);


        JButton draw = new JButton("确定");
        Msg_p.add(draw);

        add(Msg_p, BorderLayout.SOUTH);


        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int beg_x = Integer.parseInt(x.getText());
                int beg_y = Integer.parseInt(y.getText());
                int width = Integer.parseInt(w.getText());
                int hight = Integer.parseInt(h.getText());
                int xx1 = Integer.parseInt(x1.getText());
                int xx2 = Integer.parseInt(x2.getText());
                int yy1 = Integer.parseInt(y1.getText());
                int yy2 = Integer.parseInt(y2.getText());

                clip(xx1, yy1, xx2, yy2, beg_x, beg_y, width, hight);


            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new TailoringLineByCohenSutherland();
    }

    public boolean clip(int x1, int y1, int x2, int y2, int x, int y, int w, int h) {


        xMin = x;
        xMax = x + w;
        yMin = y;
        yMax = y + h;

        int p1x = x1, p1y = y1;
        int p2x = x2, p2y = y2;
        double qx = 0d, qy = 0d;
        boolean vertical = p1x == p2x;
        double slope = vertical ? 0d : (p2y - p1y) / (p2x - p1x);

        int code1 = getRegionCode(p1x, p1y);
        int code2 = getRegionCode(p2x, p2y);

        while (true) {
            if (code1 == INSIDE & code2 == INSIDE) {
                break;
            }

            if ((code1 & code2) != INSIDE) {
                return false;
            }

            int codeout = code1 == INSIDE ? code2 : code1;

            if ((codeout & LEFT) != INSIDE) {
                qx = xMin;
                qy = (qx - p1x) * slope + p1y;
            } else if ((codeout & RIGHT) != INSIDE) {
                qx = xMax;
                qy = (qx - p1x) * slope + p1y;
            } else if ((codeout & BOTTOM) != INSIDE) {
                qy = yMin;
                qx = vertical ? p1x : (qy - p1y) / slope + p1x;
            } else if ((codeout & TOP) != INSIDE) {
                qy = yMax;
                qx = vertical ? p1x : (qy - p1y) / slope + p1x;
            }

            if (codeout == code1) {
                p1x = (int) qx;
                p1y = (int) qy;
                code1 = getRegionCode(p1x, p1y);
            } else {
                p2x = (int) qx;
                p2y = (int) qy;
                code2 = getRegionCode(p2x, p2y);
            }
        }
        drawLine(xMin, yMin, xMax, yMin);
        drawLine(xMin, yMin, xMin, yMax);
        drawLine(xMax, yMin, xMax, yMax);
        drawLine(xMin, yMax, xMax, yMax);
        drawLine(p1x, p1y, p2x, p2y);

        return true;
    }

    private static final int getRegionCode(double x, double y) {
        int xcode = x < xMin ? LEFT : x > xMax ? RIGHT : INSIDE;
        int ycode = y < yMin ? BOTTOM : y > yMax ? TOP : INSIDE;
        return xcode | ycode;
    }


    void drawLine(int x0, int y0, int x1, int y1) {

        g = Draw_p.getGraphics();
        g.drawLine(x0, y0, x1, y1);

    }


}