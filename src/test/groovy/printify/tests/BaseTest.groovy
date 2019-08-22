package printify.tests

import org.apache.commons.lang3.RandomStringUtils

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class BaseTest {

    protected static LinkedHashMap<String, Object> compileProductProperties(String productName, Map printVariant, Map blueprint, Map printProvider, Map printImage) {
        [
                'title'            : productName,
                'description'      : RandomStringUtils.randomAlphanumeric(200),
                'variants'         : [[
                                              'id'   : printVariant.id,
                                              'price': 8
                                      ]],
                'blueprint_id'     : blueprint.id,
                'print_provider_id': printProvider.id,
                'print_areas'      : [[
                                              'variant_ids' : [printVariant.id],
                                              'placeholders': [[
                                                                       'position': (printVariant.placeholders as List<Map>).first().position,
                                                                       'images'  : [imageProperties(printImage.id as String)]
                                                               ]]
                                      ]],
        ]
    }

    static Map imageProperties(String imageId) {
        Map imageProperties = [
                'id'   : imageId,
                'x'    : 0.5,
                'y'    : 0.5,
                'scale': 1,
                'angle': 90
        ]
        imageProperties
    }
}
