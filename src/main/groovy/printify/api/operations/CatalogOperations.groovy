package printify.api.operations

/**
 * Created by Kristaps Mezavilks on 22.08.2019.
 */
class CatalogOperations extends ApiOperations {

    static ArrayList<Map> getBlueprints() {
        api().target('v1/catalog/blueprints.json').get() as ArrayList<Map>
    }

    static Map getBlueprintFor(String blueprintTitle) {
        Map targetBlueprint = new HashMap()
        ArrayList<Map> blueprints = getBlueprints()
        blueprints.forEach { Map blueprint ->
            if (blueprintTitle.equals(blueprint.title)) {
                targetBlueprint = blueprint
            }
        }
        targetBlueprint
    }

    static ArrayList<Map> getProvidersForBlueprint(String blueprintId) {
        api().target("v1/catalog/blueprints/$blueprintId/print_providers.json").get() as ArrayList<Map>
    }

    static ArrayList<Map> getVariantsForBlueprint(String providerId, String blueprintId) {
        api().target("v1/catalog/blueprints/$blueprintId/print_providers/$providerId/variants.json").get().variants as ArrayList<Map>
    }
}
