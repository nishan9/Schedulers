import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

    private Queue<Process> readyQueue;
    //Time Quantum 
    int tq;

    public FeedbackRRScheduler(){
        readyQueue = new LinkedList<Process>();
    }

    /**
     * Adds a process to the ready queue.
     * usedFullTimeQuantum is true if process is being moved to ready
     * after having fully used its time quantum.
     */
    public void ready(Process process, boolean usedFullTimeQuantum) {
        readyQueue.offer(process);
        if (usedFullTimeQuantum == true){
            process.setPriority(process.getPriority() + 1);
        }
    }

    /**
     * The Queue is sorted in burst times
     */
    public Process schedule() {
        Process HeadQ = readyQueue.peek();
        if(readyQueue.isEmpty() ==  false){
            for(Process p : readyQueue){
                if(p.getPriority() < HeadQ.getPriority())
                    HeadQ = p;
            }
        }
        readyQueue.remove(HeadQ);
        System.out.println("Scheduler selects process "+HeadQ);
        return HeadQ;
    }

    /**
     * Overrides and updates the Time Quantum
     */
    @Override
    public int getTimeQuantum(){
        return tq;
    }
    /**
     * Gets the Time Quantum from the parameter file. 
     * @param parameters 
     */
    @Override
    public void initialize(Properties parameters) {
        tq = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }
}