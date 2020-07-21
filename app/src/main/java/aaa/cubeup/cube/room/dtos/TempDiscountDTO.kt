package aaa.cubeup.cube.room.dtos

import aaa.cubeup.cube.data.order.LineItemDiscount
import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.DTOable
import aaa.cubeup.cube.utils.MoneyHelp
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.Money


@DatabaseView(
	value = """
		WITH lidiscount AS (
			SELECT
				line_item_uid, reference_id, discount_uid, 1 initially_discount, name, catalog_object_id, percentage, discount_type, 
				amount, currency,
				applied_order_amount, applied_order_currency, 
				IFNULL(metadata, '{}') metadata, scope
			FROM temp_ids JOIN order_line_item_discount
			USING(reference_id)
			UNION
				SELECT
					line_item_uid, reference_id, hex(randomblob(3)) discount_uid, 0 initially_discount, name, catalog_object_id, percentage, discount_type, 
					amount, currency,
					0 applied_order_amount, 'USD' applied_order_currency, 
					'{}' metadata, 'LINE_ITEM' scope
				FROM temp_ids CROSS JOIN entity_discount
				WHERE (SELECT reference_id, catalog_object_id) NOT IN (SELECT reference_id, catalog_object_id FROM order_line_item_discount)

		),
		appdiscount AS (
			SELECT
				lidiscount.line_item_uid, reference_id, discount_uid, initially_discount, name, catalog_object_id, percentage, discount_type, 
				IFNULL(amount, 0) amount, IFNULL(currency, 'USD') currency,
				IFNULL(applied_order_amount, 0) applied_order_amount, IFNULL(applied_order_currency, 'USD') applied_order_currency, 
				IFNULL(metadata, '{}') metadata, scope,
				applied_uid,
				1 initially_applied,
				IFNULL(applied_amount, 0) applied_amount,
				IFNULL(applied_currency, 'USD') applied_currency
			FROM lidiscount JOIN order_applied_discount
			USING(line_item_uid, discount_uid)
			UNION
				SELECT
					line_item_uid, reference_id, discount_uid, initially_discount, lidiscount.name, catalog_object_id, lidiscount.percentage, lidiscount.discount_type, 
					lidiscount.amount, lidiscount.currency,
					IFNULL(applied_order_amount, 0), IFNULL(applied_order_currency, 'USD'), IFNULL(lidiscount.metadata, '{}'), scope,
					hex(randomblob(2)) applied_uid,
					0 initially_applied,
					0 applied_amount, 'USD' applied_currency
				FROM lidiscount
				WHERE (SELECT line_item_uid, catalog_object_id) NOT IN (SELECT line_item_uid, catalog_object_id FROM order_applied_discount)

		)
		SELECT * FROM appdiscount
		ORDER BY reference_id, line_item_uid, name""",
	viewName = "temp_dto_discount"
)
@JsonPropertyOrder("discount_uid", "reference_id", "initially_discount", "applied_order_money", "applied_uid", "line_item_uid", "initially_applied",
	"applied_money", "metadata", "scope", "name", "catalog_object_id", "discount_type", "amount_money", "percentage")
class TempDiscountDTO : LineItemDiscount(), DTOable {

	@ColumnInfo(name = "initially_discount") @JvmField var initiallyDiscount: Boolean = false
	@ColumnInfo(name = "applied_uid") @JvmField var appliedUid: String = ""
	@ColumnInfo(name = "initially_applied") @JvmField var initiallyApplied: Boolean = false
	@Embedded(prefix = "applied_") @JvmField var appliedMoney: Money = Money(0, "USD")
	@ColumnInfo(name = "line_item_uid") @JvmField var lineItemUid: String = ""


	override fun findName(): String {
		return discountOrderData.name
	}
	override fun findAppliedUid(): String? {
		return appliedUid
	}
	override fun findId(): String? {
		return discountOrderData.catalogObjectId
	}
	override fun rightLabel(): String? {
		if(discountOrderData.discountType.contains("PERCENTAGE")) return "${discountOrderData.percentage}%"
		return MoneyHelp.toDollarsString(discountOrderData.amountMoney!!.amount)
	}
	override fun cattype(): Cattype {
		return Cattype.DISCOUNT
	}
	override fun findInitiallyApplied(): Boolean {
		return initiallyApplied
	}
}