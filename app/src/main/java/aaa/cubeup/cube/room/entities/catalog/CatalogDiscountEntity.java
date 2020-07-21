package aaa.cubeup.cube.room.entities.catalog;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogV1Id;
import com.squareup.square.models.Money;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity(tableName = "entity_discount")
@JsonPropertyOrder({"type", "catalog_object_id", "updated_at", "version", "is_deleted", "catalog_v1_ids", "present_at_all_locations",
	"present_at_location_ids", "absent_at_location_ids", "image_id", "name", "discount_type", "percentage", "amount_money", "pin_required",
	"label_color", "modify_tax_basis"})
public class CatalogDiscountEntity extends CatalogZZZZ {

	@ColumnInfo(name = "discount_type") public final String discountType;
	@ColumnInfo(name = "percentage") public final String percentage;
	@Embedded public final Money amountMoney;
	@ColumnInfo(name = "pin_required") public final Boolean pinRequired;
	@ColumnInfo(name = "label_color") public final String labelColor;
	@ColumnInfo(name = "modify_tax_basis") public final String modifyTaxBasis;


	@JsonCreator
	public CatalogDiscountEntity(
		String type, @NotNull String catalogObjectId, String updatedAt, Long version, Boolean isDeleted, List<CatalogV1Id> catalogV1Ids,
		Boolean presentAtAllLocations, List<String> presentAtLocationIds, List<String> absentAtLocationIds, String imageId,
		String name,
		String discountType,
		String percentage,
		Money amountMoney,
		Boolean pinRequired,
		String labelColor,
		String modifyTaxBasis
	) {
		super(type, catalogObjectId, updatedAt, version, isDeleted, catalogV1Ids, presentAtAllLocations, presentAtLocationIds, absentAtLocationIds,
			imageId, name);
		this.discountType = discountType;
		this.percentage = percentage;
		this.amountMoney = amountMoney;
		this.pinRequired = pinRequired;
		this.labelColor = labelColor;
		this.modifyTaxBasis = modifyTaxBasis;
	}

	public CatalogDiscountEntity(CatalogObject from) {
		super(from, from.getDiscountData().getName());
		this.discountType = from.getDiscountData().getDiscountType();
		this.percentage = from.getDiscountData().getPercentage();
		this.amountMoney = from.getDiscountData().getAmountMoney();
		this.pinRequired = from.getDiscountData().getPinRequired();
		this.labelColor = from.getDiscountData().getLabelColor();
		this.modifyTaxBasis = from.getDiscountData().getModifyTaxBasis();
	}

}
