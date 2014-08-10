package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.io.BufferedReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.batch.api.chunk.ItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("mySampleReader")
public class SampleReader implements ItemReader {

    private BufferedReader br;

    @Inject
    JobContext jobCtx;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long execID = jobCtx.getExecutionId();
        Properties parameters = jobOperator.getParameters(execID);
        String inputDir = jobCtx.getProperties().getProperty("input_dir");;
        String inputFile = parameters.getProperty("file");
        System.out.println("## execID=" + execID + " " +  inputDir + " " + inputFile);
        
        br = Files.newBufferedReader(Paths.get(inputDir, inputFile), Charset.defaultCharset());
    }

    @Override
    public Object readItem() throws Exception {
        String line = br.readLine();
        if (line == null || line.length() <= 0) {
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
