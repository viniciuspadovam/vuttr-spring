package com.br.viniciuspadovam.vuttr.infra.mongo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		return "vuttr_challenge";
	}

	@Override
	public MongoClient mongoClient() {
		ConnectionString con = new ConnectionString("mongodb://localhost:27017/vuttr_challenge");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(con).build();
		
		return MongoClients.create(mongoClientSettings);
	}
	
	@Override
	public Collection<String> getMappingBasePackages() {
		return Collections.singleton("com.br.viniciuspadovam");
	}
	
}
