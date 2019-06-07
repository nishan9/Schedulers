import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {
    
    private Queue<Process> readyQueue;
    int tq; 
    
    public RRScheduler(){
        readyQueue = new LinkedList<Process>();
    }
    /**
     * Gets the time quantum from the parameter file 
     * @param parameters 
     */
    @Override  
    public void initialize(Properties parameters) {
      tq = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    readyQueue.offer(process);
  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    System.out.println("Scheduler selects process "+readyQueue.peek());
    return readyQueue.poll();
  }
  
  /**
   * Overrides and updates the time quantum via the initialise method. 
   */
    @Override
  public int getTimeQuantum() {
      return tq; 
  }
  
}
