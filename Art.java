// Simulates the creation of a branching lightning bolt
public class Art {

    // Returns an array of either the first number in the String array or the second.
    private static double[] arrSplitter(int index, String[] args) {
        double[] numArr = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            if (index == 1) {
                numArr[i] = Double.parseDouble(args[i].substring(
                        0, args[i].indexOf("|")));
            }
            else {
                numArr[i] = Double.parseDouble(args[i].substring(
                        args[i].indexOf("|") + 1));
            }
        }
        return numArr;
    }

    // Simulates the way a bolt splits
    private static void boltBranch(int times, double theta, double x, double y,
                                   int bends, double length) {
        if (times == 0) return;
        if (times > 20) {
            StdDraw.setPenRadius(0.02);
        }
        else {
            StdDraw.setPenRadius(times * 0.001);
        }
        String[] xy = bolt(theta, x, y, bends, length);
        double[] xArr = arrSplitter(1, xy);
        double[] yArr = arrSplitter(2, xy);
        for (int j = 1; j < xy.length; j++) {
            int rand = StdRandom.uniform(4);
            if (rand == 1 || rand == 2) {
                if (rand == 1) {
                    boltBranch(times - 1, theta + 20, xArr[j], yArr[j], bends, length);
                }
                else {
                    boltBranch(times - 1, theta - 20, xArr[j], yArr[j], bends, length);
                }

            }
        }
    }

    // Creates a bolt with line length length and bends
    private static String[] bolt(double theta, double x, double y,
                                 int bends, double length) {
        String[] fullArr = new String[bends + 1];
        double tempX;
        double tempY;
        double[] xArr = new double[bends + 1];
        double[] yArr = new double[bends + 1];
        xArr[0] = x;
        yArr[0] = y;

        for (int i = 1; i < bends + 1; i++) {
            if (i % 2 == 0) {
                xArr[i] = xArr[i - 1] - length / 2;
                yArr[i] = yArr[i - 1] - length;
                tempX = xArr[i];
                tempY = yArr[i];
                xArr[i] = (tempX - x) * Math.cos(Math.toRadians(theta)) - (tempY - y) *
                        Math.sin(Math.toRadians(theta)) + x;
                yArr[i] = (tempY - y) * Math.cos(Math.toRadians(theta)) + (tempX - x) *
                        Math.sin(Math.toRadians(theta)) + y;
                StdDraw.line(xArr[i - 1], yArr[i - 1], xArr[i],
                             yArr[i]);
            }
            else {
                xArr[i] = xArr[i - 1] + length / 2;
                yArr[i] = yArr[i - 1] - length;

                tempX = xArr[i];
                tempY = yArr[i];
                xArr[i] = (tempX - x) * Math.cos(Math.toRadians(theta)) - (tempY - y) *
                        Math.sin(Math.toRadians(theta)) + x;
                yArr[i] = (tempY - y) * Math.cos(Math.toRadians(theta)) + (tempX - x) *
                        Math.sin(Math.toRadians(theta)) + y;
                StdDraw.line(xArr[i - 1], yArr[i - 1], xArr[i],
                             yArr[i]);
            }
        }
        for (int j = 0; j < bends + 1; j++) {
            fullArr[j] = xArr[j] + "|" + yArr[j];
        }
        return fullArr;
    }

    // Takes an integer command-line argument n;
    // fills the background with black
    // and creates a bolt branch with 5 bends and line length 0.5
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-10, +10);
        StdDraw.filledSquare(0, 0, 10);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        boltBranch(n, 0, 0, 0, 5, 0.5);
    }
}
