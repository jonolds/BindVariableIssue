package aaa.cubeup.cube.room.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Ignore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.OrderLineItemTax;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.BaseBVable;
import aaa.cubeup.cube.room.entities.catalog.CatalogTaxEntity;

import static aaa.cubeup.cube.data.nonentities.Cattype.TAX;

@JsonPropertyOrder({"name", "catalog_object_id", "percentage", "type"})
@DatabaseView(value = "SELECT catalog_object_id, name, inclusion_type, percentage FROM entity_tax ORDER BY name ASC", viewName = "data_order_tax")
public class TaxOrderData implements BaseBVable {

	public String name;
	@ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id")
	public String catalogObjectId;
	public String percentage = "0.0";
	@ColumnInfo(name = "inclusion_type") @JsonProperty("type")
	public String inclusionType = CatalogTaxEntity.ADDITIVE;

	public TaxOrderData() {
	}

	public TaxOrderData(@NotNull OrderLineItemTax from) {
		this(from.getCatalogObjectId(), from.getName(), from.getType(), from.getPercentage());
	}

	@Ignore
	public TaxOrderData(String catalogObjectId, String name, String inclusionType, String percentage) {
		this.catalogObjectId = catalogObjectId;
		this.name = name;
		this.inclusionType = inclusionType;
		this.percentage = percentage;
	}

	public TaxOrderData(@NotNull TaxOrderData from) {
		this.name = from.name;
		this.catalogObjectId = from.catalogObjectId;
		this.percentage = from.percentage;
		this.inclusionType = from.inclusionType;
	}

	@NotNull @Override
	public String findId() {
		return catalogObjectId;
	}

	@NotNull @Override
	public Cattype cattype() {
		return TAX;
	}

	@Override
	public String rightLabel() {
		return Optional.ofNullable(percentage).map(p -> p + "%").orElse("");
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof TaxOrderData)) {
			return false;
		}
		TaxOrderData other = (TaxOrderData) o;
		return Objects.equals(catalogObjectId, other.catalogObjectId) && Objects.equals(name, other.name) && Objects.equals(inclusionType, other.inclusionType) && Objects.equals(percentage, other.percentage);
	}
	@Override
	@NotNull
	public String toString() {
		return "name: " + name + "  catalogObjectId: " + catalogObjectId + "  percentage: " + percentage + "  inclusionType: " + inclusionType;
	}

	@NotNull @Override public String findName() {
		return name;
	}

}
