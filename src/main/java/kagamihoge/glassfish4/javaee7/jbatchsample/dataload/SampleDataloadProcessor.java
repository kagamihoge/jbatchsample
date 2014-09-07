package kagamihoge.glassfish4.javaee7.jbatchsample.dataload;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myDataloadProcessor")
public class SampleDataloadProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        return item;
    }
}
