package aws.dynamoDB;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;

import java.util.*;

/**
 * Created by zjm on 2017/2/23.
 */
public class DynamoDBOperation {

    public static void main(String[] args) {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(
                new AWSCredentials() {
                    public String getAWSAccessKeyId() {
                        return "AKIAO7WM5Q2RNP5GPH4Q";
                    }

                    public String getAWSSecretKey() {
                        return "8v+fo08JzAapi3gY+bc+mx6FZdM77kN8iE11Fl6l";
                    }
                });//.withEndpoint("http://dynamodb.cn-north-1.amazonaws.com.cn");
        client.setRegion(Region.getRegion(Regions.CN_NORTH_1));
        DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("@@@@@@jinlaile##########@@@@@@@@@");

        Table table = dynamoDB.getTable("ucs-test-dynamodb-passport-appid-oauth-rel");

        int year = 2015;
        String title = "The Big New Movie";

        PrimaryKey key = new PrimaryKey("id", "1");

//  GetItemSpec spec = new GetItemSpec().withPrimaryKey(key);

        try {
            System.out.println("Attempting to read the item...");
            Item outcome = table.getItem("id", "1");
            System.out.println("GetItem succeeded: " + outcome);
            outcome = table.getItem("id", "101");
            System.out.println(outcome);
//            loadSampleProducts(table);
            writeTable(table);
//            readTable(table);
        } catch (Exception e) {
            System.err.println("Unable to read item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
    }

    private static void writeTable(Table table){
        /*int num = 5000;
        for(int i=1; i<num; i++){
//            long start = System.nanoTime();
            long start = System.currentTimeMillis();
            writeTalbe(table, String.valueOf(i), i);
            System.out.println("执行一条数据插入需要" + (System.currentTimeMillis() - start) + "豪秒");
        }*/
        for(int i=0; i<1000; i++){
            new Thread(() -> {
                for(int j=0; j<100; j++){
                    long start = System.currentTimeMillis();
                    DynamoDBOperation.writeTalbe(table, String.valueOf(System.nanoTime()), (new Random().nextInt(100000) + 100000));
                    System.out.println("插入一条数据耗时---" + (System.currentTimeMillis() - start) + "---毫秒 ！");
                }
            }).start();
        }
    }

    private static void writeTalbe(Table table, String s, int p){
        try{
            Item item = new Item().withPrimaryKey("id", s).withNumber("PageCount", p);
            table.putItem(item);
        }catch(Exception e) {
            System.err.println("Failed to put item in " + table.getTableName());
            System.err.println(e.getMessage());
        }
    }

    private static void readTable(Table table){
        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#id", "id");
        valueMap.put(":yyy", "100");
        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#id = :yyy").withNameMap(nameMap).withValueMap(valueMap);
        ScanSpec scanSpec = new ScanSpec();
        ItemCollection<QueryOutcome> items1 = table.query(querySpec);
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);
        Iterator<Item> iterator = items.iterator();

        System.out.println("Query: printing results...");

        /*while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }*/

        items.forEach(item -> System.out.println(item.toJSONPretty()));

        System.out.println("---------id = 100 的结果-----------");
        System.out.println("---------------------------------------------------");
        System.out.println("====================================================");
        System.out.println("====================================================");
        System.out.println("====================================================");
        System.out.println("====================================================");

        items1.forEach(item -> System.out.println(item.toJSONPretty()));

      /*  Iterator<Item> iterator1 = items1.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next().toJSONPretty());
        }*/
    }

    private static void loadSampleProducts(Table table) {

        try {

            System.out.println("Adding data to table");

            Item item = new Item()
                .withPrimaryKey("id", "100")
                .withString("Title", "Book 101 Title")
                .withString("ISBN", "111-1111111111")
                .withStringSet("Authors",
                    new HashSet<String>(Arrays.asList("Author1")))
                .withNumber("Price", 2)
                .withString("Dimensions", "8.5 x 11.0 x 0.5")
                .withNumber("PageCount", 500)
                .withBoolean("InPublication", true)
                .withString("ProductCategory", "Book");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "102")
                .withString("Title", "Book 102 Title")
                .withString("ISBN", "222-2222222222")
                .withStringSet("Authors", new HashSet<String>(
                    Arrays.asList("Author1", "Author2")))
                .withNumber("Price", 20)
                .withString("Dimensions", "8.5 x 11.0 x 0.8")
                .withNumber("PageCount", 600)
                .withBoolean("InPublication", true)
                .withString("ProductCategory", "Book");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "103")
                .withString("Title", "Book 103 Title")
                .withString("ISBN", "333-3333333333")
                .withStringSet( "Authors", new HashSet<String>(
                    Arrays.asList("Author1", "Author2")))
                // Intentional. Later we'll run Scan to find price error. Find
                // items > 1000 in price.
                .withNumber("Price", 2000)
                .withString("Dimensions", "8.5 x 11.0 x 1.5")
                .withNumber("PageCount", 600)
                .withBoolean("InPublication", false)
                .withString("ProductCategory", "Book");
            table.putItem(item);

            // Add bikes.

            item = new Item()
                .withPrimaryKey("id", "201")
                .withString("Title", "18-Bike-201")
                // Size, followed by some title.
                .withString("Description", "201 Description")
                .withString("BicycleType", "Road")
                .withString("Brand", "Mountain A")
                // Trek, Specialized.
                .withNumber("Price", 100)
                .withString("Gender", "M")
                // Men's
                .withStringSet("Color", new HashSet<String>(
                    Arrays.asList("Red", "Black")))
                .withString("ProductCategory", "Bicycle");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "202")
                .withString("Title", "21-Bike-202")
                .withString("Description", "202 Description")
                .withString("BicycleType", "Road")
                .withString("Brand", "Brand-Company A")
                .withNumber("Price", 200)
                .withString("Gender", "M")
                .withStringSet("Color", new HashSet<String>(
                    Arrays.asList("Green", "Black")))
                .withString("ProductCategory", "Bicycle");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "203")
                .withString("Title", "19-Bike-203")
                .withString("Description", "203 Description")
                .withString("BicycleType", "Road")
                .withString("Brand", "Brand-Company B")
                .withNumber("Price", 300)
                .withString("Gender", "W")
                // Women's
                .withStringSet( "Color", new HashSet<String>(
                    Arrays.asList("Red", "Green", "Black")))
                .withString("ProductCategory", "Bicycle");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "204")
                .withString("Title", "18-Bike-204")
                .withString("Description", "204 Description")
                .withString("BicycleType", "Mountain")
                .withString("Brand", "Brand-Company B")
                .withNumber("Price", 400)
                .withString("Gender", "W")
                .withStringSet("Color", new HashSet<String>(
                    Arrays.asList("Red")))
                .withString("ProductCategory", "Bicycle");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("id", "205")
                .withString("Title", "20-Bike-205")
                .withString("Description", "205 Description")
                .withString("BicycleType", "Hybrid")
                .withString("Brand", "Brand-Company C")
                .withNumber("Price", 500)
                .withString("Gender", "B")
                // Boy's
                .withStringSet("Color", new HashSet<String>(
                    Arrays.asList("Red", "Black")))
                .withString("ProductCategory", "Bicycle");
            table.putItem(item);

        } catch (Exception e) {
            System.err.println("Failed to create item in ");
            System.err.println(e.getMessage());
        }

    }
}
