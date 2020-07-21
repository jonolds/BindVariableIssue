package aaa.cubeup.cube.data.nonentities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;

import aaa.cubeup.cube.utils.MoneyHelp;

@JsonPropertyOrder({"name", "reference_id", "version", "total_money", "total_discount_money", "total_tax_money"})
public class OrderButton implements Parcelable {

	public static final Creator<OrderButton> CREATOR = new Creator<OrderButton>() {
		@Override
		public OrderButton createFromParcel(Parcel in) {
			return new OrderButton(in);
		}

		@Override
		public OrderButton[] newArray(int size) {
			return new OrderButton[size];
		}
	};

	public String name;
	@ColumnInfo(name = "reference_id") @JsonProperty("reference_id")
	public String referenceId;
	public Integer version;
	@Embedded(prefix = "total_") @JsonProperty("total_money")
	public Money totalMoney;
	@Embedded(prefix = "total_discount_") @JsonProperty("total_discount_money")
	public Money totalDiscountMoney;
	@Embedded(prefix = "total_tax_") @JsonProperty("total_tax_money")
	public Money totalTaxMoney;

	public OrderButton() {
	}


	protected OrderButton(Parcel in) {
		name = in.readString();
		referenceId = in.readString();
		version = in.readInt();
		totalMoney = MoneyHelp.deserialize(in.readString());
		totalDiscountMoney = MoneyHelp.deserialize(in.readString());
		totalTaxMoney = MoneyHelp.deserialize(in.readString());
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(referenceId);
		dest.writeInt(version);
		dest.writeString(MoneyHelp.serialize(totalMoney));
		dest.writeString(MoneyHelp.serialize(totalDiscountMoney));
		dest.writeString(MoneyHelp.serialize(totalTaxMoney));
	}
}
