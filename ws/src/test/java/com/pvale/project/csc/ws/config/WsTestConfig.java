package com.pvale.project.csc.ws.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Configuration
@Import(WsCoreConfig.class)
public class WsTestConfig {

    @Value("${mock.authorization-server.port}")
    private int mockAuthorizationServerPort;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
    private URI introspectionUri;

    private final WebApplicationContext webApplicationContext;

    @Autowired
    public WsTestConfig(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Mocks the OAuth2 Authorization Server when WS introspects Bearer tokens
     * Always returns valid access token with scope service
     * @return
     */
    @Bean
    public ClientAndServer clientAndServer() {

        ClientAndServer clientAndServer = new ClientAndServer(this.mockAuthorizationServerPort);

        clientAndServer.when(request()
                        .withMethod(HttpMethod.POST.name())
                        .withPath(this.introspectionUri.getPath()),
                Times.unlimited())
                .respond(
                        response()
                                .withContentType(MediaType.APPLICATION_JSON)
                                .withBody(getSuccessIntrospectResponseBody()));

        return clientAndServer;
    }

    /**
     * Creates customizer for MockMvc
     * @return
     */
    @Bean
    public MockMvcBuilderCustomizer mockMvcBuilderCustomizer() {
        return builder -> builder.defaultRequest(requestBuilder());
    }

    /**
     * Sets the Authentication header with a random Bearer token for every request
     * @return
     */
    private RequestBuilder requestBuilder() {
        return MockMvcRequestBuilders
                .get("http://does-not-matter/will-be-overriden-anyway")
                .header(HttpHeaders.AUTHORIZATION, "Bearer IAmAnAccessToken");
    }

    private String getSuccessIntrospectResponseBody() {
        return new ObjectMapper()
                .createObjectNode()
                .put("active", true)
                .put("scope", "service")
                .toString();
    }
}