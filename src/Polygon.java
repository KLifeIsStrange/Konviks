import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

// Класс "многоугольник", реализующий интерфейс фигуры.
class Polygon extends Deq implements Figure {
    private double s, p;
    private int sizeT = 10;
    private int diametrK = 100;
    private int size = 10;
    private static int mochnost=0;
    private static int nXc, nYc, nXt, nYt;
    private static int j = 0;
    private static int jj = 0;
    private static int schetcic = 0;
    private static int OsvTochka1X, OsvTochka2X, OsvTochka1Y, OsvTochka2Y;
    private static int OsvTochka3X, OsvTochka4X, OsvTochka3Y, OsvTochka4Y;
    private static int OsvTochka5X, OsvTochka6X, OsvTochka5Y, OsvTochka6Y;
    private static double k = 0;
    private static double b = 0;
    private static double y1 = 0;
    private static double y2 = 0;
    private static double D = -1;
    private static double x1 = 0;
    private static double x2 = 0;
    private static LinkedList<Integer> PointPol = new LinkedList<>();

    //  private R2Point golova;
    //   private double hvost;
    /*
     Пересчет периметра и площади, который необходимо производить для
каждого из удаляемых ребер, выделен из соображений оптимизации в
отдельный private-метод grow.*/
    private void grow(R2Point a, R2Point b, R2Point t) {

        p -= R2Point.dist(a, b);
        s += Math.abs(R2Point.area(a, b, t));
      if (jj == -1) {
            PointPol.clear();
            jj = 0;
        }

        if (a.x < b.x) {

            PointPol.addLast((int) a.x + (sizeT / 2));
            PointPol.addLast((int) a.y - (sizeT / 2));
            PointPol.addLast((int) b.x + (sizeT / 2));
            PointPol.addLast((int) b.y - (sizeT / 2));

        } else {

            PointPol.addLast((int) b.x + (sizeT / 2));
            PointPol.addLast((int) b.y - (sizeT / 2));
            PointPol.addLast((int) a.x + (sizeT / 2));
            PointPol.addLast((int) a.y - (sizeT / 2));

        }

        j++;



    }

    public Polygon(R2Point a, R2Point b, R2Point c) {
       pushFront(b);
        if (b.light(a, c)) {
            pushFront(a);
            pushBack(c);
        } else {
            pushFront(c);
            pushBack(a);
        }
        p = R2Point.dist(a, b) + R2Point.dist(b, c)//периметр
                + R2Point.dist(c, a);
        s = Math.abs(R2Point.area(a, b, c));//вычисляем площадь по абсолютному значению через abs

        nXc = (int) b.x;
        nYc = (int) b.y;
    }

    public double perimeter() {
       return p;
    }

    public double area() {
       return s;
    }


    //При добавлении новой точки возможны два случая.
    public Figure add(R2Point t) {//T-это контейнер
        nXt = (int) t.x;
        nYt = (int) t.y;
// Ищем освещенные ребра, просматривая их одно за другим.
        int i;//Если в многоугольнике освещенных ребер нет, то это означает, что новая точка попала внутрь или на границу старой выпуклой оболочки ,делать ничего не надо.либо перестраиваем
        for (i = length(); i > 0 && !t.light(back(), front()); i--)//back -значение хвоста  front-значение головы
            pushBack(popFront());//pushBack-СМЕЩАЕМ ЭЛЕМЕНТ ХВОСТА forward(int index) {//функция вперед размер ГОЛОВУ ТОЛКАЕМ ВПЕРЕД
// УТВЕРЖДЕНИЕ: либо ребро [back=задний(),front=передний()] освещено из t,
// либо освещенных ребер нет совсем.
        if (i > 0) {
            R2Point x;
            grow(back(), front(), t);//удалим линию  [back=задний(),front=передний()] из периметра и прибавим площадь нового треугольника
// Удаляем все освещенные ребра из начала дека.
            for (x = popFront(); t.light(x, front()); x = popFront())
                grow(x, front(), t);//удалим линию  [x, front()] из периметра и прибавим площадь нового треугольника
            pushFront(x);
// Удаляем все освещенные ребра из конца дека.
            for (x = popBack(); t.light(back(), x); x = popBack())
                grow(back(), x, t);//удалим линию  [back(), x] из периметра и прибавим площадь нового треугольника
            pushBack(x);
// Завершаем обработку добавляемой точки. Добавляя к периметру два НОВЫХ ребра   // dist  Расстояние d между двумя точками на плоскости
            p += R2Point.dist(back(), t) + R2Point.dist(t, front());
            pushFront(t); //смещаем элемент головы и ставим t


        }
        return this;//если точка внутри или на границах оболочки то THIS
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.black);
        g.fillOval((int) front().x, -1 * (int) front().y, sizeT, sizeT);
        g.fillOval((int) back().x, -1 * (int) back().y, sizeT, sizeT);


