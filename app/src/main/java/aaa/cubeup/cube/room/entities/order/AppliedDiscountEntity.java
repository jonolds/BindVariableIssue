package aaa.cubeup.cube.room.entities.order;

import androidx.annotation.NonNull;
import androidx.room.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemAppliedDiscount;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import aaa.cubeup.cube.room.dtos.TempDiscountDTO;

import static androidx.room.ForeignKey.CASCADE;


@JsonPropertyOrder({"applied_uid", "discount_uid", "line_item_uid", "applied_money"})
@Entity(
	tableName = "order_applied_discount",
	indices = {@Index("discount_uid"), @Index("line_item_uid")},
	foreignKeys = {
		@ForeignKey(
			entity = LineItemDiscountEntity.class,
			parentColumns = "discount_uid",
			childColumns = "discount_uid",
			onDelete = CASCADE,
			deferred = true
		),
		@ForeignKey(
			entity = LineItemEntity.class,
			parentColumns = "line_item_uid",
			childColumns = "line_item_uid",
			onDelete = CASCADE
		)
	})
public class AppliedDiscountEntity {

	@PrimaryKey @NonNull @ColumnInfo(name = "applied_uid") @JsonProperty("applied_uid")
	public String appliedUid;

	@ColumnInfo(name = "discount_uid") @JsonProperty("discount_uid")
	public String discountUid;

	@Embedded(prefix = "applied_") @JsonProperty("applied_money")
	public Money appliedMoney;

	@NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid;


	public AppliedDiscountEntity(@NotNull String appliedUid, String discountUid, Money appliedMoney, @NotNull String lineItemUid) {
		this.appliedUid = appliedUid;
		this.discountUid = discountUid;
		this.appliedMoney = appliedMoney;
		this.lineItemUid = lineItemUid;
	}

	public AppliedDiscountEntity(@NotNull OrderLineItemAppliedDiscount appliedDiscount, @NotNull String lineItemUid) {
		this.appliedUid = appliedDiscount.getUid();
		this.discountUid = appliedDiscount.getDiscountUid();
		this.lineItemUid = lineItemUid;
		this.appliedMoney = Optional.ofNullable(appliedDiscount.getAppliedMoney()).orElse(new Money(0L, "USD"));
	}

	public AppliedDiscountEntity(@NotNull TempDiscountDTO tempDiscountDTO) {
		this.appliedUid = tempDiscountDTO.appliedUid;
		this.discountUid = tempDiscountDTO.getDiscountUid();
		this.lineItemUid = tempDiscountDTO.lineItemUid;
		this.appliedMoney = tempDiscountDTO.appliedMoney;
	}

}