package kagamihoge.glassfish4.javaee7.jbatchsample.rest;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/startdataload")
public class StartDataload {
    
    @GET
    @Produces("text/plain")
    public String go() throws SystemException, Exception {
        JobOperator job = BatchRuntime.getJobOperator();

        long id = job.start("sample-job-dataload", null);
        System.out.println("id = " + id);
        
        return "" + id;
    }
}
