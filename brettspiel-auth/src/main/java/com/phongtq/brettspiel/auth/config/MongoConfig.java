package com.phongtq.brettspiel.auth.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.phongtq.brettspiel.config.CustomCommandListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 11:33 AM
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.phongtq.brettspiel.auth")
public class MongoConfig extends AbstractReactiveMongoConfiguration  {

    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Value("${spring.data.mongodb.database}")
    private String database;


    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(url))
                .readPreference(ReadPreference.secondaryPreferred())
                .addCommandListener(new CustomCommandListener())
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    protected FieldNamingStrategy fieldNamingStrategy() {
        return new SnakeCaseFieldNamingStrategy();
    }

    @Override
    public MappingMongoConverter mappingMongoConverter(ReactiveMongoDatabaseFactory databaseFactory, MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
        MappingMongoConverter mappingMongoConverter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingMongoConverter;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory){
        ReactiveMongoTemplate reactiveMongoTemplate = new ReactiveMongoTemplate(reactiveMongoDatabaseFactory);
        reactiveMongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);
        reactiveMongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
        return reactiveMongoTemplate;
    }
}
