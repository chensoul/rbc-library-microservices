package com.productdock.library.gateway.config;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import wiremock.org.apache.hc.client5.http.classic.methods.HttpGet;
import wiremock.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import wiremock.org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import wiremock.org.apache.hc.core5.http.HttpResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWireMock(port = 8081)
@ActiveProfiles("test")
class SearchRouteTest {

    @Test
    @WithMockUser
    void givenAuthenticated_thenRequestSentToSearchService() throws Exception {
        stubFor(get(urlEqualTo("/api/search/books"))
                .willReturn(aResponse()
                        .withStatus(200)));

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/api/search/books");
        request.addHeader("Authorization", "Token");
        HttpResponse response = client.execute(request);

        verify(1, getRequestedFor(urlEqualTo("/api/search/books")).withHeader("Authorization", equalTo("Token")));
        assertThat(response.getCode()).isEqualTo(200);
    }

}
