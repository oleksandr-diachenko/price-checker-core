package com.epam.pricecheckercore.service.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class ApiParser implements Parser<String> {

    @Override
    public Optional<String> getContent(String url) {
        HttpGet request = new HttpGet(url);

        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return Optional.of(EntityUtils.toString(entity, "UTF-8"));
            }

        } catch (IOException e) {
            log.error("Can't get json from {}", url);
        }
        return Optional.empty();
    }
}
