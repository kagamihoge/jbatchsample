package kagamihoge.glassfish4.javaee7.jbatchsample.dataload;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kagamihoge.glassfish4.javaee7.jbatchsample.entity.Hoge;

@Dependent
@Named("myDataloadWriter")
public class SampleDataloadWriter implements ItemWriter {

    @PersistenceContext(unitName = "glassfish4oracle11gee")
    private EntityManager em;

    @Inject
    JobContext jobCtx;
    
    private int hogeId = 0;
    
    private int count = 0;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        long eId = jobCtx.getExecutionId();
        String t = BatchRuntime.getJobOperator().getParameters(eId).getProperty("startId");
        hogeId =  Integer.parseInt(t);
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object o : items) {
            Hoge newRecord = new Hoge(hogeId, (String)o);
            em.persist(newRecord);
            
            hogeId++;
            count++;
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("# insert count=" + count);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
