package aaa.cubeup.cube.room.views;

import androidx.room.DatabaseView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemAppliedTax;

import org.jetbrains.annotations.NotNull;

import aaa.cubeup.cube.room.dtos.TempTaxDTO;
import aaa.cubeup.cube.room.entities.order.AppliedTaxEntity;

@JsonPropertyOrder({"applied_uid", "tax_uid", "line_item_uid", "applied_money"})
@DatabaseView(value = "SELECT " + "[APPLIEDTAX].applied_uid, " + "[APPLIEDTAX].tax_uid, " + "[APPLIEDTAX].applied_amount, " + "[APPLIEDTAX].applied_currency, " + "[APPLIEDTAX].line_item_uid, " + "[LITAX].reference_id " + "FROM order_applied_tax [APPLIEDTAX] INNER JOIN order_line_item_tax [LITAX] " + "USING(tax_uid)", viewName = "cube_applied_tax")
public class AppliedTax extends AppliedTaxEntity {


	public AppliedTax(TempTaxDTO tempTaxDTO) {
		super(tempTaxDTO.getAppliedUid(), tempTaxDTO.getTaxUid(), tempTaxDTO.getAppliedMoney(), tempTaxDTO.getLineItemUid());
	}

	public AppliedTax(OrderLineItemAppliedTax appliedTax, String liUid) {
		super(appliedTax, liUid);
	}

	@JsonCreator
	public AppliedTax(@NotNull @JsonProperty("applied_uid") String appliedUid, @JsonProperty("tax_uid") String taxUid, @JsonProperty("applied_money") Money appliedMoney, @NotNull @JsonProperty("line_item_uid") String lineItemUid) {
		super(appliedUid, taxUid, appliedMoney, lineItemUid);
	}

}
