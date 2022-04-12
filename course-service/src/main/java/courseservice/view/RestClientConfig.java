package courseservice.view;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.http.HttpHeaders;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        httpHeaders.add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=7");

        ClientConfiguration clientConfiguration = ClientConfiguration.builder() //
                .connectedTo("localhost:9200") //
                .withDefaultHeaders(httpHeaders)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
