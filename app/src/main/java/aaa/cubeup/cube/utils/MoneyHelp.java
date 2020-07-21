package aaa.cubeup.cube.utils;

import com.squareup.square.models.Money;

import org.jetbrains.annotations.NotNull;

public class MoneyHelp {

	public static String toDollarsString(Money money) {
		if (money == null) return null;
		return toDollarsString(money.getAmount());
	}
	@NotNull
	public static String toDollarsString(long pennies) {
		long dollars = pennies / 100;
		String cents = String.valueOf(pennies % 100);
		cents += cents.length() < 2 ? "0" : "";
		return "$" + dollars + "." + cents;
	}

	public static Money times(Money money, double n) {
		if (money == null) return null;
		return new Money((long) (money.getAmount() * n), money.getCurrency());
	}

	public static String serialize(Money money) {
		if (money == null) return null;
		return money.getAmount() + ":" + money.getCurrency();
	}

	public static Money deserialize(String string) {
		if (string == null) return null;
		return new Money(Long.parseLong(string.split(":")[0]), string.split(":")[1]);
	}
}
