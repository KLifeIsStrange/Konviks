import java.awt.*;

// Класс "двуугольник", реализующий интерфейс фигуры.
class Segment implements Figure {
    private R2Point p, q;
    private double rast;
    private int size=10;
    private int diametrK=100;
    private static double k = 0;
    private static double x = 0;

    private static double b = 0;
    private static double y1 = 0;
    private static double y2 = 0;
    private static double D = -1;
    private static double x1 = 0;
    private static double x2 = 0;
    public Segment(R2Point p, R2Point q) {
        this.p = p; this.q = q;
    }
    public double perimeter() {
        return 2.0 * R2Point.dist(p, q);
    }
    public double area() {
        return 0.0;
    }
    //Видим реализацию идеи наперед! если введеная точка не принадлежит уже сущ прямой,
    // то предаем три точки в многоугольник и он уже нам возвращает оболочку
    public Figure add(R2Point r) {
        if (R2Point.isTriangle(p, q, r))// Если НЕ пренадлежит  новая точка уже существующей прямой:
            return new Polygon(p, q, r);
        if (q.inside(p, r)) q = r;//Если точка равна одной из двух уже существующих точек то выводим линию
        if (p.inside(r, q)) p = r;
        return this;
    }

    public void draw (Graphics g){
       g.setColor( Color.blue);

        g.fillOval((int)p.x,-1*((int)p.y),size,size);
        g.fillOval((int)q.x,-1*((int)q.y),size,size);
        g.drawLine((int)p.x+(size/2),-1*((int)p.y-(size/2)),(int)q.x+(size/2),-1*((int)q.y-(size/2)));


    }

    public int power() { //вычислить мощьность.  x^2 + y^2 = (diametrK/2)^2
       if (p.x != q.x) {
            k = p.y - q.y;

            k = k / (p.x - q.x);
            b = q.y - (k * q.x);
            //    y1 = (int) (k * x + b);// Генерация 2-го числа

            //  x^2+y2*2=50^2;
            //    x^2+(k * x + b)^2-50^2=0
            //     (1+k^2)*x^2 + 2*k*b*x +( b^2-50^2) = 0
            D = (2 * k * b) * (2 * k * b) - 4 * (k * k + 1) * (b * b - 50 * 50);
            x1 = (-1 * (2 * k * b) + Math.sqrt(D)) / (2 * (1 + k * k));
            x2 = (-1 * (2 * k * b) - Math.sqrt(D)) / (2 * (1 + k * k));
            System.out.println("сработал мощьность в Segment D= " + D + "  k" + k + "  b=" + b);
            if (p.x < q.x) {

                if (D > 0) {
                    if (x1 >= p.x && x1 <= q.x && x2 >= p.x && x2 <= q.x) {
                        return 2;
                    } else {
                        if ((x1 >= p.x && x1 <= q.x) || (x2 >= p.x && x2 <= q.x)) {

                            return 1;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (D == 0) {
                        if ((x1 >= p.x && x1 <= q.x) || (x2 >= p.x && x2 <= q.x)) {

                            return 1;
                        }
                    } else {
                        return 0;
                    }
                }

            } else {

                if (D > 0) {
                    if (x1 >= q.x && x1 <= p.x && x2 >= q.x && x2 <= p.x) {
                        return 2;
                    } else {
                        if ((x1 >= q.x && x1 <= p.x) || (x2 >= q.x && x2 <= p.x)) {

                            return 1;

                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (D == 0) {
                        if ((x1 >= q.x && x1 <= p.x) || (x2 >= q.x && x2 <= p.x)) {

                            return 1;

                        }
                    } else {
                        return 0;
                    }
                }

            }

        }
        else {
            // x*x+y*y=50*50
            //y=sqrt(50*50-x*x)
            double y1=Math.sqrt((50*50-p.x*p.x));

            if (p.y < q.y) {
                if (p.y <= -y1 && q.y >= y1) {
                    return 2;
                } else {
                    if ((p.y <= -y1 && q.y <= y1 && q.y>=-y1)||(p.y >= -y1 && q.y >= y1 && p.y<=y1)){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            } else {
                if (p.y >= y1 && q.y <= -y1) {
                    return 2;
                }else {
                    if (( q.y<= -y1 &&  p.y<= y1 && p.y>=-y1)||( q.y >= -y1 && p.y>= y1 &&  q.y<=y1)){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
            return 0;

    }

}