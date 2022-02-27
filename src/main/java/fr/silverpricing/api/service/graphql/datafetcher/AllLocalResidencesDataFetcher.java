package fr.silverpricing.api.service.graphql.datafetcher;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.rest.ExternalApiController;
import fr.silverpricing.api.rest.ResidenceController;
import fr.silverpricing.api.service.ResidenceServiceImpl;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AllLocalResidencesDataFetcher implements DataFetcher<List<Residence>>{

    @Autowired
    ResidenceRepository residenceRepository;

    @Override
    public List<Residence> get(DataFetchingEnvironment dataFetchingEnvironment) {
        try {
            return residenceRepository.findAll();
        }catch (Exception e){
            log.error("AllLocalResidencesDataFetcher",e.getMessage());
        }
        return null;
    }
}