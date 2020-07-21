package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity(tableName = "entity_tax")
@JsonPropertyOrder({"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations",
	"present_at_location_ids", "absent_at_location_ids", "image_id", "name", "calculation_phase", "inclusion_type", "percentage",
	"applies_to_custom_amounts", "enabled"})
public class CatalogTaxEntity extends CatalogZZZZ {

	@Ignore @JsonIgnore
	public static final String ADDITIVE = "ADDITIVE";


	@ColumnInfo(name = "calculation_phase") public final String calculationPhase;
	@ColumnInfo(name = "inclusion_type") public final String inclusionType;
	@ColumnInfo(name = "percentage") public final String percentage;
	@ColumnInfo(name = "applies_to_custom_amounts") public final Boolean appliesToCustomAmounts;
	@ColumnInfo(name = "enabled") public final Boolean enabled;


	@JsonCreator
	public CatalogTaxEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String name,
		String calculationPhase,
		String inclusionType,
		String percentage,
		Boolean appliesToCustomAmounts,
		Boolean enabled
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
		this.calculationPhase = calculationPhase;
		this.appliesToCustomAmounts = appliesToCustomAmounts;
		this.inclusionType = inclusionType;
		this.percentage = percentage;
		this.enabled = enabled;
	}

	public CatalogTaxEntity(CatalogObject from) {
		super(from, from.getTaxData().getName());
		this.calculationPhase = from.getTaxData().getCalculationPhase();
		this.appliesToCustomAmounts = from.getTaxData().getAppliesToCustomAmounts();
		this.inclusionType = from.getTaxData().getInclusionType();
		this.percentage = from.getTaxData().getPercentage();
		this.enabled = from.getTaxData().getEnabled();
	}

}