package printify.api.operations


import static org.junit.Assert.assertNotNull
import static printify.api.context.ValueStore.getProductId
import static printify.api.context.ValueStore.getShop

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class ProductOperations extends ApiOperations {

    static Map createProduct(Map data) {
        assertNotNull('No shop selected by \'rememberShop(Map)\'!', shop.id)
        api().target("/v1/shops/${shop.id}/products.json").post(data) as Map
    }

    static Map updateProduct(String id, Map data) {
        assertNotNull('No shop selected by \'rememberShop(Map)\'!', shop.id)
        assertNotNull('No product selected by \'rememberProductId(String)\'!', productId)
        api().target("/v1/shops/${shop.id}/products/${productId}.json").put(data) as Map
    }

    static Map deleteProduct() {
        api().target("/v1/shops/${shop.id}/products/${productId}.json").delete() as Map
    }

    static Map getProduct() {
        assertNotNull('No shop selected by \'rememberShop(Map)\'!', shop.id)
        assertNotNull('No product selected by \'rememberProductId(String)\'!', productId)
        api().target("/v1/shops/${shop.id}/products/${productId}.json").get() as Map
    }
}
