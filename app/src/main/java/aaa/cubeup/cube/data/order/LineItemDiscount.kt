package aaa.cubeup.cube.data.order

import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.SCOPE_LINE_ITEM
import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.ScopeDef
import aaa.cubeup.cube.room.entities.order.LineItemDiscountEntity
import aaa.cubeup.cube.room.views.DiscountOrderData
import android.util.ArrayMap
import androidx.room.DatabaseView
import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.square.models.Money


@Suppress("UNUSED_PARAMETER")
@DatabaseView(value = "SELECT * FROM order_line_item_discount ORDER BY name", viewName = "view_line_item_discount")
open class LineItemDiscount : LineItemDiscountEntity {



	constructor(@JsonProperty("discount_uid") discountUid: String = "",
				@JsonProperty("discount_order_data") discountOrderData: DiscountOrderData = DiscountOrderData(),
				@JsonProperty("applied_money") appliedOrderMoney: Money = Money(0, "USD"),
				@JsonProperty("metadata") metadata: MutableMap<String?, String?> = ArrayMap(),
				@ScopeDef @JsonProperty("scope") scope: String = SCOPE_LINE_ITEM,
				@JsonProperty("reference_id") referenceId: String = ""
	)
		: super(discountUid, referenceId, discountOrderData, appliedOrderMoney, metadata, scope)


}