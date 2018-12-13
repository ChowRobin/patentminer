package org.patentminer.util;

import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.patentminer.model.Company;
import org.patentminer.model.Inventor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVUtil {

    public static void main(String[] args) {
//        importToDB(new File("/Users/robin/Downloads/G06N(2017-1998).csv"), "patents");
    }

    public static void importToDB(File file, String collectionName) {
        String[] keys = {"application_number", "application_date", "publication_number", "publication_date",
                        "companies", "inventors", "invention_titles_cn", "abstract_cn", "ipc"};
        CSVReader csvReader = null;
        try {
            MongoClient client = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = client.getDatabase("pminer");
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

            csvReader = new CSVReader(new FileReader(file));
            String[] tmp;
            Map<String, Object> map;
            int count = 0;
            csvReader.readNext();
            while ((tmp = csvReader.readNext()) != null){
                map = new HashMap<String, Object>();
                map.put(keys[0], tmp[0]);
                map.put(keys[1], tmp[1]);
                map.put(keys[2], tmp[2]);
                map.put(keys[3], tmp[3]);
                String[] tt;
                List<Map<String, Object>> companies = new ArrayList<>();
                tt = tmp[4].split(";");
                Map<String, Object> cMap = new HashMap<>();
                for (int j = 0; j < tt.length; ++j) {
                    cMap = new HashMap<>();
                    cMap.put("name_cn", tt[j]);
                    companies.add(cMap);
                }
                map.put(keys[4], companies);

                List<Map> inventors = new ArrayList<>();
                tt = tmp[5].split(";");
                for (int j = 0; j < tt.length; ++j) {
                    cMap = new HashMap<>();
                    cMap.put("name_cn", tt[j]);
                    inventors.add(cMap);
                }
                map.put(keys[5], inventors);

                List<String> titles = new ArrayList<>();
                tt = tmp[6].split(";");
                for (int j = 0; j < tt.length; ++j) {
                    titles.add(tt[j]);
                }
                map.put(keys[6], titles);

                map.put(keys[7], tmp[7]);

                List<String> ipcs = new ArrayList<>();
                tt = tmp[8].split(";");
                for (int j = 0; j < tt.length; ++j) {
                    ipcs.add(tt[j]);
                }
                map.put(keys[8], ipcs);


                // save to MongoDB
                collection.insertOne(new Document(map));
                System.out.println("insert " + ++count + "document\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
