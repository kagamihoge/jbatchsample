package kagamihoge.glassfish4.javaee7.jbatchsample;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;

import kagamihoge.glassfish4.javaee7.jbatchsample.batch.SampleBatchlet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JBatchTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(SampleBatchlet.class)
            .addAsResource("META-INF/batch-jobs/sample-job.xml")
            .addAsResource("META-INF/batch-jobs/sample-job-named.xml")
            ;
    }
    
    @Inject
    public SampleBatchlet batchlet;
    
    @Test
    public void testBatchlet() {
        try {
            batchlet.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testBatch() {
        JobOperator job = BatchRuntime.getJobOperator();
        
        long id = job.start("sample-job-named", null);
        System.out.println("id = " + id);
    }

}
