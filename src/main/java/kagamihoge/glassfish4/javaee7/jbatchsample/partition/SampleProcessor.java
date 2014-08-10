package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("mySampleProcessor")
public class SampleProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object item) throws Exception {
	    return ((String) item).split(":")[0];
	}
}
