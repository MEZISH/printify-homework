package printify.tests

import org.apache.commons.lang3.RandomStringUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import printify.api.operations.CatalogOperations
import printify.api.operations.MediaOperations
import printify.api.operations.ProductOperations
import printify.api.operations.ShopOperations

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNull
import static printify.api.context.ValueStore.*

class ProductTest extends BaseTest {

    @Before
    void setUp() {
        def myShop = ShopOperations.shops.first()
        rememberShop(myShop)
    }

    @Test
    void createProduct() {
        def blueprint = CatalogOperations.getBlueprintFor "Dog Tag"
        def printProvider = CatalogOperations.getProvidersForBlueprint(blueprint.id as String).first()
        def printVariant = CatalogOperations.getVariantsForBlueprint(printProvider.id as String, blueprint.id as String).first()
        def printImage = MediaOperations.uploadImage 'test_print_home.png'


        def productName = "Product_" + RandomStringUtils.randomAlphanumeric(5)
        Map productProperties = compileProductProperties(productName, printVariant, blueprint, printProvider, printImage)

        def product = ProductOperations.createProduct(productProperties)
        assertNotNull("Product was not created successfully!\n" + product, product.id)
        rememberProductId(product.id as String)

        def expectedProduct = ProductOperations.products.find { prod ->
            productId.equals(prod.id) && productProperties.title.equals(prod.title) && productProperties.description.equals(prod.description)
        }
        assertNotNull(expectedProduct)
    }

    @Test
    void updateProduct() {

        createProduct()

        Map productUpdates = [
                'title'      : 'Updated title',
                'description': RandomStringUtils.randomAlphanumeric(200),
        ]
        def product = ProductOperations.updateProduct(productUpdates)
        assertNotNull("Product was not updated successfully!\n" + product, product.id)

        def expectedProduct = ProductOperations.products.find { prod ->
            productId.equals(prod.id) && productUpdates.title.equals(prod.title) && productUpdates.description.equals(prod.description)
        }
        assertNotNull(expectedProduct)
    }

    @Test
    void deleteProduct() {

        createProduct()

        def product = ProductOperations.deleteProduct()
        assert product.isEmpty(): "Product was not deleted successfully!\n" + product

        def expectedProduct = ProductOperations.products.find { prod -> productId.equals(prod.id) }
        assertNull(expectedProduct)
    }

    @After
    void cleanUp() {
        ProductOperations.deleteProduct()
    }
}
