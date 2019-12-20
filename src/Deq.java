import java.awt.*;

// Непрерывная реализация дека.
/*Для класса Polygon, однако, необходимо нечто большее — вершины многоугольника необходимо хранить в каком-то контейнере.
Для этих целей особенно хорошо в данном случае подходит дек*/
class Deq {
    private final static int DEFSIZE = 16;
    private R2Point[] array;
    private int size, head, tail;//голова и хвост
    private int forward(int index) {//функция вперед размер . пока index+1 меньше размера контейнера
  //      System.out.println("сработал forward=вперед в Deq "+"index = "+index+"  array.length = "+array.length );
        return ++index < array.length ? index : 0;
    }
    private int backward(int index) {//функция назад уменьшает индекс
 //       System.out.println("сработал backward=назад в Deq "+"index = "+index+"  array.length = "+array.length);
        return --index >= 0 ? index : array.length - 1;
    }
    public Deq(int size) {//функция принимающая размер создает массив сайз  голова -нулевой элемент хвост длина минус один ,
        array = new R2Point[size];
        this.size = head = 0;
        tail = array.length - 1;
   //     System.out.println("сработал конструктор с параметром size в Deq ");
    }
    public Deq() {

        this(DEFSIZE);
 //       System.out.println("сработал конструктор в Deq ");
    }
    public int length() {//ФУНКЦИЯ ДЛИНА МАССИВА КОГДА ВЫЗЫВАЕТСЯ ТО МАССИВ ИЗ ДЛИНЫ 16 ПО УМОЛЧАНИЮ НАЧИНАЕТ ЗАПОЛНЯТСЯ ОТ ТОЧЕК
  //      System.out.println("сработал length в Deq ");
        return size;
    }
    //смещаем элемент головы
    public void pushFront(R2Point p) {//ФУНКЦИЯ ТОЛКАЕМ ПЕРЕД В МАССИВ[голове присваиваем голову на шаг назад = координаты икс
  //      System.out.println("сработал pushFront в Deq ");//голову ставим в конец и двигаем
        array[head=backward(head)] = p;//backward-назад
        size += 1;
    }
    //смещаем элемент хвоста
    public void pushBack(R2Point p) {//ФУНКЦИЯ ТОЛКАЕМ НАЗАД
 //       System.out.println("сработал pushBack в Deq ");
        array[tail=forward(tail)] = p;//forward-вперед
        size += 1;
    }
    public R2Point popFront() {
  //      System.out.println("сработал popFront в Deq ");
        R2Point p = front();
        head = forward(head);// forward(int index) {//функция вперед размер ГОЛОВУ ТОЛКАЕМ ВПЕРЕД
        size -= 1;
        return p;
    }
    public R2Point popBack() {
 //       System.out.println("сработал popBack в Deq ");
        R2Point p = back();
        tail = backward(tail);//backward(int index) {//функция назад уменьшает индекс ХВОСТА | СМЕСТИЛИ AB РАССМАТРИВАЕМ BC
        size -= 1;
        return p;
    }
    public R2Point front() {//ФУНКЦИЯ ВОЗВРАЩАЕТ значение головы
//        System.out.println("сработал front в Deq ");
        return array[head];
    }
    public R2Point back() {//ФУНКЦИЯ ВОЗВРАЩАЕТ значения хвоста
   //     System.out.println("сработал back в Deq ");
        return array[tail];
    }
/*
    public void draw (Graphics g){
        System.out.println("сработал дро в deq ");

    for(int i=0; i<size;i++ )
        {
          //array[i].get().draw(g);//ЯВЛЕНИЕ ПОЛИМОРФИЗМА

        }

        //.get().draw(g);//ЯВЛЕНИЕ ПОЛИМОРФИЗМА
    }*/


}
