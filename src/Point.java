import java.awt.*;

// Класс "одноугольник", реализующий интерфейс фигуры.
class Point implements Figure {
    private R2Point p;
    private int size=10;
    private int diametrK=100;

    public Point(R2Point p) {
    this.p = p;
    }


    //Видим реализацию идеи наперед! если введены две совпадающие точки ,то возвращаем точку . Если две разные точки то вызываем двуугольник СЕГМЕНТ
    public Figure add(R2Point q) {
    if (!R2Point.equal(p,q)) return new Segment(p, q);
        else return this;
    }

    public double perimeter() {
     return 0.0;
    }
    public double area() {
    return 0.0;
    }
    public void draw (Graphics g){

      g.setColor(Color.green);
        g.fillOval((int)p.x,-1*((int)p.y),size,size);

    }

    public int power()
    { //вычислить мощьность.
       if (Math.abs(Math.sqrt( (diametrK/2)*(diametrK/2) -p.x*p.x))==Math.abs(p.y))
            return 1;
        else
            return 0;

    }


}
