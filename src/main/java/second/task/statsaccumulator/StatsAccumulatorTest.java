package second.task.statsaccumulator;

public class StatsAccumulatorTest {
    public static void main(String[] args) {
        StatsAccumulator s = new StatsAccumulatorImpl();
        s.add(1);
        s.add(2);
        System.out.println("Min " + s.getMin()); // 1
        System.out.println("Max " +s.getMax()); // 2
        System.out.println("Count " +s.getCount()); // 2
        System.out.println("Avg " +s.getAvg()); // 1.5
        s.add(-10);
        System.out.println("Min " +s.getMin()); // -10
        s.add(3);
        s.add(8);
        System.out.println("Max " +s.getMax());  // 8
        System.out.println("Count " +s.getCount()); // 5
        System.out.println("Avg " +s.getAvg()); // 1.5
    }
}
