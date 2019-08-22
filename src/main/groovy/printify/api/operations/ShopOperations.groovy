package printify.api.operations

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class ShopOperations extends ApiOperations {

    static ArrayList<Map> getShops() {
        api().target('v1/shops.json').get() as ArrayList<Map>
    }
}
