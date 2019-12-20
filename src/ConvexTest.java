import javax.swing.*;
import java.awt.*;

class ConvexTest extends JFrame{

    static int a=0;
    private static int scetchic=0;

    public static void main(String[] args) throws Exception {


        Convex convex = new Convex();
        MyFrame w= new MyFrame(convex);
        Graphics g =w.getGraphics() ;
        w.paintOsi(g);
        while (true) {
            convex.add(new R2Point());

            w.paint(g);
            System.out.println("S = " + convex.area() + " , P = " + convex.perimeter());
            scetchic=convex.power()+scetchic;
            System.out.println("Мощьность множества точек пересечения оболочки и X^2+Y^2=50^2 " +  scetchic);

        }
    }
}