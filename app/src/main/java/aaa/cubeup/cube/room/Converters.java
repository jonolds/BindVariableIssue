package aaa.cubeup.cube.room;

import androidx.collection.ArrayMap;
import androidx.room.TypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.square.models.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.utils.help.JsonHelp;

public class Converters {

	@TypeConverter
	public static String Cattype(Cattype cattype) {
		return cattype == null ? null : cattype.toString();
	}

	@TypeConverter
	public static Cattype Cattype(String string) {
		return string != null ? Cattype.fromString(string) : null;
	}

	@TypeConverter
	public static String OrderQuantityUnit(OrderQuantityUnit list) {
		return list != null ? JsonHelp.toJson(list) : null;
	}

	@TypeConverter
	public static OrderQuantityUnit OrderQuantityUnit(String string) {
		return string != null ? (OrderQuantityUnit) JsonHelp.toObject(string, OrderQuantityUnit.class) : null;
	}

	@TypeConverter
	public static String OrderRoundingAdjustment(OrderRoundingAdjustment list) {
		return list != null ? JsonHelp.toJson(list) : null;
	}

	@TypeConverter
	public static OrderRoundingAdjustment OrderRoundingAdjustment(String string) {
		return string != null ? (OrderRoundingAdjustment) JsonHelp.toObject(string, OrderQuantityUnit.class) : null;
	}

	@TypeConverter
	public static String map(Map<String, String> mapStrings) {
		return mapStrings != null ? JsonHelp.toJson(mapStrings) : null;
	}

	@TypeConverter
	public static Map<String, String> map(String string) {
		if (string == null)
			return new ArrayMap<>();
		try {
			return JsonHelp.mapperZZZZ.readValue(string, JsonHelp.mapperZZZZ.getTypeFactory().constructMapType(ArrayMap.class, String.class, String.class));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static class CatalogItemOptionForItemConverter {
		@TypeConverter
		public static String CatalogItemOptionForItem(List<CatalogItemOptionForItem> list) {
			return list == null ? null : JsonHelp.toJson(list);
		}

		@TypeConverter
		public static List<CatalogItemOptionForItem> CatalogItemOptionForItem(String string) {
			if (string == null) return null;
			try {
				return Arrays.asList(JsonHelp.mapperZZZZ.readValue(string, CatalogItemOptionForItem[].class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class CatalogItemOptionValueForItemVariationConverter {
		@TypeConverter
		public static String CatalogItemOptionValueForItemVariation(List<CatalogItemOptionValueForItemVariation> mods) {
			return mods == null ? null : JsonHelp.toJson(mods);
		}

		@TypeConverter
		public static List<CatalogItemOptionValueForItemVariation> CatalogItemOptionValueForItemVariation(String string) {
			if (string == null) return null;

			try {
				return Arrays.asList(JsonHelp.mapperZZZZ.readValue(string, CatalogItemOptionValueForItemVariation[].class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class CatalogModifierOverrideConverter {
		@TypeConverter
		public static String CatalogModifierOverride(List<CatalogModifierOverride> list) {
			return list == null ? null : JsonHelp.toJson(list);
		}

		@TypeConverter
		public static List<CatalogModifierOverride> CatalogModifierOverride(String string) {
			if (string == null) return null;
			try {
				return Arrays.asList(JsonHelp.mapperZZZZ.readValue(string, CatalogModifierOverride[].class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class CatalogV1IdConverter {
		@TypeConverter
		public static String CatalogV1Id(List<CatalogV1Id> catalogV1IdList) {
			return catalogV1IdList == null ? null : JsonHelp.toJson(catalogV1IdList);
		}

		@TypeConverter
		public static List<CatalogV1Id> CatalogV1Id(String string) {
			if (string == null) return null;
			try {
				CatalogV1Id[] catalogV1Ids = JsonHelp.mapperZZZZ.readValue(string, CatalogV1Id[].class);
				List<CatalogV1Id> catalogV1IdList = Arrays.stream(catalogV1Ids).collect(Collectors.toList());
				return catalogV1IdList;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class ItemVariationLocationOverridesConverter {
		@TypeConverter
		public static String ItemVariationLocationOverrides(List<ItemVariationLocationOverrides> itemVariationLocationOverridesList) {
			return itemVariationLocationOverridesList == null ? null : JsonHelp.toJson(itemVariationLocationOverridesList);
		}

		@TypeConverter
		public static List<ItemVariationLocationOverrides> ItemVariationLocationOverrides(String string) {
			if (string == null) return null;

			try {
				return Arrays.asList(JsonHelp.mapperZZZZ.readValue(string, ItemVariationLocationOverrides[].class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class OrderFulfillmentConverter {
		@TypeConverter
		public static String OrderFulfillment(List<OrderFulfillment> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<OrderFulfillment> OrderFulfillment(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, OrderFulfillment.class);
		}
	}

	public static class OrderReturnConverter {
		@TypeConverter
		public static String OrderReturn(List<OrderReturn> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<OrderReturn> OrderReturn(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, OrderReturn.class);
		}
	}

	public static class OrderServiceChargeConverter {
		@TypeConverter
		public static String OrderServiceCharge(List<OrderServiceCharge> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<OrderServiceCharge> OrderServiceCharge(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, OrderServiceCharge.class);
		}
	}

	public static class RefundConverter {
		@TypeConverter
		public static String Refund(List<Refund> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<Refund> Refund(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, Refund.class);
		}
	}

	public static class StringConverter {
		@TypeConverter
		public static String String(List<String> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<String> String(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, String.class);
		}

//		@TypeConverter
//		public static String toString(ArrayMap<String, String> map) {
//			return toJson(map);
//		}
//		@TypeConverter
//		public static ArrayMap<String, String> toMap(String value) {
//			if(value == null)
//				return null;
//			return Help.toObjectMap(value, String.class, String.class);
//		}
	}

	public static class TenderConverter {
		@TypeConverter
		public static String Tender(List<Tender> list) {
			return list != null ? JsonHelp.toJson(list) : null;
		}

		@TypeConverter
		public static List<Tender> Tender(String string) {
			if (string == null) return null;
			return JsonHelp.toObjectList(string, Tender.class);
		}
	}

}
