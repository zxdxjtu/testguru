public class mainEntry {

    public final static void main(String[] args) {
        new Profiler.Builder().profilingGroupName("my-profiling-group").build()
            .start();
        int count = 0;
        for (int i = 0; i < 1000000000; i ++) {
            int symbol = i % 2 == 0? 1 : -1;
            count += symbol * i;
        }
        System.out.println(count);
    }
}
