package printify.api.operations

import printify.api.Client

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class ApiOperations {

    static Client api() {
        new Client()
    }
}
