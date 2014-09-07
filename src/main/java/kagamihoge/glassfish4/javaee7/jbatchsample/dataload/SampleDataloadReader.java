package kagamihoge.glassfish4.javaee7.jbatchsample.dataload;

import java.io.BufferedReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("myDataloadReader")
public class SampleDataloadReader implements ItemReader {

    private BufferedReader br;

    @Inject
    JobContext jobCtx;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        long execID = jobCtx.getExecutionId();
        Properties parameters = BatchRuntime.getJobOperator().getParameters(execID);
        String inputFile = parameters.getProperty("file");
        
        br = Files.newBufferedReader(Paths.get(inputFile), Charset.defaultCharset());
    }

    @Override
    public Object readItem() throws Exception {
        String line = br.readLine();
        if (line == null) {
            return null;
        }
        return line;
    }

    @Override
    public void close() throws Exception {
        br.close();
    }
    
    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
