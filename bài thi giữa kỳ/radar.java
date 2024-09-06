
interface Signal {
    double getValue(int n);
}


class DiscreteSignal implements Signal {
    private double[] values;

    
    public DiscreteSignal(double[] values) {
        this.values = values;
    }

    
    @Override
    public double getValue(int n) {
        if (n >= 0 && n < values.length) {
            return values[n];
        } else {
            return 0; 
        }
    }
}


class Radar {
    
    public static double[] analyzeSignal(int n) {
        double[] signal = new double[16];
        for (int i = 0; i <= 15; i++) {
            if (i >= 0 && i <= 15) {
                signal[i] = 1 - (double) i / 15;
            } else {
                signal[i] = 0;
            }
        }
        return signal;
    }

    
    public static void printSignal(double[] signal) {
        for (int i = 0; i < signal.length; i++) {
            System.out.println("x(" + i + ") = " + signal[i]);
        }
    }

    public static void main(String[] args) {
        double[] signal = analyzeSignal(4);
        printSignal(signal);
    }
}
