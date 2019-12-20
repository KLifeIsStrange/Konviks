import java.awt.*;

// Интерфейс, задающий новый тип - фигуру.
interface Figure {

    public double perimeter();
    public double area();
    public void draw(Graphics g);
    public Figure add(R2Point p);
    public int power();

}