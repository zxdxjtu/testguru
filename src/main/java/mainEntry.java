import java.util.concurrent.ConcurrentHashMap;
public class mainEntry {

    public final static void main(String[] args) {
        new Profiler.Builder().profilingGroupName("test").build()
            .start();
        int count = 0;
        for (int i = 0; i < 1000000000; i ++) {
            int symbol = i % 2 == 0? 1 : -1;
            count += symbol * i;
        }
        for (int i = 0; i < 1000000000000000; i ++) {
            int symbol = i % 3 == 0? 1 : -1;
            count += symbol * i;
            count *= 2;
        }
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        m.put("a", "b");
        System.out.println(count);
    }
}
