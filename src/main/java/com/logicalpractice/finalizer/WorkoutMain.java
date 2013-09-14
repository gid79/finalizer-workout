package com.logicalpractice.finalizer;


/**
 * Simple program to show that finalize is called from a different thread.
 */
public class WorkoutMain {

  static ThreadLocal<String> stringer = new ThreadLocal<String>();

  public static void main(String[] args) throws InterruptedException {
    new WorkoutMain().run();
    System.gc();
    Thread.sleep(100);
  }

  private void run() {
    String threadName = Thread.currentThread().getName();

    stringer.set(threadName);

    System.out.printf("setting 'stringer' to '%s'\n", threadName);
  }

  @Override
  protected void finalize() throws Throwable {
    String threadName = Thread.currentThread().getName();
    System.out.printf("finalize in thread '%s' stringer value %s\n", threadName, stringer.get());
  }
}
