package com.example.orderjms;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class CustomOrderRepositoryImpl implements CustomerOrderRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateOrderAmount(String from, String product, int amount) {
        Query query = new Query();
        query.addCriteria(Criteria.where("from").is(from));
        query.addCriteria(Criteria.where("product").is(product));
        Update update = new Update();
        update.set("amount", amount);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Order.class);
        if(result == null) {
            System.out.println("No document Updated!");
        } else {
            System.out.println(result.getMatchedCount()+ " Document'(s) updated...");
        }
    }
}
