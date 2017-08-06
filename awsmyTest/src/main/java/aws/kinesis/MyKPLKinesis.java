package aws.kinesis;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhang.jianming on 2017/1/5.
 */
public class MyKPLKinesis {

    Logger logger = LoggerFactory.getLogger(MyKPLKinesis.class);

    @Autowired
    private KinesisProducer producer;

    @Autowired
    private AWSCredentialsProvider provider;

    @Autowired
    private KinesisProducerConfiguration configuration;

    public MyKPLKinesis(){
        provider = new AWSCredentialsProvider() {
            public AWSCredentials getCredentials() {
                return new AWSCredentials() {
                    public String getAWSAccessKeyId() {
                        return null;
                    }

                    public String getAWSSecretKey() {
                        return null;
                    }
                };
            }

            public void refresh() {

            }
        };
        configuration.setCredentialsProvider(provider);
        configuration.setRegion(Region.getRegion(Regions.CN_NORTH_1).getName());
        producer = new KinesisProducer(configuration);
    }

    public void putStream(){
        for(int i=0; i<1000; i++){
            logger.info("");
        }
    }
}
