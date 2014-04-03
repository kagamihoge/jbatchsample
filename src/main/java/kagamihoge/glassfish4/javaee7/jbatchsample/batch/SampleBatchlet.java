package kagamihoge.glassfish4.javaee7.jbatchsample.batch;

import javax.batch.api.Batchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myBatchlet")
public class SampleBatchlet implements Batchlet {

    @Override
    public String process() throws Exception {
        System.out.println("## start");
        return null;
    }

    @Override
    public void stop() throws Exception {
        System.out.println("## stop");
    }


}
