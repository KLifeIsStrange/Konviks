import java.awt.*;

// Класс "нульугольник", реализующий интерфейс фигуры.
class Void implements Figure {
    public double perimeter()
    {
        System.out.println("сработал   perimeter()  в Void ");
        return 0.0;
    }
    public double area() {
        System.out.println("сработал   area()  в Void ");
        return 0.0;
    }

    public Figure add(R2Point p) {
        System.out.println("сработал   add(R2Point p)  в Void ");
        return new Point(p);
    }
    public void draw (Graphics g){
        System.out.println("сработал  draw  в Void ");

    }
    public int power()
    { //вычислить мощьность.
        return 0;

    }

}