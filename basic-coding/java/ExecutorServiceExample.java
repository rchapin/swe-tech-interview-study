import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {

  public static void main(String[] args) throws Exception {
    List<Future<Long>> futures = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (long i = 1; i <= 9; i++) {
      futures.add(executorService.submit(new Factorial(i)));
    }
    
    Long total = 0L;
    for (Future<Long> f : futures) {
      total += f.get();
      System.out.println("Current total = " + total);
    }
    System.out.println("Total value = " + total);
  }

  public static class Factorial implements Callable<Long> {

    long i;

    public Factorial(long i) {
      this.i = i;
    }

    @Override
    public Long call() throws Exception {
      long retVal = 1;
      long curVal = 1;

      while (curVal <= i) {
        retVal *= curVal;
        System.out.printf("Thread id=%d, sleeping%n", Thread.currentThread().getId());
        Thread.sleep(1500L);
        curVal++;
      }

      return Long.valueOf(retVal);
    }
  }
}
