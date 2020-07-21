package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;
import com.squareup.square.models.Money;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
	tableName = "entity_mod",
	indices = {@Index("modifier_list_id")},
	foreignKeys = {
		@ForeignKey(
			entity = CatalogModlistEntity.class, parentColumns = "catalog_object_id", childColumns = "modifier_list_id", onDelete = CASCADE,
			deferred = true
		),
	}
)
@JsonPropertyOrder(
	{"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations", "present_at_location_ids",
		"absent_at_location_ids", "image_id", "name", "price_money", "ordinal", "modifier_list_id"})
public class CatalogModEntity extends CatalogZZZZ {


	@ColumnInfo(name = "ordinal") public final Integer ordinal;
	@Embedded public final Money priceMoney;
	@ColumnInfo(name = "modifier_list_id") public final String modifierListId;


	@JsonCreator
	public CatalogModEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String name,
		Money priceMoney,
		Integer ordinal,
		String modifierListId
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
		this.ordinal = ordinal;
		this.priceMoney = priceMoney;
		this.modifierListId = modifierListId;
	}

	public CatalogModEntity(CatalogObject from) {
		super(from, from.getModifierData().getName());
		this.ordinal = from.getModifierData().getOrdinal();
		this.priceMoney = from.getModifierData().getPriceMoney();
		this.modifierListId = from.getModifierData().getModifierListId();
	}

}
