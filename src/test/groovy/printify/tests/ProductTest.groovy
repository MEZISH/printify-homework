package printify.tests

import com.google.gson.Gson
import org.apache.commons.lang3.RandomStringUtils
import org.junit.After
import org.junit.Assert
import org.junit.Test
import printify.api.operations.CatalogOperations
import printify.api.operations.MediaOperations
import printify.api.operations.ProductOperations
import printify.api.operations.ShopOperations

import static printify.api.context.ValueStore.rememberProductId
import static printify.api.context.ValueStore.rememberShop

class ProductTest extends BaseTest {

    @Test
    void createProduct() {
        def blueprint = CatalogOperations.getBlueprintFor "Dog Tag"
        def printProvider = CatalogOperations.getProvidersForBlueprint(blueprint.id as String).first()
        def printVariant = CatalogOperations.getVariantsForBlueprint(printProvider.id as String, blueprint.id as String).first()
        def printImage = MediaOperations.uploadImage 'test_print_home.png'
        def myShop = ShopOperations.getShops().first()
        rememberShop(myShop)

        def productName = "Product_" + RandomStringUtils.randomAlphanumeric(5)
        Map productProperties = [
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

        println "\n\n${new Gson().toJson(productProperties)}\n"

        def product = ProductOperations.createProduct(productProperties)
        rememberProductId(product.id as String)

        Assert.assertNotNull(ProductOperations.getProduct())
    }

    @Test
    void updateProduct() {

    }

    @Test
    void deleteProduct() {

    }

    @After
    void cleanUp() {
        ProductOperations.deleteProduct()
    }
}
