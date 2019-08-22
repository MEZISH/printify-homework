package printify.api.context

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class ValueStore {

    public static final String STORE_KEY = 'store'
    public static final String PRODUCT_ID_KEY = 'product.id'

    public static final ThreadLocal<HashMap> VALUE_STORE = new ThreadLocal<HashMap>() {
        @Override
        protected HashMap initialValue() {
            return new HashMap()
        }
    }

    static void store(String key, Object value) {
        VALUE_STORE.get().remove(key)
        VALUE_STORE.get().put(key, value)
    }

    static Object get(String key) {
        VALUE_STORE.get().get(key)
    }

    static rememberShop(Map shop) {
        store(STORE_KEY, shop)
    }

    static Map getShop() {
        get(STORE_KEY) as Map
    }

    static rememberProductId(String productId) {
        store(PRODUCT_ID_KEY, productId)
    }

    static String getProductId() {
        get(PRODUCT_ID_KEY)
    }
}
