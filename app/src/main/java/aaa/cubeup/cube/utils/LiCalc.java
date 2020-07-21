package aaa.cubeup.cube.utils;

import java.util.List;

import aaa.cubeup.cube.data.order.LineItemMod;

import static aaa.cubeup.cube.utils.MoneyHelp.times;

public class LiCalc {

	public static List<LineItemMod> setModValues(List<LineItemMod> mods, double quantity) {
		if (mods != null) mods.forEach(x -> LiCalc.setModValue(x, quantity));
		return mods;
	}

	public static void setModValue(LineItemMod lineItemModZZZZ, double quantity) {
		lineItemModZZZZ.totalPriceMoney = times(lineItemModZZZZ.basePriceMoney, quantity);
	}

}
