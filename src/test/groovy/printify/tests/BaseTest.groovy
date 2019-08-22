package printify.tests

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class BaseTest {
    static Map imageProperties(String imageId) {
        Map imageProperties = [
                'id'   : imageId,
                'x'    : 0.5,
                'y'    : 0.5,
                'scale': 1,
                'angle': 0
        ]
        imageProperties
    }
}
