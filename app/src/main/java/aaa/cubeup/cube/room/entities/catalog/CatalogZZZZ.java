package aaa.cubeup.cube.room.entities.catalog;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.ZZZZButtonable;


@JsonTypeInfo(visible = true, use = Id.NAME, property = "type")
@JsonSubTypes({
	@Type(value = CatalogCategoryEntity.class, name = "CATEGORY"),
	@Type(value = CatalogDiscountEntity.class, name = "DISCOUNT"),
	@Type(value = CatalogItemEntity.class, name = "ITEM"),
	@Type(value = CatalogVariEntity.class, name = "ITEM_VARIATION"),
	@Type(value = CatalogModEntity.class, name = "MODIFIER"),
	@Type(value = CatalogModlistEntity.class, name = "MODIFIER_LIST"),
	@Type(value = CatalogTaxEntity.class, name = "TAX"),
})
@JsonPropertyOrder({"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations",
	"present_at_location_ids", "absent_at_location_ids", "image_id"})
public abstract class CatalogZZZZ implements ZZZZButtonable {

	@ColumnInfo(name = "type") public String type;
	@ColumnInfo(name = "catalog_object_id") @PrimaryKey @NonNull public String catalogObjectId;
	@ColumnInfo(name = "updated_at") public String updatedAt;
	@ColumnInfo(name = "version") public long version;
	@ColumnInfo(name = "is_deleted") public boolean isDeleted;
	@ColumnInfo(name = "catalog_v1_ids") public List<CatalogV1Id> catalogV1Ids;
	@ColumnInfo(name = "present_at_all_locations") public boolean presentAtAllLocations;
	@ColumnInfo(name = "present_at_location_ids") public List<String> presentAtLocationIds;
	@ColumnInfo(name = "absent_at_location_ids") public List<String> absentAtLocationIds;
	@ColumnInfo(name = "image_id") public String imageId;
	@ColumnInfo(name = "name") public String name;


	@JsonCreator
	public CatalogZZZZ(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId, String name
	) {
		this.type = type;
		this.catalogObjectId = catalogObjectId;
		this.updatedAt = updatedAt;
		this.version = version;
		this.isDeleted = isDeleted;
		this.catalogV1Ids = catalogV1Ids;
		this.presentAtAllLocations = presentAtAllLocations;
		this.presentAtLocationIds = presentAtLocationIds;
		this.absentAtLocationIds = absentAtLocationIds;
		this.imageId = imageId;
		this.name = name;
	}

	public CatalogZZZZ(@NotNull CatalogObject from, String name) {
		this.type = from.getType();
		this.catalogObjectId = from.getId();
		this.updatedAt = from.getUpdatedAt();
		this.version = from.getVersion();
		this.imageId = from.getImageId();
		this.isDeleted = from.getIsDeleted();
		this.catalogV1Ids = from.getCatalogV1Ids();
		this.presentAtAllLocations = from.getPresentAtAllLocations();
		this.presentAtLocationIds = from.getPresentAtLocationIds();
		this.absentAtLocationIds = from.getAbsentAtLocationIds();
		this.name = name;
	}


	@Override @NotNull public String findId() {
		return this.catalogObjectId;
	}
	@Override @NotNull public String findName() {
		return name != null ? name : "";
	}
	@Override @NotNull public Cattype cattype() {
		return Cattype.fromString(type);
	}

}
