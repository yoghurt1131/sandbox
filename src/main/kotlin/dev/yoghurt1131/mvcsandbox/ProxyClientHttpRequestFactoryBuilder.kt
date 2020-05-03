package dev.yoghurt1131.mvcsandbox

import org.apache.http.HttpHost
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory

class ProxyClientHttpRequestFactoryBuilder(private val httpHost: HttpHost): ClientHttpRequestFactoryBuilder {
    override fun build(): ClientHttpRequestFactory {
        var httpClientBuilder = HeaderCustomizeHttpClientBuilder.create()
        httpClientBuilder.setProxy(httpHost)
        return HttpComponentsClientHttpRequestFactory(httpClientBuilder.build())
    }
}