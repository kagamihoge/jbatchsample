package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.io.BufferedWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("mySampleWriter")
public class SampleWriter implements ItemWriter {

    @Inject
    JobContext jobCtx;
    
    private Map<String, Integer> result;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        result = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            result.put(String.format("%02d", i), 0);
        }
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object i : items) {
            String time = (String) i;
            Integer newCount = result.get(time) + 1;

            result.put(time, newCount);
        }
        System.out.println("## execID=" + jobCtx.getExecutionId() + " writes:" + items.size());
    }
    
    @Override
    public void close() throws Exception {
        long execID = jobCtx.getExecutionId();
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(jobCtx.getProperties().getProperty("output_dir"), execID + ".txt"),
                Charset.defaultCharset())) {
            for (Map.Entry<String, Integer> e : result.entrySet()) {
                bw.write(e.getKey() + ":" + e.getValue());
                bw.newLine();
            }
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
