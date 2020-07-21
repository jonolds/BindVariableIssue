package aaa.cubeup.cube.data.nonentities;

import androidx.room.Embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.square.models.Money;


public class OrderMoneyAmountsZZZZ {

	@Embedded(prefix = "total_")
	@JsonProperty("total_money")
	public Money totalMoney;
	@Embedded(prefix = "tax_")
	@JsonProperty("tax_money")
	public Money taxMoney;
	@Embedded(prefix = "discount_")
	@JsonProperty("discount_money")
	public Money discountMoney;
	@Embedded(prefix = "tip_")
	@JsonProperty("tip_money")
	public Money tipMoney;
	@Embedded(prefix = "service_charge_")
	@JsonProperty("service_charge_money")
	public Money serviceChargeMoney;

	public OrderMoneyAmountsZZZZ() {
	}


}
