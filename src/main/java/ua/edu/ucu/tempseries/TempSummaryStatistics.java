package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private double AvgTemp;
    private double DevTemp;
    private double MinTemp;
    private double MaxTemp;

    TempSummaryStatistics(double avg, double dev, double min, double max) {
        AvgTemp = avg;
        DevTemp = dev;
        MinTemp = min;
        MaxTemp = max;

    }
    public double getAvgTemp() {
        return AvgTemp;
    }
    public double getDevTemp() {
        return DevTemp;
    }
    public double getMinTemp() {
        return MinTemp;
    }
    public double getMaxTemp() {
        return MaxTemp;
    }
}
