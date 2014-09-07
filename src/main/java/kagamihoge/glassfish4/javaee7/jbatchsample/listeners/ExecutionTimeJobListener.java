package kagamihoge.glassfish4.javaee7.jbatchsample.listeners;

import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("executionTimeJobListener")
public class ExecutionTimeJobListener implements JobListener {

    private long start = 0;
    
    @Override
    public void beforeJob() throws Exception {
        start = System.currentTimeMillis();

    }

    @Override
    public void afterJob() throws Exception {
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
