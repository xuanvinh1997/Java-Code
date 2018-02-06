import java.lang.reflect.Array;
import java.util.Arrays;
import java.lang.Math;

public class Quaternion {
    public final double a, b, c, d;

    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    // Method view array form
    public double[] Format() {
        return new double[]{this.a, this.b, this.c, this.d};
    }

    //Method get scalar part
    public double getScalarPart() {
        return this.a;
    }

    //Method get Vector Part
    public Vector3 getVectorPart() {
        return new Vector3(this.b, this.c, this.d);
    }

    //Method addition
    public Quaternion Plus(Quaternion x2) {
        return new Quaternion(this.a + x2.a, this.b + x2.b, this.c + x2.c, this.d + x2.d);
    }

    //Method subtraction
    public Quaternion Minus(Quaternion x2) {
        return new Quaternion(this.a - x2.a, this.b - x2.b, this.c - x2.c, this.d - x2.d);
    }

    //Method multiplication by scalar
    public Quaternion MulWithC(double C) {
        return new Quaternion(C * this.a, C * this.b, C * this.c, C * this.d);
    }

    //Method multiplication by Quaternion
    public Quaternion MulWithQuaternion(Quaternion x2) {
        double s1 = this.a;
        double s2 = x2.a;
        Vector3 v1 = new Vector3(this.b, this.c, this.d);
        Vector3 v2 = new Vector3(x2.b, x2.c, x2.d);
        Vector3 v = v1.Exterior(v2);
        Vector3 V = v.add(v2.MulWithC(s1).add(v1.MulWithC(s2)));
        return new Quaternion(s1 * s2 - v1.MulWithVector(v2), V.getX(), V.getY(), V.getZ());
    }

    //Method conjugate
    public Quaternion Conjugate() {
        return new Quaternion(this.a, -this.b, -this.c, -this.d);
    }

    //Method norm
    public double Norm() {
        return Math.pow(this.a, 2) + Math.pow(this.b, 2) + Math.pow(this.c, 2) + Math.pow(this.d, 2);
    }

    //Method unit quaternion
    public Quaternion UnitQuaternion() {
        Quaternion instance = new Quaternion(this.a, this.b, this.c, this.d);
        double div = Math.pow(instance.Norm(), -1);
        return instance.MulWithC(div);
    }

    //Method reverse Quaternion
    public Quaternion Reverse() {
        double norm = 1 / (Math.pow(this.a, 2) + Math.pow(this.b, 2) + Math.pow(this.c, 2) + Math.pow(this.d, 2));
        return new Quaternion(this.a * norm, -this.b * norm, -this.c * norm, -this.d * norm);
    }

    //Method divide
    public Quaternion Divide(Quaternion x2) {
        Quaternion x = (new Quaternion(this.a, this.b, this.c, this.d)).MulWithQuaternion(x2.Reverse());
        return x;
    }

    //Method find axis
    public Vector3 FindAxis() {
        Quaternion Ins = new Quaternion(this.a, this.b, this.c, this.d).UnitQuaternion();
        double ax = Ins.b / Math.sqrt(1 - Ins.a * Ins.a);
        double ay = Ins.c / Math.sqrt(1 - Ins.a * Ins.a);
        double az = Ins.d / Math.sqrt(1 - Ins.a * Ins.a);
        return new Vector3(ax, ay, az);
    }

    //Method find angle
    public double FindAngle() {
        Quaternion Ins = new Quaternion(this.a, this.b, this.c, this.d).UnitQuaternion();
        return 2 * Math.acos(Ins.a);
    }

}
