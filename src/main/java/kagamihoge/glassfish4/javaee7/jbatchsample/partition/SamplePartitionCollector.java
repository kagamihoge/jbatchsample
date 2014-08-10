package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.io.Serializable;

import javax.batch.api.partition.PartitionCollector;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myCollector")
public class SamplePartitionCollector implements PartitionCollector {
	@Override
	public Serializable collectPartitionData() throws Exception {
		System.out.println("collectPartitionData");
		return new Hoge();
	}

}
