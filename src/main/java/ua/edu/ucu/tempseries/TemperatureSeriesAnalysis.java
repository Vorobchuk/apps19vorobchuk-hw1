package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    private static final int MINTEMP = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatures = Arrays.copyOf(temperatureSeries, temperatureSeries.length);

        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < MINTEMP) {
                throw new InputMismatchException();
            }
        }
    }
    public void emptyCheck() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        emptyCheck();
        double sum = 0;
        double avg;
        for (int i = 0; i < temperatures.length; i++) {
            sum  += temperatures[i];
        }
        avg = sum / temperatures.length;

        return avg;
    }

    public double deviation() {
        emptyCheck();
        double avgTemp = this.average();
        double num = 0;
        for (int i = 0; i < temperatures.length; i++) {
            num += (temperatures[i] - avgTemp) *  (temperatures[i] - avgTemp);
        }

        return Math.sqrt (num / temperatures.length);
    }

    public double min() {
        emptyCheck();
        double minT = temperatures[0];
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < minT) {
                minT = temperatures[i];
            }
        }
        return minT;
    }

    public double max() {
        emptyCheck();
        double maxT = temperatures[0];
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > maxT) {
                maxT = temperatures[i];
            }
        }
        return maxT;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        emptyCheck();
        double difference = temperatures[0] - tempValue;
        double dif = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (Math.abs(temperatures[i] - tempValue) <= Math.abs(difference)) {
                difference = temperatures[i]-tempValue;
                dif = temperatures[i];
            }
        }
    return dif;
    }

    public double[] findTempsLessThen(double tempValue) {
        emptyCheck();
        int arrLength = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < tempValue) {
                arrLength += 1;
            }
        }
        double [] lessTemp =  new double [arrLength];
        int index = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < tempValue) {
                lessTemp[index] = temperatures[i];
                index += 1;
            }
        }
        return lessTemp;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        emptyCheck();
        int arrLength = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > tempValue) {
                arrLength += 1;
            }
        }
        double [] greaterTemp =  new double [arrLength];
        int index = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > tempValue) {
                greaterTemp[index] = temperatures[i];
                index += 1;
            }
        }
        return greaterTemp;
    }

    public TempSummaryStatistics summaryStatistics() {
        emptyCheck();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        emptyCheck();

        int newLen = temperatures.length * 2;
        double[] result = new double[newLen];

        int i;
        int tempNum = 0;
        for (i = 0; i < temperatures.length; i++) {
            result[i] = temperatures[i];
            tempNum += temperatures[i];
        }
        for (int j = 0; j < temps.length; j++) {
            if (temps[j] < MINTEMP) {
                throw new InputMismatchException();
            }
            result[i + j] = temps[j];
            tempNum += temps[j];
        }
        this.temperatures = result;
        return tempNum;
    }
}
