import java.util.Scanner;

/*
    public static  int q1 = 0;
    public static int q2 = 1;
    public static int q3 = 2;
    public static int s0 = 0;
    public static int s1 = 1;
    public static int s2 = 2;
    public  static int s3 = 3;
*/

class main {
    public static int cooler;
    public static int heater;

    public static int state, nstate;
    public static int sstate, nsstate;
    public static int crs;

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        while((t = sc.nextInt())!= 1000) { // temperature = 1000 => terminate
            first(t);
            System.out.println("cooler: " + cooler + " heater: " + heater + " crs: " + crs + " state: " + state + " second state: " + sstate);
        }
    }

    public static void first(int t){
        switch (state) {
            case 0:
                if (t > 35) {
                    nstate = 1;
                    heater = 0;
                    cooler = 1;
                } else if (t < 15){
                    nstate = 2;
                    heater = 1;
                    cooler = 0;
                }
                break;
            case 1:
                if (t < 25) {
                    nstate = 0;
                    heater = 0;
                    cooler = 0;
                }
                break;
             case 2:
                if (t > 30) {
                    nstate = 0;
                    heater = 0;
                    cooler = 0;
                }
                break;
            default:
        }
        switch (sstate) {
            case 0:
            if (t > 35){
                nsstate = 1;
                crs = 4;
            }
            break;
            case 1:
            if (t > 40) {
                nsstate = 2;
                crs = 6;
            }
            else if (t < 25) {
                nsstate = 0;
                crs = 0;
            }
            break;
            case 2:
            if (t > 45) {
                nsstate = 3;
                crs = 8;
            }
            else if (t < 35) {
                nsstate = 1;
                crs = 4;
            }
            break;
            case 3:
            if (t < 40) {
                nsstate = 2;
                crs = 6;
            }
        }
        state = nstate;
        sstate = nsstate;
    }
}
