/**
 * Created by zhangsheng on 2016/11/9.
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 1; !StdIn.isEmpty(); i++) {
            String str = StdIn.readString();
            if (i <= k) {
                rq.enqueue(str);
            } else {
                int j = StdRandom.uniform(i);
                if (j < k) {
                    rq.dequeue();
                    rq.enqueue(str);
                }
            }
        }
        while (!rq.isEmpty()) {
            System.out.println(rq.dequeue());
        }
//        String str = StdIn.readString();
//        rq.enqueue(str);
//        while (!StdIn.isEmpty()) {
//            str = StdIn.readString();
//            rq.enqueue(str);
//        }
//        for (int i = 0; i < k; i++) {
//            System.out.println(rq.dequeue());
//        }
    }
}
