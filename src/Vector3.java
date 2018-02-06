import java.util.Vector;

public class Vector3 {
    private final double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public  double getZ(){
        return this.z;
    }
    //Method format vector
    public double[] Format(){
        return new double[]{this.x,this.y,this.z};
    }
    public Vector3 add(Vector3 v2) {
        return new Vector3(this.x + v2.x, this.y + v2.y, this.z + v2.z);
    }

    public Vector3 MulWithC(double C) {
        return new Vector3(C * this.x, C * this.y, C * this.z);
    }

    public double MulWithVector(Vector3 v2) {
        return this.x * v2.x + this.y * v2.y + this.z * v2.z;
    }

    public Vector3 Exterior(Vector3 v2) {
        return new Vector3(this.y * v2.z - this.z * v2.y, this.z * v2.x - this.x * v2.z, this.x * v2.y - this.y * v2.x);
    }
}
