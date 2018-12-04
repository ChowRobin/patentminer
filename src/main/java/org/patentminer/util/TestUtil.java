package org.patentminer.util;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestUtil {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);

        MongoDatabase database = client.getDatabase("pminer");
        System.out.println("Connect to database successfully!");

        MongoCollection collection = database.getCollection("user");
        System.out.println("collection choosed.");

        FindIterable iterable = collection.find();
        MongoCursor cursor = iterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
