package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity(tableName = "entity_category")
@JsonPropertyOrder({"type", "id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations", "present_at_location_ids",
	"absent_at_location_ids", "image_id", "name"})
public class CatalogCategoryEntity extends CatalogZZZZ {


	@JsonCreator
	public CatalogCategoryEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String name
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
	}

	public CatalogCategoryEntity(CatalogObject from) {
		super(from, from.getCategoryData().getName());
	}


}
