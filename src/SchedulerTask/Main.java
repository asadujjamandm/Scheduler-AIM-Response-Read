package SchedulerTask;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

class Main{
    public static void main(String[] args)
    {
        try {            
            Timer time = new Timer(); // Instantiate Timer Object
    
            // Start running the task on Monday at 15:40:00, period is set to 8 hours
            // if you want to run the task immediately, set the 2nd parameter to 0
            time.schedule(new Scheduler(), 0, TimeUnit.SECONDS.toMillis(30));        
        }
        catch(Exception ex){
            ex.printStackTrace();
        }        
    }
}

