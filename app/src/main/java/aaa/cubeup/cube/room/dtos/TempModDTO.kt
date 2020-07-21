@file:Suppress("ConvertSecondaryConstructorToPrimary")

package aaa.cubeup.cube.room.dtos

import aaa.cubeup.cube.data.order.LineItemMod
import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.DTOable
import aaa.cubeup.cube.utils.MoneyHelp
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.Money

@JsonPropertyOrder("name", "initially_applied", "applied_uid", "catalog_object_id", "base_price_money", "total_price_money", "line_item_uid")
@DatabaseView(
	value = """
		WITH ids AS (
			SELECT
				line_item_uid,
				modifier_list_id
			FROM temp_ids JOIN entity_item_modlist_info info
			USING (item_id)
			WHERE IFNULL(info.enabled, 1) IS NOT 0
		)
		SELECT
			modlist_liuid,
			line_item_uid,
			initially_applied,
			applied_uid,
			catalog_object_id,
			name,
			base_price_amount, base_price_currency,
			total_price_amount, total_price_currency
		FROM
		(
			SELECT
				(SELECT ordinal FROM entity_mod WHERE catalog_object_id = entity_mod.catalog_object_id) ordinal,
				modifier_list_id||'_'||line_item_uid modlist_liuid,
				line_item_uid,
				1 AS initially_applied,
				applied_uid,
				catalog_object_id,
				name,
				base_price_amount, base_price_currency,
				total_price_amount, total_price_currency
			FROM ids JOIN order_line_item_mod
			USING(line_item_uid)
			UNION
				SELECT
					ordinal,
					modifier_list_id||'_'||line_item_uid modlist_liuid,
					line_item_uid,
					0 AS initially_applied,
					hex(randomblob(2)) applied_uid,
					catalog_object_id,
					name,
					amount base_price_amount, currency base_price_currency,
					0 total_price_amount, currency total_price_currency
				FROM ids JOIN entity_mod mod
				USING(modifier_list_id)
				WHERE (SELECT line_item_uid, catalog_object_id) NOT IN (SELECT line_item_uid, catalog_object_id FROM order_line_item_mod)
		)
		ORDER BY ordinal, name""",
	viewName = "temp_dto_mod_order")
class TempModDTO : LineItemMod, DTOable {


	@JvmField @ColumnInfo(name = "modlist_liuid") @JsonProperty("modlist_liuid")
	var modlistLiUid: String = ""

	@JvmField @ColumnInfo(name = "initially_applied") @JsonProperty("initially_applied")
	var initiallyApplied: Boolean




	@JsonCreator
	constructor(
		@JsonProperty("applied_uid") appliedUid: String,
		@JsonProperty("catalog_object_id")catalogObjectId: String,
		@JsonProperty("name") name: String,
		@JsonProperty("base_price_money") basePriceMoney: Money?,
		@JsonProperty("total_price_money") totalPriceMoney: Money?,
		@JsonProperty("line_item_uid") lineItemUid: String,
		@JsonProperty("initially_applied") initiallyApplied: Boolean
	) : super(appliedUid, catalogObjectId, name, basePriceMoney, totalPriceMoney, lineItemUid) {
		this.initiallyApplied = initiallyApplied
	}


	@JsonProperty("applied_uid")
	override fun findAppliedUid(): String {
		return appliedUid
	}

	override fun findId(): String {
		return catalogObjectId
	}

	override fun cattype(): Cattype {
		return Cattype.MOD
	}

	override fun rightLabel(): String? {
		return MoneyHelp.toDollarsString(basePriceMoney)
	}

	override fun findInitiallyApplied(): Boolean {
		return initiallyApplied
	}
}