        Color myWhite = new Color(240, 240, 240);

        g.setColor(Color.black);
        if (schetcic == 0) {
            g.drawLine((int) nXc + (sizeT / 2), -1 * ((int) nYc - (sizeT / 2)), (int) front().x + (sizeT / 2), -1 * ((int) front().y - (sizeT / 2)));
            g.drawLine((int) back().x + (sizeT / 2), -1 * (((int) back().y - (sizeT / 2))), (int) front().x + (sizeT / 2), -1 * ((int) front().y - (sizeT / 2)));
            g.drawLine((int) back().x + (sizeT / 2), -1 * ((int) back().y - (sizeT / 2)), (int) nXc + (sizeT / 2), -1 * ((int) nYc - (sizeT / 2)));
            mochnost=mochnost+ powerVichisleniya(  back().x ,  back().y,  front().x,  front().y);
            mochnost=mochnost+ powerVichisleniya(back().x,  back().y, nXc,  nYc);

            nXc = 0;
            nYc = 0;
            schetcic++;
        } else {
            g.fillOval((int) nXt, -1 * (int) nYt, sizeT, sizeT);
            if (j != 0) {

                for (int w = 0; w < (PointPol.size() - 3); w = w + 4) {
                   k = (PointPol.get(w + 1) - PointPol.get(w + 3));
                    k = k / (PointPol.get(w) - PointPol.get(w + 2));
                    b = PointPol.get(w + 3) - (k * PointPol.get(w + 2));

                    // ВЫЧИСЛЯЕМ ЕСТЬ ЛИ СРЕДИ ОСВЕЩЕННЫЙ РЕБЕР ТЕ КОТОРЫЕ ИМЕЛИ ПЕРЕСЕЧЕНИЕ С ОКРУЖНОСТЬЮ


                    D = (2 * k * b) * (2 * k * b) - 4 * (k * k + 1) * (b * b - 50 * 50);
                    x1 = (-1 * (2 * k * b) + Math.sqrt(D)) / (2 * (1 + k * k));
                    x2 = (-1 * (2 * k * b) - Math.sqrt(D)) / (2 * (1 + k * k));

                    if (PointPol.get(w) < PointPol.get(w + 2)) {

                        if (D > 0) {
                            if (x1 >= PointPol.get(w) && x1 <= PointPol.get(w + 2) && x2 >= PointPol.get(w) && x2 <= PointPol.get(w + 2)) {
                                mochnost = mochnost - 2;
                            } else {
                                if ((x1 >= PointPol.get(w) && x1 <= PointPol.get(w + 2)) || (x2 >= PointPol.get(w) && x2 <= PointPol.get(w + 2))) {

                                    mochnost--;
                                }
                            }
                        } else {
                            if (D == 0) {
                                if ((x1 >= PointPol.get(w) && x1 <= PointPol.get(w + 2)) || (x2 >= PointPol.get(w) && x2 <= PointPol.get(w + 2))) {

                                    mochnost--;
                                }
                            }
                        }

                    } else {

                        if (D > 0) {
                            if (x1 >= PointPol.get(w + 2) && x1 <= PointPol.get(w) && x2 >= PointPol.get(w + 2) && x2 <= PointPol.get(w)) {
                                mochnost = mochnost - 2;
                            } else {
                                if ((x1 >= PointPol.get(w + 2) && x1 <= PointPol.get(w)) || (x2 >= PointPol.get(w + 2) && x2 <= PointPol.get(w))) {

                                    mochnost--;

                                }
                            }
                        } else {
                            if (D == 0) {
                                if ((x1 >= PointPol.get(w + 2) && x1 <= PointPol.get(w)) || (x2 >= PointPol.get(w + 2) && x2 <= PointPol.get(w))) {

                                    mochnost--;

                                }
                            }
                        }

                    }


                    for (int l = PointPol.get(w); l <= PointPol.get(w + 2); l = l + 1) {
                        y1 = (int) (k * l + b);// Генерация 2-го числа
                        g.clearRect(l, -1 * (int) (y1 + ((y1 / Math.abs(y1)) * 2)), 5, 5);//узнаем знак у1 и увеличиваем координату на это число +((y1/Math.abs(y1))*3)
                        g.setColor(myWhite);
                        g.drawLine(PointPol.get(w), -1 * PointPol.get(w + 1), PointPol.get(w + 2), -1 * PointPol.get(w + 3));
                    }

                }
                g.setColor(Color.black);
                g.fillOval((int) front().x, -1 * (int) front().y, sizeT, sizeT);
                g.fillOval((int) back().x, -1 * (int) back().y, sizeT, sizeT);



                int maxyp = 1, minyp = 1;
                for (int q = 3; q < PointPol.size(); q = q + 2) {
                    if (PointPol.get(q) > PointPol.get(maxyp)) {
                        maxyp = q;
                    }
                    if (PointPol.get(q) < PointPol.get(minyp)) {
                        minyp = q;
                    }
                }
                System.out.println("maxyp  =  " + maxyp);
                System.out.println("minyp  =  " + minyp);
                System.out.println(" front().x   =  " + front().x);
                int maxxp = 0, minxp = 0;
                for (int q = 2; q < (PointPol.size() - 1); q = q + 2) {
                    if (PointPol.get(q) > PointPol.get(maxxp)) {
                        maxxp = q;
                    }
                    if (PointPol.get(q) < PointPol.get(minxp)) {
                        minxp = q;
                    }
                }

                if (j == 1) {
                    g.setColor(Color.black);
                    g.drawLine(PointPol.get(0), -1 * (PointPol.get(1)), (int) front().x + (sizeT / 2), -1 * (((int) front().y - (sizeT / 2))));
                    g.drawLine(PointPol.get(2), -1 * (PointPol.get(3)), (int) front().x + (sizeT / 2), -1 * (((int) front().y - (sizeT / 2))));
                    mochnost=mochnost+ powerVichisleniya( PointPol.get(0),  PointPol.get(1),  front().x,  front().y);
                    mochnost=mochnost+ powerVichisleniya( PointPol.get(2),  PointPol.get(3),  front().x,  front().y);

                } else {

                    int maxn = 100;
                    maxxp = 2;
                    for (int rab = 0; rab < 2; rab++) {


                        for (int q = 0; q < (PointPol.size() - 1); q = q + 2) {

                            double provline1 = Math.sqrt(Math.abs((front().x - PointPol.get(maxxp)) * (front().x - PointPol.get(maxxp)) + ((front().y - PointPol.get(maxxp + 1)) * (front().y - PointPol.get(maxxp + 1)))));
                            double provline2 = Math.sqrt(Math.abs((front().x - PointPol.get(q)) * (front().x - PointPol.get(q)) + ((front().y - PointPol.get(q + 1)) * (front().y - PointPol.get(q + 1)))));

                            if ((provline2 > provline1) && (maxn != q)) {
                                maxxp = q;
                                //а координата по у -1 * (PointPol.get(maxxp + 1))
                            } else {
                                minxp = q;
                                //    System.out.println("minxp  =  " + minxp);
                            }
                        }
                        maxn = maxxp;
                       g.setColor(Color.black);
                        g.drawLine(PointPol.get(maxxp), -1 * (PointPol.get(maxxp + 1)), (int) front().x + (sizeT / 2), -1 * (((int) front().y - (sizeT / 2))));
                        maxxp = minxp;
                        mochnost=mochnost+ powerVichisleniya( PointPol.get(maxxp),  PointPol.get(maxxp + 1),  front().x,  front().y);

                        if (((PointPol.get(minyp) + (sizeT / 2)) == ((int) front().y)) && (maxn != (minyp - 1))) {
                            int vr1 = (PointPol.get(minyp));
                            if (PointPol.get(maxn + 1) != vr1) {
                                g.setColor(Color.black);
                                g.drawLine(PointPol.get(minyp - 1), -1 * (PointPol.get(minyp)), (int) front().x + (sizeT / 2), -1 * (((int) front().y - (sizeT / 2))));
                                mochnost=mochnost+ powerVichisleniya( PointPol.get(minyp - 1), PointPol.get(minyp),  front().x,  front().y);
                                j = 0;
                                jj = -1;

                                break;
                            }

                        }
                        if ((PointPol.get(maxyp) + (sizeT / 2)) == ((int) front().y)) {
                            int vr = (PointPol.get(maxyp));
                            if ((PointPol.get(maxn + 1)) != vr) {
                                g.setColor(Color.black);
                                g.drawLine(PointPol.get(maxyp - 1), -1 * (PointPol.get(maxyp)), (int) front().x + (sizeT / 2), -1 * (((int) front().y - (sizeT / 2))));
                                j = 0;
                                jj = -1;
                                mochnost=mochnost+ powerVichisleniya( PointPol.get(maxyp - 1), (PointPol.get(maxyp)),  front().x,  front().y);
                               break;
                            }
                        }

                    }


                    //  }


                }

            }
            j = 0;
            jj = -1;
        }

    }


