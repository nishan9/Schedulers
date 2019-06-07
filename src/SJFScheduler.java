import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;
/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  Double initialBurst;
  Double alphaBurst;
  Double nextBurst;

    private PriorityQueue<Process> readyQueue;

    /**
     * Creates a priority Queue order by the burst burst number. 
     */
    public SJFScheduler(){
        readyQueue = new PriorityQueue<>(new Comparator<Process>(){
            @Override
            public int compare(Process o1, Process o2) {
                Double p1 = getBurst(o1); 
                Double p2 = getBurst(o2); 
                return p1.compareTo(p2); 
            }
        
        }); 
    }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
      readyQueue.offer(process);
      System.out.println("Burst is " + getBurst(process));
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
     * Gets the values from the parameter file 
     * @param parameters 
     */
    @Override
    public void initialize(Properties parameters) {
        initialBurst = Double.parseDouble(parameters.getProperty("initialBurstEstimate"));
        alphaBurst = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    }
    /**
     * updates the burst time given the calculation to predict the next burst time 
     * @param e
     * @return 
     */
    public double getBurst(Process e){
        nextBurst = alphaBurst * e.getRecentBurst() + (1- alphaBurst) * e.getRecentBurst();
        return nextBurst; 
    }
}
