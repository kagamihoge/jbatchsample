package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

import javax.batch.api.partition.PartitionReducer;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("myReducer")
public class SamplePartitionReducer implements PartitionReducer {

    @Inject
    JobContext jobCtx;

    @Override
    public void beginPartitionedStep() throws Exception {
        System.out.println("beginPartitionedStep");
    }

    @Override
    public void beforePartitionedStepCompletion() throws Exception {
        System.out.println("beforePartitionedStepCompletion");

        final HashMap<String, Integer> summary = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            summary.put(String.format("%02d", i), 0);
        }

        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try (BufferedReader br = Files.newBufferedReader(file, Charset.defaultCharset());) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] r = line.split(":");
                        summary.put(r[0], summary.get(r[0]) + Integer.parseInt(r[1]));
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(jobCtx.getProperties().getProperty("output_dir")), visitor);

        System.out.println(summary);
    }

    @Override
	public void afterPartitionedStepCompletion(PartitionStatus status)
			throws Exception {
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        };
	    
	    Files.walkFileTree(Paths.get(jobCtx.getProperties().getProperty("output_dir")), visitor);
		System.out.println("afterPartitionedStepCompletion");
	}

    @Override
    public void rollbackPartitionedStep() throws Exception {
        System.out.println("rollbackPartitionedStep");
    }

}