//}


    /*
    если ребро освещено и это ребро имело пересечение с окружностью , то мы должны уменьшить мощьность на единицу
    если новосозданные линии *точка 1 и ГОЛОВА* И *точка 2 и ГОЛОВА*
    */
    public int powerVichisleniya(double Tx1, double Ty1, double Tx2, double Ty2) { //вычислить мощьность.
        System.out.println("сработал мощьность в Polygon ");
        if (Tx1 != Tx2) {
            k = Ty1 - Ty2;
            k = k / (Tx1 - Tx2);
            b = Ty2 - (k * Tx2);
            //    y1 = (int) (k * x + b);// Генерация 2-го числа

            //  x^2+y2*2=50^2;
            //    x^2+(k * x + b)^2-50^2=0
            //     (1+k^2)*x^2 + 2*k*b*x +( b^2-50^2) = 0
            D = (2 * k * b) * (2 * k * b) - 4 * (k * k + 1) * (b * b - 50 * 50);
            x1 = (-1 * (2 * k * b) + Math.sqrt(D)) / (2 * (1 + k * k));
            x2 = (-1 * (2 * k * b) - Math.sqrt(D)) / (2 * (1 + k * k));

            if (Tx1 < Tx2) {

                if (D > 0) {
                    if (x1 >= Tx1 && x1 <= Tx2 && x2 >= Tx1 && x2 <= Tx2) {
                        return 2;
                    } else {
                        if ((x1 >= Tx1 && x1 <= Tx2) || (x2 >= Tx1 && x2 <= Tx2)) {

                            return 1;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (D == 0) {
                        if ((x1 >= Tx1 && x1 <= Tx2) || (x2 >= Tx1 && x2 <= Tx2)) {

                            return 1;
                        }
                    } else {
                        return 0;
                    }
                }

            } else {

                if (D > 0) {
                    if (x1 >= Tx2 && x1 <= Tx1 && x2 >= Tx2 && x2 <= Tx1) {
                        return 2;
                    } else {
                        if ((x1 >= Tx2 && x1 <= Tx1) || (x2 >= Tx2 && x2 <= Tx1)) {

                            return 1;

                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (D == 0) {
                        if ((x1 >= Tx2 && x1 <= Tx1) || (x2 >= Tx2 && x2 <= Tx1)) {

                            return 1;

                        }
                    } else {
                        return 0;
                    }
                }

            }
        }
        else
        {
            // x*x+y*y=50*50
            //y=sqrt(50*50-x*x)
            double y1=Math.sqrt((50*50-Tx1*Tx1));

            if (Ty1 < Ty2) {
                if (Ty1 <= -y1 && Ty2 >= y1) {
                    return 2;
                } else {
                    if ((Ty1 <= -y1 && Ty2 <= y1 && Ty2>=-y1)||(Ty1 >= -y1 && Ty2 >= y1 && Ty1<=y1)){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            } else {
                if (Ty1 >= y1 && Ty2 <= -y1) {
                    return 2;
                }else {
                    if (( Ty2<= -y1 &&  Ty1<= y1 && Ty1>=-y1)||( Ty2 >= -y1 && Ty1>= y1 &&  Ty2<=y1)){
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


    public int power() {
            int vremper=mochnost;
            mochnost=0;
            System.out.println("mochnost"+vremper);
            return vremper;

    }

}