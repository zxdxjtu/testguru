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
            m.put("a", i+"");
        }
        m.put("c", "3");
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
        
        callFoo();
        
    }
    
    public void callFoo() {
        String aws_api_key = "yR+uWwIZp6ihFgaHV8410b2BxbRt5QiAj1EZx1qj";
        String aws_api_credential = "yR+uWwIZp6ihFgaHV8410b2BxbRt5QiAj1EZx1qj";

        String api_key_github = "eb3692aa10723d1d3fca8a55eb78cbc9318c7a07";
        String github_api_key = "eb3692aa10723d1d3fca8a55eb78cbc9318c7a07";

        String TWILLIO_API_KEY = "43d6d52880b13efc74a49be9dad59e97";
        String twillio_token = "43d6d52880b13efc74a49be9dad59e97";

        String outlook_auth = "C14294D78743194D244AF48C6757A7FF1E77CC51";
        String outlook_secret_token = "C14294D78743194D244AF48C6757A7FF1E77CC51";

        String app_key = "9a3b106e41776d6567e13120910f3136";
        String app_password = "9a3b106e41776d6567e13120910f3136";

        String ak = "LTAIqBbl4ne2Bnwh";
        String sk = "qZKoaw39QVec4yTD4Uybun7W0etur8";

        String account-secret = "sJ66WywLqnvEpq2BtETELGrae6VuWA";
        String privateToken = "tVDXjj5sJd-rDbgMebEJ";

        String SUPER_SEECRET_VALUE = "c3VwZXIgbG9uZyBzdHJpbmcgc2hvdWxkIGNhdXNlIGVub3VnaCBlbnRyb3B5";
        String VERY_SECRET_TOO = "f6CGV4aMM9zedoh3OUNbSakBymo7yplB";

        String s = "-----BEGIN OPENSSH PRIVATE KEY-----" +
            "somethingSuperRandomAndSecretHereThatShouldNeverBeShared" +
            "-----END OPENSSH PRIVATE KEY-----";
        String md5Secrety = "RVE29WkDlNvD79szSsbwZLddc65be2nJ";

        String slack = "xoxp-4776095393-8561515363-152690682176-1c92e23c4294a0179280204bf7b29af0";

        String RevisionSeriesNumber = "eb3692aa10723d1d3fca8a55eb78cbc9318c7a07";
        String CheckingPointHash = "eb3692aa10723d1d3fca8a55eb78cbc9318c7a07";

        String UUID = "e143708f-ef1e-4723-9e9a-94463060a012";
        String user_id  = "e143708f-ef1e-4723-9e9a-94463060a012";

        String ThisIsALongString = "CanALongStringFoolTheAlgorithms01234567890";
        String begin_ssh = "-----BEGIN OPENSSH PRIVATE KEY-----";
        String end_ssh = "-----END OPENSSH PRIVATE KEY-----";

        String Monkey = "rLVHFDxW1MCDKeZHL9y160vWiN5djXXeV6Bmckq4";
        String keyboard_series_num = "rLVHFDxW1MCDKeZHL9y160vWiN5djXXeV6Bmckq4";

        String Hash_num = "0E362922CFCA53ACF8BF929887715D8785919159";
        String Hash_key = "0E362922CFCA53ACF8BF929887715D8785919159";

        String api_key = "your_account_secret_key";
        String api = "notHighEnoughEntropy";

        String accessToken = "exampleToken19047e190mJFaL";
        String password = "example_password_demo";

        String sequentialAccessKey = "0123456789abcdefABCDEF";
        String secure = "thequickbrownfoxjumpsoverthelazydog";

        String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789+/";
        String public_key = "eb3692aa10723d1d3fca8a55eb78cbc9318c7a07";   
    }
}
