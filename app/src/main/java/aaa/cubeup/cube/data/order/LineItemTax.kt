package aaa.cubeup.cube.data.order

import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.SCOPE_LINE_ITEM
import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.ScopeDef
import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.BaseBVable
import aaa.cubeup.cube.room.entities.order.LineItemTaxEntity
import aaa.cubeup.cube.room.views.TaxOrderData
import androidx.collection.ArrayMap
import androidx.room.DatabaseView
import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.square.models.Money
import java.util.*

@DatabaseView(value = "SELECT * FROM order_line_item_tax ORDER BY name", viewName = "view_line_item_tax")
open class LineItemTax : LineItemTaxEntity, BaseBVable {

	constructor(
		@JsonProperty("tax_uid") taxUid: String = "",
		@JsonProperty("tax_order_data") taxOrderData: TaxOrderData = TaxOrderData(),
		@JsonProperty("metadata") metadata: MutableMap<String?, String?>? = ArrayMap(),
		@JsonProperty("applied_money") appliedOrderMoney: Money? = Money(0, "USD"),
		@ScopeDef @JsonProperty("scope") scope: String = SCOPE_LINE_ITEM,
		@JsonProperty("reference_id") referenceId: String = ""
	) : super(taxUid, taxOrderData, metadata, appliedOrderMoney, scope, referenceId)


	companion object;


	override fun rightLabel(): String? {
		return Optional.ofNullable(taxOrderData!!.percentage).map { p: String -> "$p%" }.orElse("")
	}

	override fun findName(): String {
		return taxOrderData!!.name
	}

	override fun findId(): String {
		return taxOrderData!!.catalogObjectId
	}

	override fun cattype(): Cattype {
		return Cattype.TAX
	}
}