package interactions.backend.config;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MongoConfigTest {

    @Mock
    private MongoClient mockMongoClient;

    @Test
    void testMongoClient() {
        MongoConfig mongoConfig = new MongoConfig();
        ReflectionTestUtils.setField(mongoConfig, "connectionString", "mongodb://localhost:27017/test");

        MongoClient mongoClient = mongoConfig.mongoClient();
        assertThat(mongoClient).isNotNull();
    }

    @Test
    void testGetDatabaseName() {
        MongoConfig mongoConfig = new MongoConfig();
        ReflectionTestUtils.setField(mongoConfig, "databaseName", "test");

        String databaseName = mongoConfig.getDatabaseName();
        assertThat(databaseName).isEqualTo("test");
    }

    @Test
    void testMongoTemplate() {
        MongoConfig mongoConfig = new MongoConfig();
        ReflectionTestUtils.setField(mongoConfig, "databaseName", "test");
        ReflectionTestUtils.setField(mongoConfig, "connectionString", "mongodb://localhost:27017/test");

        MongoTemplate mongoTemplate = mongoConfig.mongoTemplate();
        assertThat(mongoTemplate).isNotNull();
    }
}