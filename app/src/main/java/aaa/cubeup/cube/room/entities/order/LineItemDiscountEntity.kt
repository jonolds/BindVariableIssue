package aaa.cubeup.cube.room.entities.order

import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.SCOPE_LINE_ITEM
import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.ScopeDef
import aaa.cubeup.cube.room.views.DiscountOrderData
import androidx.collection.ArrayMap
import androidx.room.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.squareup.square.models.Money
import com.squareup.square.models.OrderLineItemDiscount

@JsonPropertyOrder("discount_uid", "reference_id", "discount_order_data", "metadata", "applied_money", "scope")
@Entity(
	tableName = "order_line_item_discount",
	indices = [Index("reference_id")],
	foreignKeys = [
		ForeignKey(
			entity = OrderObjectEntity::class,
			parentColumns = ["reference_id"],
			childColumns = ["reference_id"],
			onDelete = ForeignKey.CASCADE,
			deferred = true)
	])
open class LineItemDiscountEntity(
	@PrimaryKey @ColumnInfo(name = "discount_uid") @field:JsonProperty("discount_uid") open var discountUid: String = "",
	@JvmField @ColumnInfo(name = "reference_id") @field:JsonProperty("reference_id") var referenceId: String = "",
	@JvmField @JsonUnwrapped @Embedded @JsonProperty("discount_order_data") var discountOrderData: DiscountOrderData = DiscountOrderData(),
	@JvmField @Embedded(prefix = "applied_order_") @JsonProperty("applied_money") var appliedOrderMoney: Money = Money(0, "USD"),
	@JvmField var metadata: MutableMap<String?, String?> = ArrayMap(),
	@JvmField @ScopeDef var scope: String = SCOPE_LINE_ITEM

) {


	fun toSparse(): OrderLineItemDiscount {
		return OrderLineItemDiscount.Builder()
			.uid(discountUid)
			.catalogObjectId(discountOrderData.catalogObjectId)
			.metadata(fillMeta())
			.scope(scope)
			.build()
	}

	private fun fillMeta(): MutableMap<String?, String?>? {
		metadata = ArrayMap()
		metadata["referenceId"] = referenceId
		return metadata
	}
}