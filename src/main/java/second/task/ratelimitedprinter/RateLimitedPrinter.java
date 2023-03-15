package second.task.ratelimitedprinter;


public class RateLimitedPrinter  {
    private int interval;
    private long currentTime;
    public RateLimitedPrinter(int interval) {
        this.interval = interval;
        this.currentTime = 0;
    }
    public void print(String message) {
        if (System.currentTimeMillis() - this.currentTime > this.interval) {
            System.out.println(message);
            this.currentTime = System.currentTimeMillis();
        }

    }
}
