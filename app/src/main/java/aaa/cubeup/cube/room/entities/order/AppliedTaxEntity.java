package aaa.cubeup.cube.room.entities.order;

import androidx.annotation.NonNull;
import androidx.room.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemAppliedTax;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static androidx.room.ForeignKey.CASCADE;

@JsonPropertyOrder({"applied_uid", "tax_uid", "line_item_uid", "applied_money"})
@Entity(
	tableName = "order_applied_tax",
	indices = {
		@Index("tax_uid"),
		@Index("line_item_uid")
	},
	foreignKeys = {
		@ForeignKey(
			entity = LineItemTaxEntity.class,
			parentColumns = "tax_uid",
			childColumns = "tax_uid",
			onDelete = CASCADE,
			deferred = true
		),
		@ForeignKey(
			entity = LineItemEntity.class,
			parentColumns = "line_item_uid",
			childColumns = "line_item_uid",
			onDelete = CASCADE,
			deferred = true
		)
	})
public class AppliedTaxEntity {

	@PrimaryKey @NonNull @ColumnInfo(name = "applied_uid") @JsonProperty("applied_uid")
	public String appliedUid;

	@ColumnInfo(name = "tax_uid") @JsonProperty("tax_uid")
	public String taxUid;

	@NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid;

	@Embedded(prefix = "applied_") @JsonProperty("applied_money")
	public Money appliedMoney;

	public AppliedTaxEntity(@NotNull OrderLineItemAppliedTax orderLineItemAppliedTax, @NotNull String lineItemUid) {
		this.appliedUid = orderLineItemAppliedTax.getUid();
		this.taxUid = orderLineItemAppliedTax.getTaxUid();
		this.lineItemUid = lineItemUid;
		this.appliedMoney = Optional.ofNullable(orderLineItemAppliedTax.getAppliedMoney()).orElse(new Money(0L, "USD"));
	}

	@JsonCreator
	public AppliedTaxEntity(@NotNull @JsonProperty("applied_uid") String appliedUid, @JsonProperty("tax_uid") String taxUid, @JsonProperty("applied_money") Money appliedMoney, @NotNull @JsonProperty("line_item_uid") String lineItemUid) {
		this.appliedUid = appliedUid;
		this.taxUid = taxUid;
		this.lineItemUid = lineItemUid;
		this.appliedMoney = appliedMoney;
	}

}
