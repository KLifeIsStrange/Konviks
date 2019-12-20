import java.util.LinkedList;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;
/*
нужно хранить
только один (для «одноугольника») или два (для «двуугольника») экземпляра класса R2Point, представляющие соответственно единственную
или две концевых точки этих выпуклых оболочек.
*/

class R2Point {
    public static int mochnost;
    public double x, y;
    private ArrayList<Convex> f = new ArrayList<>();//для того чтобы провести линию или точку
    public static LinkedList<Integer> XYPoin = new LinkedList<>();

    public R2Point(double x, double y) {
       this.x = x; this.y = y;
    }
    public R2Point() throws Exception {
        f = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("x = ");
        x = in.nextInt();
         XYPoin.addLast((int)x);
        System.out.println("y = ");
        y = in.nextInt();
        XYPoin.addLast((int)y);
        //в список XYPoint  хранится список точек
        System.out.println(XYPoin);
      //  f.add(this.x);
    }
    //Расстояние d между двумя точками на плоскости
    public static double dist(R2Point a, R2Point b) {
      return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
    // площадь
    public static double area(R2Point a, R2Point b, R2Point c) {
       return 0.5*((a.x-c.x)*(b.y-c.y)-(a.y-c.y)*(b.x-c.x));
    }
    /*Два объекта типа R2Point соответствуют сов-
    падающим точкам плоскости тогда и только тогда, когда попарно равны их координаты, что позволяет реализовать метод equal.*/
    public static boolean equal(R2Point a, R2Point b) {
       return a.x==b.x && a.y==b.y;
    }
    //пренадлежит ли новая точка уже существующей прямой:
    public static boolean isTriangle(R2Point a, R2Point b, R2Point c) {
        return area(a, b, c) != 0.0;
    }//определяет точка не пренадлежит
    public boolean inside(R2Point a, R2Point b) {
        return (a.x <= x && x <= b.x || a.x >= x && x >= b.x) &&
                (a.y <= y && y <= b.y || a.y >= y && y >= b.y);

    }
    /*метод light, позволяющий выяснить,
    освещено ли ребро [a, b] выпуклой оболочки из точки t,*/
    public boolean light(R2Point a, R2Point b) {

        double s = area(a, b, this);
        return s < 0.0 || ( s == 0.0 && ! inside(a, b));

    }




}