import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Collections;



class BatchStat {
    private static MongoClient client = null;
    private static MongoDatabase db = null;

    private static String ip = "localhost";
    private static int port = 27017;
    private static String name = "centralefitness";
    private static String idKey = "_id";
    public static Map<Collections, MongoCollection> collections = null;
    public String key;


    public static void main (String[] args){
        System.out.println("Hello World");
        BatchStat INSTANCE = new BatchStat();
    }
    private  BatchStat() {
        this.client = new MongoClient(BatchStat.ip, BatchStat.port);
        this.db = this.client.getDatabase(BatchStat.name);
        this.collections = new HashMap<>();
        for (Collections col : Collections.values()) {
            MongoCollection collection;
            try {
                collection = db.getCollection(key);
            } catch (Exception e) {
                db.createCollection(key);
                collection = db.getCollection(key);
            }
            this.collections.put(col, collection);
        }
    }
}