package aws.lab8;
// Copyright 2015 Amazon Web Services, Inc. or its affiliates. All rights reserved.

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class Utils {

    public static final String LAB_S3_BUCKET_NAME = "us-east-1-aws-training";
    public static final String LAB_S3_BUCKET_REGION = "us-east-1";
    public static final String PHARMA_DATA_FILE_KEY = "awsu-ilt/developing/v2.0/lab-8-elasticache/static/SampleInputFiles/PharmaListings.csv";

    public static Region getRegion() {
        Region region = Regions.getCurrentRegion();

        // For local testing only.
        if (region == null) {
            region = Region.getRegion(Regions.US_EAST_1);
        }

        System.out.printf("Utils.getRegion returned %s. %n ", region.getName());
        return region;
    }
}
