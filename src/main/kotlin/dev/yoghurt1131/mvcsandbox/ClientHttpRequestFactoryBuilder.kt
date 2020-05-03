package dev.yoghurt1131.mvcsandbox

import org.springframework.http.client.ClientHttpRequestFactory

interface ClientHttpRequestFactoryBuilder {

    fun build(): ClientHttpRequestFactory
}