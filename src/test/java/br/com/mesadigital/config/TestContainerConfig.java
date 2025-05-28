package br.com.mesadigital.config;

import br.com.mesadigital.operacoes.OperacoesApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(classes = OperacoesApplication.class)
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("qa")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestContainerConfig {

    @Autowired
    protected MockMvc mockMvc;

    protected static ObjectMapper objectMapper = new ObjectMapper();

    private static final Network NETWORK = Network.newNetwork();

    @Container
    public static GenericContainer<?> operacoesMongoDB = new GenericContainer<>("hiranneri/operacoes-db")
            .withExposedPorts(27017)
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "hiran")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "secret")
            .withEnv("MONGO_INITDB_DATABASE", "operacoes");

    @Container
    public static final org.testcontainers.containers.KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.0")
                    .asCompatibleSubstituteFor("confluentinc/cp-kafka"));

    private static int getPortaMongoDB() {
        return operacoesMongoDB.getMappedPort(27017);
    }

    @Container
    public static GenericContainer<?> zookeeperContainer = new GenericContainer<>("confluentinc/cp-zookeeper:7.3.0")
            .withNetwork(NETWORK)
            .withNetworkAliases("zookeeper");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        int portaMongoDB = getPortaMongoDB();
        registry.add("spring.data.mongodb.uri" , (() -> "mongodb://hiran:secret@localhost:" + portaMongoDB + "/operacoes"));
        registry.add("spring.data.mongodb.authentication-database" , (() -> "test"));

        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);

        // Producer configs
        registry.add("spring.kafka.producer.key-serializer",
                () -> "org.apache.kafka.common.serialization.StringSerializer");

        registry.add("spring.kafka.producer.value-serializer",
                () -> "org.springframework.kafka.support.serializer.JsonSerializer");

        registry.add("spring.kafka.producer.properties.spring.json.add.type.headers", () -> "true");
        registry.add("spring.kafka.producer.properties.request.timeout.ms", () -> "15000");
        registry.add("spring.kafka.producer.properties.retry.backoff.ms", () -> "1000");

        // Consumer configs (caso tenha consumidor no teste)
        registry.add("spring.kafka.consumer.key-deserializer",
                () -> "org.apache.kafka.common.serialization.StringDeserializer");

        registry.add("spring.kafka.consumer.value-deserializer",
                () -> "org.springframework.kafka.support.serializer.JsonDeserializer");

        registry.add("spring.kafka.consumer.properties.spring.json.trusted.packages",
                () -> "br.com.mesadigital.operacoes.controller.dto.kafka");

        registry.add("spring.kafka.consumer.properties.spring.json.value.default.type",
                () -> "br.com.mesadigital.operacoes.controller.dto.kafka.PedidoOperacoesDTO");

        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");

        registry.add("spring.kafka.consumer.group-id", () -> "test-group");

    }

    @BeforeAll
    static void baseSetup() {
        // setup comum para todos os testes (limpar banco, etc)
    }
}
