package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogItemOptionForItem;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity(
	tableName = "entity_item",
	indices = {
		@Index("name"),
		@Index(value = {"category_id"})
	}
)
@JsonPropertyOrder({"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations",
	"present_at_location_ids", "absent_at_location_ids", "image_id", "item_data"})
public class CatalogItemEntity extends CatalogZZZZ {


	@ColumnInfo(name = "item_id") public final String itemId;
	@ColumnInfo(name = "description") public final String description;
	@ColumnInfo(name = "abbreviation") public final String abbreviation;
	@ColumnInfo(name = "label_color") public final String labelColor;
	@ColumnInfo(name = "available_online") public final Boolean availableOnline;
	@ColumnInfo(name = "available_for_pickup") public final Boolean availableForPickup;
	@ColumnInfo(name = "available_electronically") public final Boolean availableElectronically;
	@ColumnInfo(name = "category_id") public final String categoryId;
	@ColumnInfo(name = "tax_ids") public final List<String> taxIds;
	@ColumnInfo(name = "product_type") public final String productType;
	@ColumnInfo(name = "skip_modifier_screen") public final Boolean skipModifierScreen;
	@ColumnInfo(name = "item_options") public final List<CatalogItemOptionForItem> itemOptions;

	@Ignore public List<CatalogItemModlistInfoEntity> modifierListInfo;

	@JsonCreator
	public CatalogItemEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String itemId,
		String name,
		String description,
		String abbreviation,
		String labelColor,
		Boolean availableOnline,
		Boolean availableForPickup,
		Boolean availableElectronically,
		String categoryId,
		List<String> taxIds,
		String productType,
		Boolean skipModifierScreen,
		List<CatalogItemOptionForItem> itemOptions
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.abbreviation = abbreviation;
		this.labelColor = labelColor;
		this.availableOnline = availableOnline;
		this.availableForPickup = availableForPickup;
		this.availableElectronically = availableElectronically;
		this.categoryId = categoryId;
		this.taxIds = taxIds;
		this.productType = productType;
		this.skipModifierScreen = skipModifierScreen;
		this.itemOptions = itemOptions;
	}

	public CatalogItemEntity(CatalogObject from) {
		super(from, from.getItemData().getName());

		this.itemId = from.getId();
		this.description = from.getItemData().getDescription();
		this.abbreviation = from.getItemData().getAbbreviation();
		this.labelColor = from.getItemData().getLabelColor();
		this.availableOnline = from.getItemData().getAvailableOnline();
		this.availableForPickup = from.getItemData().getAvailableForPickup();
		this.availableElectronically = from.getItemData().getAvailableElectronically();
		this.categoryId = from.getItemData().getCategoryId();
		this.taxIds = from.getItemData().getTaxIds();
		this.productType = from.getItemData().getProductType();
		this.skipModifierScreen = from.getItemData().getSkipModifierScreen();
		this.itemOptions = from.getItemData().getItemOptions();

		this.modifierListInfo = CatalogItemModlistInfoEntity.fromSqrList(from.getItemData().getModifierListInfo(), catalogObjectId);

	}

}
