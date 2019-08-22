package printify.utils

import groovy.json.JsonSlurper
import org.apache.http.HttpResponse

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class DataUtils {

    static Object resolve(HttpResponse response) {
        def bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
        def jsonResponse = bufferedReader.getText()
        new JsonSlurper().parseText(jsonResponse)
    }

    static String getAuthToken(String location) {
        def authToken = this.class.getResource(location)
        authToken.text
    }

    static byte[] readImageFromResources(String resourceFilename) {
        def imageResource = this.class.getResource("/printify/images/$resourceFilename")
        imageResource.bytes
    }
}
