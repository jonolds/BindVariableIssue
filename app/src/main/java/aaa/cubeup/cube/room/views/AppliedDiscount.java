package aaa.cubeup.cube.room.views;

import androidx.room.DatabaseView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemAppliedDiscount;

import org.jetbrains.annotations.NotNull;

import aaa.cubeup.cube.room.dtos.TempDiscountDTO;
import aaa.cubeup.cube.room.entities.order.AppliedDiscountEntity;

@DatabaseView(
	value = "SELECT " +
		"APPLIED.applied_uid, " +
		"APPLIED.discount_uid, " +
		"APPLIED.applied_amount, " +
		"APPLIED.applied_currency, " +
		"APPLIED.line_item_uid, " +
		"LIDISC.amount, " +
		"LIDISC.currency, " +
		"LIDISC.discount_type " +
		"FROM order_applied_discount APPLIED INNER JOIN order_line_item_discount LIDISC " +
		"USING(discount_uid)",
	viewName = "cube_applied_discount"
)
public class AppliedDiscount extends AppliedDiscountEntity {

	@JsonCreator
	public AppliedDiscount(@NotNull @JsonProperty("applied_uid") String appliedUid,
						   @JsonProperty("discount_uid") String discountUid,
						   @JsonProperty("applied_money") Money appliedMoney,
						   @NotNull @JsonProperty("line_item_uid") String lineItemUid) {
		super(appliedUid, discountUid, appliedMoney, lineItemUid);
	}


	public AppliedDiscount(TempDiscountDTO tempDiscountDTO) {
		super(tempDiscountDTO);
	}

	public AppliedDiscount(OrderLineItemAppliedDiscount appliedDiscount, String liUid) {
		super(appliedDiscount, liUid);
	}

}
