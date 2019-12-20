import java.awt.*;

// Класс "выпуклая оболочка".
class Convex {
    public static int flag=0;
    private Figure fig;

    public Convex()
    {
        fig = new Void();//в первый раз нульугольник ,благодаря этому окно открывается сразу .Часть индуктивного алгоритма.
    }
    public void add(R2Point p)
    { //добавить новую точку и построить выпуклую оболочку;

        fig = fig.add(p);
    }
    public double area()
    { //вычислить площадь.

        return fig.area();

    }
    public double perimeter()
    { //вычислить периметр;

        return fig.perimeter();

    }

    public void draw (Graphics g){
        //передаем в интерфейс метод дро

            fig.draw(g);//ЯВЛЕНИЕ ПОЛИМОРФИЗМА обратилось в интерфейсу , вызвался дро при дефолтном открытии в VOID



    }
    public int power()
    { //вычислить мощьность.

        return fig.power();

    }
}