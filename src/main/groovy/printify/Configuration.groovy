package printify

import printify.utils.DataUtils

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class Configuration {

    public static final String API_BASE_URL = "https://api.printify.com"
    public static final String API_AUTH_TOKEN = DataUtils.getAuthToken("/printify/api-auth-token")
}
