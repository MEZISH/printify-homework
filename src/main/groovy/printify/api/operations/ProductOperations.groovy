package printify.api.operations


import static printify.api.context.ValueStore.getProductId
import static printify.api.context.ValueStore.getShop

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class ProductOperations extends ApiOperations {

    static Map createProduct(Map data) {
        api().target("/v1/shops/${shop.id}/products.json").post(data) as Map
    }

    static Map updateProduct(Map data) {
        api().target("/v1/shops/${shop.id}/products/${productId}.json").put(data) as Map
    }

    static Map deleteProduct() {
        api().target("/v1/shops/${shop.id}/products/${productId}.json").delete() as Map
    }

    static ArrayList<Map> getProducts() {
        api().target("/v1/shops/${shop.id}/products.json").get().data as ArrayList<Map>
    }
}
