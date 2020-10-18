package step1;

import java.util.Scanner;

public class ShapeTrouble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius = scanner.nextDouble();
        double length = scanner.nextDouble();
        double width = scanner.nextDouble();
        Circle circle = new Circle(radius);
        Rectangle rectangle = new Rectangle(length, width);
        System.out.println(circle.getAcreage());
        System.out.print(rectangle.getAcreage());
    }
}

/********** Begin *********/
abstract class Shape {
    public  abstract double getAcreage();

}

class Circle extends Shape {
    private double radius;

    public  Circle(double radius){}
    public void Cricle(double r)
    {
        radius=r;
    }
    public double getAcreage()
    {
        return radius*radius*3.1415926;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;
    public  Rectangle(){}
    public  Rectangle(double l,double w)
    {
        length=l;
        width=w;
    }
    public double getAcreage()
    {
        return length*width;
    }
}
/********** End *********/
