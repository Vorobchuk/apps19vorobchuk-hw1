package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    private static final int MinTemp = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatures = Arrays.copyOf(temperatureSeries, temperatureSeries.length);

        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < MinTemp) {
                throw new InputMismatchException();
            }
        }
    }
    public void empty_check() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        empty_check();
        double sum = 0;
        double avg;
        for (int i = 0; i < temperatures.length; i++) {
            sum  += temperatures[i];
        }
        avg = sum / temperatures.length;

        return avg;
    }

    public double deviation() {
        empty_check();
        double avgTemp = this.average();
        double num = 0;
        for (int i = 0; i < temperatures.length; i++){
            num += (temperatures[i] - avgTemp) *  (temperatures[i] - avgTemp);
        }

        return Math.sqrt (num / temperatures.length );
    }

    public double min() {
        empty_check();
        double minT = temperatures[0];
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < minT) {
                minT = temperatures[i];
            }
        }
        return minT;
    }

    public double max() {
        empty_check();
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
        empty_check();
        double dif = 0;
        double difference = (temperatures[0] - tempValue);
        for (int i = 0; i < temperatures.length; i++) {
            if (Math.abs(temperatures[i] - tempValue) <= Math.abs(difference)){
                difference = temperatures[i]-tempValue;
                dif = temperatures[i];
            }
        }
    return dif;
    }

    public double[] findTempsLessThen(double tempValue) {
        empty_check();
        int arrLength = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < tempValue){
                arrLength += 1;
            }
        }
        double [] LessTemp =  new double [arrLength];
        int index = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < tempValue){
                LessTemp[index] = temperatures[i];
                index += 1;
            }
        }
        return LessTemp;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        empty_check();
        int arrLength = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > tempValue){
                arrLength += 1;
            }
        }
        double [] GreaterTemp =  new double [arrLength];
        int index = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > tempValue){
                GreaterTemp[index] = temperatures[i];
                index += 1;
            }
        }
        return GreaterTemp;
    }

    public TempSummaryStatistics summaryStatistics() {
        empty_check();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        empty_check();

        int newLen = temperatures.length * 2;
        double[] result = new double[newLen];

        int i;
        int TempNum= 0;
        for ( i = 0; i < temperatures.length; i++) {
            result[i] = temperatures[i];
            TempNum += temperatures[i];
        }
        for (int j = 0; j < temps.length; j++) {
            if (temps[j] < MinTemp){
                throw new InputMismatchException();
            }
            result[i + j] = temps[j];
            TempNum += temps[j];
        }
        this.temperatures = result;
        return TempNum;
    }
}
