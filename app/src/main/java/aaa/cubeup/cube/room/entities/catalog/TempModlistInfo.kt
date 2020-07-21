package aaa.cubeup.cube.room.entities.catalog

import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.BaseBVable
import aaa.cubeup.cube.room.dtos.TempModDTO
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Relation
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.CatalogModifierOverride

@DatabaseView(
	value = """
		SELECT
			line_item_uid,
			modifier_list_id || '_' || line_item_uid modlist_liuid,
			name,
			selection_type,
			modifier_overrides,
			CASE WHEN IFNULL(min_selected_modifiers, -1) < 0 THEN 0 ELSE min_selected_modifiers END min_selected_modifiers,
			CASE WHEN IFNULL(max_selected_modifiers, -1) < 0 THEN 99 ELSE max_selected_modifiers END max_selected_modifiers
		FROM temp_ids JOIN entity_item_modlist_info USING(item_id)
		JOIN entity_modlist ON modifier_list_id = entity_modlist.catalog_object_id
		WHERE IFNULL(enabled, 1) IS NOT 0
		ORDER BY ordinal, name""",
	viewName = "temp_view_item_modlist_info"
)
@JsonPropertyOrder("name", "mods", "selection_type", "line_item_uid", "modlist_liuid", "modifier_overrides",
	"min_selected_modifiers", "max_selected_modifiers")
class TempModlistInfo : BaseBVable {

	@JvmField @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	var lineItemUid: String = ""

	@JvmField @ColumnInfo(name = "modlist_liuid") @JsonProperty("modlist_liuid")
	var modlistLiUid: String = ""

	@JvmField
	var name: String = ""

	@JvmField @ColumnInfo(name = "selection_type") @JsonProperty("selection_type")
	var selectionType: String = ""

	@JvmField @ColumnInfo(name = "modifier_overrides") @JsonProperty("modifier_overrides")
	var modifierOverrides: List<CatalogModifierOverride>? = null

	@JvmField @ColumnInfo(name = "min_selected_modifiers", defaultValue = "0") @JsonProperty("min_selected_modifiers")
	var minSelectedModifiers: Int = 0

	@JvmField @ColumnInfo(name = "max_selected_modifiers", defaultValue = "20") @JsonProperty("max_selected_modifiers")
	var maxSelectedModifiers: Int = 99


	@JvmField @Relation(parentColumn = "modlist_liuid", entityColumn = "modlist_liuid") @JsonProperty("mods")
	var mods: List<TempModDTO>? = null

	fun getName(): String {
		return name
	}

	override fun findId(): String {
		return modlistLiUid.substringBefore('_')
	}

	override fun cattype(): Cattype {
		return Cattype.MODLIST
	}

	override fun rightLabel(): String? {
		return null
	}

	override fun findName(): String {
		return name
	}
}