package printify.api.operations

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class CatalogOperations extends ApiOperations {

    static ArrayList<Map> getBlueprints() {
        api().target('v1/catalog/blueprints.json').get() as ArrayList<Map>
    }

    static Map getBlueprintFor(String blueprintTitle) {
        blueprints.find { bp -> blueprintTitle.equals(bp.title) }
    }

    static ArrayList<Map> getProvidersForBlueprint(String blueprintId) {
        api().target("v1/catalog/blueprints/$blueprintId/print_providers.json").get() as ArrayList<Map>
    }

    static ArrayList<Map> getVariantsForBlueprint(String providerId, String blueprintId) {
        api().target("v1/catalog/blueprints/$blueprintId/print_providers/$providerId/variants.json").get().variants as ArrayList<Map>
    }
}
