package dev.yoghurt1131.mvcsandbox

import org.apache.http.ConnectionReuseStrategy
import org.apache.http.HttpRequest
import org.apache.http.HttpRequestInterceptor
import org.apache.http.client.AuthenticationStrategy
import org.apache.http.client.UserTokenHandler
import org.apache.http.conn.ConnectionKeepAliveStrategy
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.execchain.ClientExecChain
import org.apache.http.protocol.HttpProcessor
import org.apache.http.protocol.HttpRequestExecutor
import org.apache.http.protocol.ImmutableHttpProcessor
import org.apache.http.protocol.RequestTargetHost

class HeaderCustomizeHttpClientBuilder : HttpClientBuilder() {

    companion object {
        fun create(): HttpClientBuilder {
            return HeaderCustomizeHttpClientBuilder()
        }
    }

    override fun createMainExec(requestExec: HttpRequestExecutor?, connManager: HttpClientConnectionManager?,
                                reuseStrategy: ConnectionReuseStrategy?, keepAliveStrategy: ConnectionKeepAliveStrategy?,
                                proxyHttpProcessor: HttpProcessor?, targetAuthStrategy: AuthenticationStrategy?,
                                proxyAuthStrategy: AuthenticationStrategy?, userTokenHandler: UserTokenHandler?): ClientExecChain {

        val headerCustomizeHttpProcessor = ImmutableHttpProcessor(
                RequestTargetHost(),
                HttpRequestInterceptor { request: HttpRequest, _ -> request.addHeader("X-AUTH", "authValue") }
        )
        return super.createMainExec(requestExec, connManager, reuseStrategy, keepAliveStrategy,
                /* customized processor*/ headerCustomizeHttpProcessor, targetAuthStrategy,
                proxyAuthStrategy, userTokenHandler)
    }
}
