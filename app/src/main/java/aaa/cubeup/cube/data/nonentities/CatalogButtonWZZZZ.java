package aaa.cubeup.cube.data.nonentities;

import androidx.room.Ignore;
import androidx.room.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

import aaa.cubeup.cube.room.entities.catalog.CatalogCategoryEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogDiscountEntity;
import aaa.cubeup.cube.room.entities.otherentities.CatalogButton;
import aaa.cubeup.cube.room.views.ItemBV;

import static aaa.cubeup.cube.data.nonentities.Cattype.*;

@JsonPropertyOrder({"index", "cattype", "catalog_object_id", "category_cube", "discount_cube", "item_cube"})
public class CatalogButtonWZZZZ extends CatalogButton implements Serializable {

	@JsonProperty("category_cube")
	@Relation(parentColumn = "catalog_object_id", entityColumn = "catalog_object_id")
	public CatalogCategoryEntity catalogCategoryEntity;

	@JsonProperty("discount_cube")
	@Relation(parentColumn = "catalog_object_id", entityColumn = "catalog_object_id")

	public CatalogDiscountEntity catalogDiscountEntity;

	@JsonProperty("item_cube")
	@Relation(parentColumn = "catalog_object_id", entityColumn = "item_id")
	public ItemBV itemBV;

	@Ignore @JsonIgnore
	public String getButtonText() {
		if (this.cattype == CATEGORY) return catalogCategoryEntity.name;
		if (this.cattype == DISCOUNT) return catalogDiscountEntity.name;
		if (this.cattype == ITEM) return itemBV.getName();
		return null;
	}
}
