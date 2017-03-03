package aws.dynamoDB;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

/**
 * Created by zjm on 2017/2/24.
 */
public class CreateTableClient {

    private String tableName;
    private String partitionKey;
    private String partitionKeyType;
    private String rangKey;
    private String rangKeyType;
    private long readCapacityUnits;
    private long writeCapacityUnits;
    private DynamoDB dynamoDB;

    CreateTableClient(){
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
        dynamoDB = new DynamoDB(client);
    }

    public static void main(String[] args) {
        CreateTableClient tableClient = new CreateTableClient();
        tableClient.setTableName("passport-test-java-createTable-01");
        tableClient.setPartitionKey("id");
        tableClient.setRangKey("name");
        tableClient.setReadCapacityUnits(5);
        tableClient.setWriteCapacityUnits(5);
        tableClient.createTable();
        tableClient.deleteTable("passport-test-java-createTable-01");
    }

    public void createTable(){

        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                Arrays.asList(
                    new KeySchemaElement(partitionKey, KeyType.HASH),  //Partition key
                    new KeySchemaElement(rangKey, KeyType.RANGE)), //Sort key
                    Arrays.asList(
                        new AttributeDefinition(partitionKey, ScalarAttributeType.N),
                        new AttributeDefinition(rangKey, ScalarAttributeType.S)),
                    new ProvisionedThroughput(readCapacityUnits, writeCapacityUnits));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }

    }

    private void createTable(String tableName, String hashKeyName, String hashKeyType, String rangeKeyName,
                             String rangeKeyType, long readCapacityUnits, long writeCapacityUnits) {

        try {

            ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
            keySchema.add(new KeySchemaElement()
                .withAttributeName(hashKeyName)
                .withKeyType(KeyType.HASH));

            ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName(hashKeyName)
                .withAttributeType(hashKeyType));

            if (StringUtils.isNotBlank(rangeKeyName)) {
                keySchema.add(new KeySchemaElement()
                    .withAttributeName(rangeKeyName)
                    .withKeyType(KeyType.RANGE));
                attributeDefinitions.add(new AttributeDefinition()
                    .withAttributeName(rangeKeyName)
                    .withAttributeType(rangeKeyType));
            }

            CreateTableRequest request = new CreateTableRequest()
                    .withTableName(tableName)
                    .withKeySchema(keySchema)
                    .withProvisionedThroughput( new ProvisionedThroughput()
                        .withReadCapacityUnits(readCapacityUnits)
                        .withWriteCapacityUnits(writeCapacityUnits));

            request.setAttributeDefinitions(attributeDefinitions);

            System.out.println("Issuing CreateTable request for " + tableName);
            Table table = dynamoDB.createTable(request);
            System.out.println("Waiting for " + tableName
                + " to be created...this may take a while...");
            table.waitForActive();

        } catch (Exception e) {
            System.err.println("CreateTable request failed for " + tableName);
            System.err.println(e.getMessage());
        }
    }

    public void deleteTable(String tableName){
        Table table = dynamoDB.getTable(tableName);
        try {
            System.out.println("Issuing DeleteTable request for " + tableName);
            table.delete();
            System.out.println("Waiting for " + tableName + " to be deleted...this may take a while...");
            table.waitForDelete();
            System.out.println("Delete table " + tableName + " successed");
        } catch (Exception e) {
            System.err.println("DeleteTable request failed for " + tableName);
            System.err.println(e.getMessage());
        }
    }

    public void deleteTableItems(String tableName){
        Table table = dynamoDB.getTable(tableName);
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
            .withPrimaryKey(new PrimaryKey("id", "1"))
            .withConditionExpression("id >= :val")
            .withValueMap(new ValueMap()
            .withNumber(":val", 5.0));
        try {
            System.out.println("Attempting a conditional delete...");
            table.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete item: id > 1" );
            System.err.println(e.getMessage());
        }

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getPartitionKeyType() {
        return partitionKeyType;
    }

    public void setPartitionKeyType(String partitionKeyType) {
        this.partitionKeyType = partitionKeyType;
    }

    public String getRangKey() {
        return rangKey;
    }

    public void setRangKey(String rangKey) {
        this.rangKey = rangKey;
    }

    public String getRangKeyType() {
        return rangKeyType;
    }

    public void setRangKeyType(String rangKeyType) {
        this.rangKeyType = rangKeyType;
    }

    public long getReadCapacityUnits() {
        return readCapacityUnits;
    }

    public void setReadCapacityUnits(long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
    }

    public long getWriteCapacityUnits() {
        return writeCapacityUnits;
    }

    public void setWriteCapacityUnits(long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
    }
}
