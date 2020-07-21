package aaa.cubeup.cube.data.nonentities;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.jetbrains.annotations.NotNull;

import java.util.TreeMap;

public enum Cattype {
	CATEGORY, DISCOUNT, IMAGE, ITEM, MOD, MODLIST, TAX, VARI;

	private static final TreeMap<String, Cattype> valueMap = new TreeMap<>();
	public int buttonRank;
	private String value;
	static {

		CATEGORY.buttonRank = 1;
		DISCOUNT.buttonRank = 2;
		IMAGE.buttonRank = -99;
		ITEM.buttonRank = 0;
		MOD.buttonRank = -99;
		MODLIST.buttonRank = -99;
		TAX.buttonRank = -99;
		VARI.buttonRank = -99;

		CATEGORY.value = "CATEGORY";
		DISCOUNT.value = "DISCOUNT";
		IMAGE.value = "IMAGE";
		ITEM.value = "ITEM";
		MOD.value = "MODIFIER";
		MODLIST.value = "MODIFIER_LIST";
		TAX.value = "TAX";
		VARI.value = "ITEM_VARIATION";

		valueMap.put("CATEGORY", CATEGORY);
		valueMap.put("DISCOUNT", DISCOUNT);
		valueMap.put("IMAGE", IMAGE);
		valueMap.put("ITEM", ITEM);
		valueMap.put("MODIFIER", MOD);
		valueMap.put("MODIFIER_LIST", MODLIST);
		valueMap.put("TAX", TAX);
		valueMap.put("ITEM_VARIATION", VARI);
	}
	@JsonCreator
	public static Cattype fromString(String toConvert) {
		return valueMap.get(toConvert);
	}

	@NotNull @Override
	public String toString() {
		return value;
	}

}
