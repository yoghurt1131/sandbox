package dev.yoghurt1131.mvcsandbox

import org.apache.http.HttpHost
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URI

@RestController
class SampleClient() {

    @GetMapping("/")
    fun call() {
        var restTemplate = RestTemplateBuilder().build()
        val proxyHost = "proxy"
        val proxyPort = 8888
        val address = InetSocketAddress(proxyHost, proxyPort)

        // プロキシ設定
        var requestFactory = SimpleClientHttpRequestFactory()
        val proxy = Proxy(Proxy.Type.HTTP, address)
        requestFactory.setProxy(proxy)
        restTemplate.requestFactory = requestFactory

        // 認証ヘッダを詰めてリクエスト
        var headers = HttpHeaders()
        headers.add("X-AUTH-HEADER", "authValue")
        val requestEntity = RequestEntity<Request>(Request(), headers, HttpMethod.GET, URI("https://server"))
        restTemplate.exchange(requestEntity, Response::class.java)
    }

    @GetMapping("/customized")
    fun callCustomized() {
        val proxyHost = "proxy"
        val proxyPort = 8888
        val requestFactoryBuilder = ProxyClientHttpRequestFactoryBuilder(HttpHost(proxyHost, proxyPort))
        var restTemplate = RestTemplateBuilder()
                .requestFactory{ requestFactoryBuilder.build() }
                .build()

        // 認証ヘッダを詰めてリクエスト
        var headers = HttpHeaders()
        headers.add("X-AUTH-HEADER", "authValue")
        val requestEntity = RequestEntity<Request>(Request(), headers, HttpMethod.GET, URI("https://server"))
        restTemplate.exchange(requestEntity, Response::class.java)
    }
}

class Request
class Response