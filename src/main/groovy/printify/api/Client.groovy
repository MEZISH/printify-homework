package printify.api

import groovy.json.JsonBuilder
import org.apache.http.client.methods.*
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import printify.Configuration
import printify.utils.DataUtils

import static printify.utils.DataUtils.resolve

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class Client {

    private String targetUrl

    Client target(String targetEndpoint) {
        targetUrl = "$Configuration.API_BASE_URL/$targetEndpoint"
        this
    }

    Object get() {
        def request = new HttpGet(targetUrl)
        request = addHeaders(request)

        def client = HttpClientBuilder.create().build()
        def response = client.execute(request)

        resolve(response)
    }

    Object put(Map content) {
        def request = new HttpPut(targetUrl)
        request = addHeaders(request)
        def jsonBody = new JsonBuilder(content).toString()
        request.setEntity(new StringEntity(jsonBody))

        def client = HttpClientBuilder.create().build()
        def response = client.execute(request)

        resolve(response)
    }

    Object post(Map content) {
        def request = new HttpPost(targetUrl)
        request = addHeaders(request)
        def jsonBody = new JsonBuilder(content).toString()
        request.setEntity(new StringEntity(jsonBody))

        def client = HttpClientBuilder.create().build()
        def response = client.execute(request)

        resolve(response)
    }

    Object delete() {
        def request = new HttpDelete(targetUrl)
        request = addHeaders(request)

        def client = HttpClientBuilder.create().build()
        def response = client.execute(request)

        resolve(response)
    }

    private static HttpUriRequest addHeaders(HttpUriRequest request) {
        request.addHeader("content-type", "application/json")
        request.addHeader("User-Agent", "Mezavilks test suite")
        request.addHeader("Authorization", "Bearer ${DataUtils.getAuthToken Configuration.API_AUTH_TOKEN_LOCATION}")
        request
    }
}
