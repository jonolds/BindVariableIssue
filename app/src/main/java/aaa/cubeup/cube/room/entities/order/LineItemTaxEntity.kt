package aaa.cubeup.cube.room.entities.order

import aaa.cubeup.cube.data.order.OrderZZZZ
import aaa.cubeup.cube.data.order.OrderZZZZ.Scope.ScopeDef
import aaa.cubeup.cube.room.views.TaxOrderData
import androidx.collection.ArrayMap
import androidx.room.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.squareup.square.models.Money
import com.squareup.square.models.OrderLineItemTax


@JsonPropertyOrder("tax_uid", "reference_id", "tax_order_data", "applied_money", "scope", "metadata")
@Entity(
	tableName = "order_line_item_tax",
	indices = [Index("reference_id"), Index("catalog_object_id")],
	foreignKeys = [
		ForeignKey(
			entity = OrderObjectEntity::class,
			parentColumns = ["reference_id"],
			childColumns = ["reference_id"],
			onDelete = ForeignKey.CASCADE,
			deferred = true)
	])
open class LineItemTaxEntity(
	@PrimaryKey @ColumnInfo(name = "tax_uid") @field:JsonProperty("tax_uid") @JsonProperty("tax_uid") var taxUid: String = "",
	@JvmField @ColumnInfo(name = "reference_id") @field:JsonProperty("reference_id") @JsonProperty("reference_id") var referenceId: String = ""
) {

	@JvmField @JsonUnwrapped @Embedded @JsonProperty("tax_order_data")
	var taxOrderData: TaxOrderData? = null

	@JvmField
	var metadata: MutableMap<String?, String?>? = null

	@JvmField @Embedded(prefix = "applied_order_") @JsonProperty("applied_money")
	var appliedOrderMoney: Money? = null

	@JvmField @ScopeDef
	var scope: String? = OrderZZZZ.Scope.SCOPE_LINE_ITEM


	constructor(
		@JsonProperty("tax_uid") taxUid: String,
		@JsonProperty("tax_order_data") taxOrderData: TaxOrderData?,
		@JsonProperty("metadata") metadata: MutableMap<String?, String?>?,
		@JsonProperty("applied_money") appliedOrderMoney: Money?,
		@ScopeDef @JsonProperty("scope") scope: String?,
		@JsonProperty("reference_id") referenceId: String
	) : this(taxUid, referenceId) {
		this.metadata = metadata ?: ArrayMap()
		this.taxOrderData = taxOrderData
		this.appliedOrderMoney = appliedOrderMoney
		this.scope = scope
	}

	fun toSparse(): OrderLineItemTax {
		return OrderLineItemTax.Builder()
			.uid(taxUid)
			.catalogObjectId(taxOrderData!!.catalogObjectId)
			.metadata(fillMeta())
			.scope(scope)
			.build()
	}

	private fun fillMeta(): MutableMap<String?, String?>? {
		(metadata ?: ArrayMap())["referenceId"] = referenceId
		return metadata
	}
}