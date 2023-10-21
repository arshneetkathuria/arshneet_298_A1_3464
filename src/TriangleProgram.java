import javax.sound.midi.SysexMessage;
import java.util.Scanner;

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Triangle {
    Point A, B, C;
    private Type type=Type.UNKNOWN;
    public Triangle(Point A, Point B, Point C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public Type getType()
    {
        return type;
    }

    private void setType(Type type)
    {
        System.out.println("Set type"+type);
        this.type=type;
    }

    public void checkTriangleType()
    {
        if(isEquilateral())
        {
            setType(Type.EQUILATERAL);

        }
        else if(isIsosceles())
        {
            setType(Type.ISOSCELES);

        }
        else
        {
            setType(Type.UNKNOWN);
        }

    }

    public boolean isEquilateral() {
        double sideAB = calculateDistance(A, B);
        double sideBC = calculateDistance(B, C);
        double sideCA = calculateDistance(C, A);
        return (sideAB == sideBC) && (sideBC == sideCA) && (sideCA==sideAB);
    }

    public boolean isIsosceles() {
        double sideAB = calculateDistance(A, B);
        double sideBC = calculateDistance(B, C);
        double sideCA = calculateDistance(C, A);
        return (sideAB == sideBC) || (sideBC == sideCA) || (sideCA == sideAB);
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public double calculatePerimeter()
    {
        double sideAB=calculateDistance(A,B);
        double sideBC=calculateDistance(B,C);
        double sideCA=calculateDistance(A,C);
        return sideAB+sideBC+sideCA;
    }

    public double calculateArea() {
        return 0.5 * Math.abs(
                (A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y))
        );
    }

    public boolean isInside( Point P)
    {
        Triangle triangle1=new Triangle(A,B,C);
        Triangle triangle2=new Triangle(P,B,C);
        Triangle triangle3=new Triangle(A,P,C);
        Triangle triangle4=new Triangle(A,B,P);
        /* Calculate area of triangle ABC */
        double A =triangle1.calculateArea();

        /* Calculate area of triangle PBC */
        double A1 = triangle2.calculateArea();

        /* Calculate area of triangle PAC */
        double A2 = triangle3.calculateArea();

        /* Calculate area of triangle PAB */
        double A3 = triangle4.calculateArea();

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }


}

public class TriangleProgram {
    private static final int SIZE=3;
    public Type type;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Triangle[] triangleArray = new Triangle[SIZE];
        for(int i=0;i<3;i++) {
            System.out.println("Enter the coordinates for triangle "+(i+1));
            System.out.print("Enter the coordinates (x, y) for vertex A: ");
            double xA = scanner.nextDouble();
            double yA = scanner.nextDouble();
            Point A = new Point(xA, yA);

            System.out.print("Enter the coordinates (x, y) for vertex B: ");
            double xB = scanner.nextDouble();
            double yB = scanner.nextDouble();
            Point B = new Point(xB, yB);

            System.out.print("Enter the coordinates (x, y) for vertex C: ");
            double xC = scanner.nextDouble();
            double yC = scanner.nextDouble();
            Point C = new Point(xC, yC);

            Triangle triangle = new Triangle(A, B, C);
            triangleArray[i]=triangle;
        }
        System.out.println("Enter point to check if its inside the triangle or outside");
        double p1=scanner.nextDouble();
        double p2= scanner.nextDouble();
        Point p=new Point(p1,p2);

        for(int i=0;i<3;i++)
        {
            triangleArray[i].checkTriangleType();
            System.out.println("Triangle "+(i+1)+" is: "+triangleArray[i].getType());
            System.out.println("Triangle "+(i+1)+" is:"+triangleArray[i].getType());
            System.out.println("Perimeter of Triangle "+(i+1)+" is: "+Math.round(triangleArray[i].calculatePerimeter()));
            System.out.println("Area of Triangle "+(i+1)+" is "+Math.round(triangleArray[i].calculateArea()));
            System.out.println("The point is: "+(triangleArray[i].isInside(p)?"inside":"outside")+" the triangle.");
        }

        scanner.close();
    }
}
