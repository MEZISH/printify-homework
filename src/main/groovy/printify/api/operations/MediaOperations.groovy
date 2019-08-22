package printify.api.operations

import static printify.utils.DataUtils.readImageFromResources

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class MediaOperations extends ApiOperations {

    static Map uploadImage(String resourceFileName) {
        def image = readImageFromResources(resourceFileName)
        Map requestBody = [
                'file_name': resourceFileName,
                'contents' : Base64.encoder.encodeToString(image)
        ]
        api().target('v1/uploads/images.json').post(requestBody) as Map
    }
}
