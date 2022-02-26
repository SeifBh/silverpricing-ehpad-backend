package fr.silverpricing.api.service.graphql.datafetcher;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.rest.ExternalResidencesController;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.List;

@Component
@Slf4j
public class AllResidencesDataFetcher  implements DataFetcher<List<Residence>>{

    //@Value("${api.pour-les-personnes-agees.base-url}")
    private String BASE_URL = "https://www.pour-les-personnes-agees.gouv.fr/api/v1";
    private String URI = BASE_URL + "/establishment";

    @Autowired
    ExternalResidencesController externalResidencesController;


    @Override
    public List<Residence> get(DataFetchingEnvironment dataFetchingEnvironment) {
        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            ResponseEntity<List<Residence>> rateResponse =
                    restTemplate.exchange(URI,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Residence>>() {
                            });
            List<Residence> residences = rateResponse.getBody();
            log.info("Fetched all residences");
            return residences;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}