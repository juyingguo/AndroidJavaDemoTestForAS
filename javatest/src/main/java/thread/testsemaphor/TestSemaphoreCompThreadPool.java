package thread.testsemaphor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSemaphoreCompThreadPool {

                public static void main(String[] args) {

                // 线程池

                ExecutorService exec = Executors.newCachedThreadPool();
//                ExecutorService exec = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
//                ExecutorService exec = Executors.newSingleThreadExecutor();
//                ExecutorService exec = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
//                ExecutorService exec = Executors.newFixedThreadPool(20);


                 // 模拟20个客户端访问

                 for (int index = 0; index < 20; index++) {

                              final int NO = index;

                              Runnable run = new Runnable() {

                                                 public void run() {

                                                            try {


                                                                    System.out.println("Accessing: " + NO);

                                                                    Thread.sleep((long) (1 * 1000));


                                                            } catch (InterruptedException e) {

                                                                    e.printStackTrace();

                                                            }

                                                  }

                                      };

                      exec.execute(run);

             }

             // 退出线程池

             exec.shutdown();

       }

} 