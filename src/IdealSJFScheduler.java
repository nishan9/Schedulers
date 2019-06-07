import java.util.LinkedList;
import java.util.Queue;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  private Queue<Process> readyQueue;
  
  public IdealSJFScheduler(){
        readyQueue = new LinkedList<Process>();
  }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum. 5 first 8 last 
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    readyQueue.add(process);
  }

  /**
   * The queue of processes will be order by the burst time. 
   */
  public Process schedule() {
      
      Process HeadQ = readyQueue.peek(); 
      if(readyQueue.isEmpty() ==  false)
      {
        int bursttime = HeadQ.getNextBurst(); 
        for (Process p: readyQueue){
            if (bursttime > p.getNextBurst()){
                HeadQ = p;
                bursttime = p.getNextBurst();
            }
        }
        
      }
      readyQueue.remove(HeadQ);
      System.out.println("Scheduler selects process "+HeadQ);
      return HeadQ;
  }
}
