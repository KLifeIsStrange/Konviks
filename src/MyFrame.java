import javax.swing.*;
        import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MyFrame extends JFrame{
    private Convex n;
    public int OknoHeight=700;
    public int OknoWidth=700;
    public static int proverca=0;
    private int diametrK=100;
    //}
    public MyFrame(Convex n) {

               // super("Окошко");
                this.setTitle("Окошко");
                this.setSize(700, 700);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setVisible(true);
                this.setLocationRelativeTo(null);
            this.n = n;


    }


    public void paintOsi(Graphics g) {
      g.translate((OknoWidth)/2,OknoHeight/2);   //функция преобразования кооринат
        g.drawLine((-OknoWidth)/2,0,(OknoWidth)/2,0);
        g.drawLine(0,(-OknoHeight)/2,0,(OknoHeight/2));
        g.setColor( Color.orange);
        g.drawOval(0-(diametrK/2), 0-(diametrK/2), diametrK, diametrK);
    }

    @Override
    public void paint(Graphics g) {

        if ((OknoHeight!=this.getSize().height) || (OknoWidth!=this.getSize().width) )
        {

            OknoHeight=this.getSize().height;
            OknoWidth=this.getSize().width;
            g.translate((OknoWidth)/2,OknoHeight/2);   //функция преобразования кооринат

//очищаем экран рисуем оси и вызываем конвикс
            g.clearRect(-OknoWidth /2, -OknoHeight/2,OknoWidth , OknoHeight);
            g.setColor( Color.orange);
            g.drawOval(0-(diametrK/2), 0-(diametrK/2), diametrK, diametrK);

            g.setColor( Color.black);
            g.drawLine((-OknoWidth)/2,0,(OknoWidth)/2,0);
            g.drawLine(0,(-OknoHeight)/2,0,(OknoHeight/2));

            n.draw(g);//если не написать эту функцию ,то не будет вызываться флок и отрисовываться птицы

        }
        else{

            g.setColor( Color.black);
            n.draw(g);//если не написать эту функцию ,то не будет вызываться ф
        }


    }






}