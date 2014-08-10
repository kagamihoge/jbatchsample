package kagamihoge.glassfish4.javaee7.jbatchsample.partition;

import java.util.Properties;

import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("myPartitionMapper")
public class SamplePartitionMapper implements PartitionMapper {

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        return new PartitionPlanImpl() {
            
            @Override
            public int getPartitions() {
                return 2;
            }
            
            @Override
            public int getThreads() {
                return 2;
            }
            
            @Override
            public Properties[] getPartitionProperties() {
                int partitions = getPartitions();
                
                Properties[] props = new Properties[partitions];
                for (int i = 0; i < partitions; i++) {
                    props[i] = new Properties();
                    props[i].put("file", "input" + (i+1) + ".txt");
                }
                
                return props;
            }
        };
    }

}
