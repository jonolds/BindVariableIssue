package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogItemOptionValueForItemVariation;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;
import com.squareup.square.models.ItemVariationLocationOverrides;
import com.squareup.square.models.Money;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;


@JsonPropertyOrder({"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations",
	"present_at_location_ids", "absent_at_location_ids", "image_id", "name", "item_id", "sku", "upc", "ordinal", "pricingType", "price_money",
	"location_overrides", "track_inventory", "inventory_alert_type", "inventory_alert_threshold", "user_data", "service_duration",
	"item_option_values", "measurement_unit_id"})
@Entity(
	tableName = "entity_vari",
	indices = {@Index("item_id")},
	foreignKeys = {
		@ForeignKey(
			entity = CatalogItemEntity.class, parentColumns = "catalog_object_id", childColumns = "item_id", onDelete = CASCADE, deferred = true
		)
	}
)
public class CatalogVariEntity extends CatalogZZZZ implements Comparable<CatalogVariEntity> {


	@ColumnInfo(name = "item_id") public final String itemId;
	@ColumnInfo(name = "sku") public final String sku;
	@ColumnInfo(name = "upc") public final String upc;
	@ColumnInfo(name = "ordinal") public final Integer ordinal;
	@Embedded public final Money priceMoney;
	@ColumnInfo(name = "location_overrides") public final List<ItemVariationLocationOverrides> locationOverrides;
	@ColumnInfo(name = "track_inventory") public final Boolean trackInventory;
	@ColumnInfo(name = "inventory_alert_threshold") public final Long inventoryAlertThreshold;
	@ColumnInfo(name = "item_option_values") public final List<CatalogItemOptionValueForItemVariation> itemOptionValues;
	@ColumnInfo(name = "pricing_type") public final String pricingType;
	@ColumnInfo(name = "inventory_alert_type") public final String inventoryAlertType;
	@ColumnInfo(name = "user_data") public final String userData;
	@ColumnInfo(name = "service_duration") public final Long serviceDuration;
	@ColumnInfo(name = "measurement_unit_id") public final String measurementUnitId;


	@JsonCreator
	public CatalogVariEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String itemId,
		String name,
		String sku,
		String upc,
		Integer ordinal,
		String pricingType,
		Money priceMoney,
		List<ItemVariationLocationOverrides> locationOverrides,
		Boolean trackInventory,
		String inventoryAlertType,
		Long inventoryAlertThreshold,
		String userData,
		Long serviceDuration,
		List<CatalogItemOptionValueForItemVariation> itemOptionValues,
		String measurementUnitId
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
		this.itemId = itemId;
		this.sku = sku;
		this.upc = upc;
		this.ordinal = ordinal;
		this.pricingType = pricingType;
		this.priceMoney = priceMoney;
		this.locationOverrides = locationOverrides;
		this.trackInventory = trackInventory;
		this.inventoryAlertType = inventoryAlertType;
		this.inventoryAlertThreshold = inventoryAlertThreshold;
		this.userData = userData;
		this.serviceDuration = serviceDuration;
		this.itemOptionValues = itemOptionValues;
		this.measurementUnitId = measurementUnitId;
	}

	public CatalogVariEntity(CatalogObject from) {
		super(from, from.getItemVariationData().getName());
		this.itemId = from.getItemVariationData().getItemId();
		this.sku = from.getItemVariationData().getSku();
		this.upc = from.getItemVariationData().getUpc();
		this.ordinal = from.getItemVariationData().getOrdinal();
		this.pricingType = from.getItemVariationData().getPricingType();
		this.priceMoney = from.getItemVariationData().getPriceMoney();
		this.locationOverrides = from.getItemVariationData().getLocationOverrides();
		this.trackInventory = from.getItemVariationData().getTrackInventory();
		this.inventoryAlertType = from.getItemVariationData().getInventoryAlertType();
		this.inventoryAlertThreshold = from.getItemVariationData().getInventoryAlertThreshold();
		this.userData = from.getItemVariationData().getUserData();
		this.serviceDuration = from.getItemVariationData().getServiceDuration();
		this.itemOptionValues = from.getItemVariationData().getItemOptionValues();
		this.measurementUnitId = from.getItemVariationData().getMeasurementUnitId();
	}


	@Override public int compareTo(@NotNull CatalogVariEntity o) {
		return this.ordinal - o.ordinal;
	}
}
