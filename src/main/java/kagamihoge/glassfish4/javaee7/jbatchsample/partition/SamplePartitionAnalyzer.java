package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.io.Serializable;

import javax.batch.api.partition.PartitionAnalyzer;
import javax.batch.runtime.BatchStatus;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myAnalyzer")
public class SamplePartitionAnalyzer implements PartitionAnalyzer {

	@Override
	public void analyzeCollectorData(Serializable data) throws Exception {
		System.out.println("analyzeCollectorData:" + data);
	}

	@Override
	public void analyzeStatus(BatchStatus batchStatus, String exitStatus)
			throws Exception {
		System.out.println("analyzeStatus:" + batchStatus + ":" + exitStatus);
	}

}
