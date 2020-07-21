package aaa.cubeup.cube.room.views

import aaa.cubeup.cube.data.nonentities.Cattype
import aaa.cubeup.cube.data.nonentities.BaseBVable
import aaa.cubeup.cube.utils.MoneyHelp
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.squareup.square.models.Money

@JsonPropertyOrder("catalog_object_id", "name", "type", "percentage", "amount_money")
@DatabaseView(value = "SELECT catalog_object_id, name, discount_type, percentage, amount, currency FROM entity_discount ORDER BY name ASC",
	viewName = "data_order_discount")
data class DiscountOrderData (
	@JvmField @ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id") var catalogObjectId: String
) : BaseBVable {

	@JvmField
	var name: String = ""

	@JvmField @ColumnInfo(name = "discount_type") @JsonProperty("type")
	var discountType: String = ""

	@JvmField
	var percentage: String = ""

	@JvmField @Embedded @JsonProperty("amount_money")
	var amountMoney: Money? = null

	constructor() : this("")

	constructor(@JsonProperty("catalog_object_id") catalogObjectId: String,
				@JsonProperty("name") name: String,
				@JsonProperty("type") discountType: String,
				@JsonProperty("percentage") percentage: String,
				@JsonProperty("amount_money") amountMoney: Money?) : this(catalogObjectId){
		this.name = name
		this.discountType = discountType
		this.percentage = percentage
		this.amountMoney = amountMoney
	}

	override fun rightLabel(): String? {
		return if (discountType.contains("PERCENTAGE")) "$percentage%" else MoneyHelp.toDollarsString(amountMoney!!.amount)
	}

	override fun findName(): String {
		return name
	}

	fun getName(): String {
		return name
	}

	override fun findId(): String {
		return catalogObjectId
	}

	override fun cattype(): Cattype {
		return Cattype.DISCOUNT
	}

}