package aaa.cubeup.cube.room.dtos

import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.DTOable
import aaa.cubeup.cube.utils.MoneyHelp
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.Money
import java.util.*

@DatabaseView(
	value = """
		WITH OLI AS (
			SELECT 
				variation_name, catalog_object_id, item_name, tmp.item_id, base_price_amount, base_price_currency, 
				(SELECT ordinal FROM bv_vari WHERE catalog_object_id IS bv_vari.catalog_object_id) AS ordinal, 
				line_item_uid, 1 AS initially_applied 
			FROM temp_ids tmp, order_line_item oli USING(line_item_uid)
		), 
		EXTRAS AS ( 
			SELECT variation_name, catalog_object_id, tmp.item_name, item_id, base_price_amount, base_price_currency, ordinal, line_item_uid, 0 AS initially_applied
			FROM temp_ids tmp, bv_vari bv
			USING(item_id)
			Where (SELECT line_item_uid, catalog_object_id) NOT IN (SELECT line_item_uid, catalog_object_id FROM order_line_item) 
		)
		SELECT variation_name, catalog_object_id, item_name, item_id, base_price_amount, base_price_currency, ordinal, line_item_uid, initially_applied 
		FROM OLI
		UNION SELECT 
			variation_name, catalog_object_id, item_name, item_id, base_price_amount, base_price_currency, ordinal, line_item_uid, initially_applied 
		FROM EXTRAS  
		ORDER BY ordinal, variation_name """,
	viewName = "temp_dto_vari")
@JsonPropertyOrder("variation_name", "initially_applied", "catalog_object_id", "item_name", "item_id", "base_price_money", "line_item_uid")
class TempVariDTO : DTOable {

	@JvmField @ColumnInfo(name = "variation_name") @JsonProperty("variation_name")
	var variationName: String = ""
	@JvmField @ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id")
	var catalogObjectId: String = ""
	@JvmField @ColumnInfo(name = "item_name") @JsonProperty("item_name")
	var itemName: String = ""
	@JvmField @ColumnInfo(name = "item_id") @JsonProperty("item_id")
	var itemId: String = ""
	@JvmField @Embedded(prefix = "base_price_") @JsonProperty("base_price_money")
	var basePriceMoney: Money? = null
	@JvmField @ColumnInfo(name = "initially_applied") @JsonProperty("initially_applied")
	var initiallyApplied: Boolean = false
	@JvmField @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	var lineItemUid: String = ""



	override fun findAppliedUid(): String {
		return ""
	}

	override fun findId(): String {
		return catalogObjectId
	}

	override fun rightLabel(): String {
		return Optional.ofNullable(basePriceMoney).map { m->m.amount }.filter { m -> m != null }.map { pennies: Long? -> MoneyHelp.toDollarsString(pennies!!) }.orElse("")
	}

	override fun findName(): String {
		return variationName
	}

	override fun cattype(): Cattype {
		return Cattype.VARI
	}

	override fun findInitiallyApplied(): Boolean {
		return initiallyApplied
	}

}
