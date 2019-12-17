import java.util.concurrent.ConcurrentHashMap;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;


public class mainEntry {
    
    private static final String ACCESS_KEY_PROPERTY_NAME = "s3.accessKey";
    private static final String SECRET_KEY_PROPERTY_NAME = "s3.secretKey";
    private static final String EMPTY_CREDENTIAL = "empty";

    private static final String BUCKET_NAME_PROPERTY_NAME = "s3.bucketName";
    private static final String DEFAULT_BUCKET_NAME = "async-client-test";

    private String bucketName;
    BasicAWSCredentials credentials;

    private TestS3Object PL = TestS3Object.withNameAndRandomMetadata("COUNTRY_BY_DATE/2014/05/PL", 2);
    private TestS3Object US = TestS3Object.withNameAndRandomMetadata("COUNTRY_BY_DATE/2014/05/US", 3);
    private TestS3Object CZ = TestS3Object.withNameAndRandomMetadata("COUNTRY_BY_DATE/2014/06/CZ", 0);
    private TestS3Object UK = TestS3Object.withNameAndRandomMetadata("COUNTRY_BY_DATE/2014/07/UK", 1);

    ClientConfiguration configuration;

    List<String> fieldsToIgnore = new ArrayList<>();

    public final static void main(String[] args) {
        new Profiler.Builder().profilingGroupName("test").build()
            .start();
        int count = 0;
        for (int i = 0; i < 1000000000; i ++) {
            int symbol = i % 2 == 0? 1 : -1;
            count += symbol * i;
        }
        for (int i = 0; i < 1000000000000000; i ++) {
            int symbol = i % 3 == 0? 1 : -1;
            count += symbol * i;
            count *= 2;
        }
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        m.put("a", "b");
        String b = m.get("a");
        for (int i = 0; i < 10000000000; i ++) {
            m.put("a", i);
        }
        System.out.println(count);
        AmazonS3Client amazonS3Client;
        AsyncS3Client client;
        
        String accessKey = System.getProperty(ACCESS_KEY_PROPERTY_NAME, EMPTY_CREDENTIAL);
        String secretKey = System.getProperty(SECRET_KEY_PROPERTY_NAME, EMPTY_CREDENTIAL);

        credentials = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3Client = new AmazonS3Client(credentials);

        if (EMPTY_CREDENTIAL.equals(accessKey) || EMPTY_CREDENTIAL.equals(secretKey)) {
            LOGGER.info("No amazon configuration was found. Assuming fake S3 listens on port 12345");

            amazonS3Client.setEndpoint("http://localhost:12345");
            configuration = ClientConfiguration
                    .builder()
                    .connectTo("localhost:12345")
                    .useCredentials(credentials)
                    .build();
        } else {
            configuration = ClientConfiguration
                    .builder()
                    .useCredentials(credentials)
                    .build();

            LOGGER.info("Found amazon configuration. Using real S3");
        }

        client = new AsyncS3Client(configuration, HttpClientFactory.defaultFactory());
        bucketName = System.getProperty(BUCKET_NAME_PROPERTY_NAME, DEFAULT_BUCKET_NAME);
        
        Single<ObjectListing> listing = client.listObjects(bucketName);
        ObjectListing amazonListing = amazonS3Client.listObjects(bucketName);
        
    }
}
