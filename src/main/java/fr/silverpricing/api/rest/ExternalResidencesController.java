package fr.silverpricing.api.rest;

import fr.silverpricing.api.model.Residence;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import static fr.silverpricing.api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/pour-les-personnes-agees")
@Slf4j
public class ExternalResidencesController {


    @GetMapping("/all")
    public List<Residence> getAll() {
        try {
            log.info("Fetching from external API - Loading...");
            String uri="https://www.pour-les-personnes-agees.gouv.fr/api/v1/establishment";
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            ResponseEntity<List<Residence>> residencesResponse =
                    restTemplate.exchange(uri,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Residence>>() {
                            });
            List<Residence> residences = residencesResponse.getBody();

            return residences.subList(0,1);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
