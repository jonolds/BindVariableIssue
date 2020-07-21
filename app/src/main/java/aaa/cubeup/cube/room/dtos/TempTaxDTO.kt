package aaa.cubeup.cube.room.dtos

import aaa.cubeup.cube.data.order.LineItemTax
import aaa.cubeup.cube.data.nonentities.DTOable
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.Money

@DatabaseView(
	value = """
		WITH litax AS (
			SELECT
				line_item_uid, reference_id, tax_uid, 1 initially_tax, name, catalog_object_id, percentage, inclusion_type,
				applied_order_amount, applied_order_currency, metadata, scope
			FROM temp_ids JOIN order_line_item_tax
			USING(reference_id)
			UNION
				SELECT
					line_item_uid, reference_id, hex(randomblob(3)) tax_uid, 0 initially_tax, name, catalog_object_id, percentage, inclusion_type,
					0 applied_order_amount, 'USD' applied_order_currency, '{}' metadata, 'LINE_ITEM' scope
				FROM temp_ids CROSS JOIN entity_tax
				WHERE (SELECT reference_id, catalog_object_id) NOT IN (SELECT reference_id, catalog_object_id FROM order_line_item_tax)

		),
		apptax AS (
			SELECT
				litax.line_item_uid, reference_id, tax_uid, initially_tax, name, catalog_object_id, percentage, inclusion_type,
				applied_order_amount, applied_order_currency, metadata, scope,
				applied_uid,
				1 initially_applied,
				applied_amount,
				applied_currency
			FROM litax JOIN order_applied_tax
			USING(line_item_uid, tax_uid)
			UNION
				SELECT
					line_item_uid, reference_id, tax_uid, initially_tax, litax.name, catalog_object_id, litax.percentage, litax.inclusion_type,
					applied_order_amount, applied_order_currency, litax.metadata, scope,
					hex(randomblob(2)) applied_uid,
					0 initially_applied,
					0 applied_amount,
					'USD' applied_currency
				FROM litax
				WHERE (SELECT line_item_uid, catalog_object_id) NOT IN (SELECT line_item_uid, catalog_object_id FROM order_applied_tax)

		)
		SELECT * FROM apptax
		ORDER BY reference_id, line_item_uid, name""",
	viewName = "temp_dto_tax"
)
@JsonPropertyOrder("tax_order_data", "tax_uid", "initially_tax", "initially_applied", "reference_id", "line_item_uid", "applied_uid", "applied_order_money",
	"applied_money", "scope", "metadata")
class TempTaxDTO : LineItemTax(), DTOable {


	@ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	var lineItemUid: String = ""

	@Embedded(prefix = "applied_") @JsonProperty("applied_money")
	var appliedMoney: Money? = null

	@ColumnInfo(name = "initially_tax") @JsonProperty("initially_tax")
	var initiallyTax = false

	@ColumnInfo(name = "initially_applied") @JsonProperty("initially_applied")
	var initiallyApplied = false

	@ColumnInfo(name = "applied_uid") @JsonProperty("applied_uid")
	var appliedUid: String = ""






	override fun findAppliedUid(): String {
		return appliedUid
	}
	override fun findInitiallyApplied(): Boolean {
		return initiallyApplied
	}
}