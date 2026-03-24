import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BasicMultiThreading {

  private int limit;
  private int count;
  private Object lock;

  public BasicMultiThreading(int limit) {
    this.count = 1;
    this.limit = limit;
    this.lock = new Object();
  }

  public String getOutput() throws Exception {
    StringBuilder buf = new StringBuilder();

    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.submit(new Incrementor(true, buf));
    executor.submit(new Incrementor(false, buf));
    executor.shutdown();
    executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    return buf.toString();
  }

  public static void main(String[] args) throws Exception {
    BasicMultiThreading b = new BasicMultiThreading(5);
    System.out.printf("%d, %s%n", 5, b.getOutput());
  }

  public class Incrementor implements Runnable {
    boolean even;
    int expectedMod;
    StringBuilder buf;

    public Incrementor(boolean even, StringBuilder buf) {
      this.even = even;
      this.buf = buf;
      if (even) {
        expectedMod = 0;
      } else {
        expectedMod = 1;
      }
    }

    @Override
    public void run() {

      while (count < limit) {
        synchronized(lock) {
          try {
            while ((count%2) != expectedMod) {
              lock.wait();
            }
            buf.append("0" + count);
            count++;
          } catch (Exception e) {
            e.printStackTrace();
          }
          lock.notifyAll();
        }
      }
    }
  }

}
