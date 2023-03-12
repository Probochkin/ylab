package second.task.statsaccumulator;

public class StatsAccumulatorImpl implements StatsAccumulator {
    private int count = 0;
    private int min = 0;
    private int max = 0;
    private double sum = 0;


    @Override
    public void add(int value) {
        if (count == 0) {
            this.min = value;
            this.max = value;
            this.sum = value;
            this.count++;
        } else {
            this.count++;
            if (this.min > value) {
                this.min = value;
            }
            if (this.max < value) {
                this.max = value;
            }
            this.sum = this.sum + value;
        }
    }

    @Override
    public int getMin() {
        return this.min;
    }

    @Override
    public int getMax() {
        return this.max;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Double getAvg() {
        return this.sum / this.count;
    }
}
