package aaa.cubeup.cube.room.entities.catalog;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.CatalogItemModifierListInfo;
import com.squareup.square.models.CatalogModifierOverride;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
	tableName = "entity_item_modlist_info",
	indices = {
		@Index("item_id"),
		@Index(value = {"modifier_list_id"})
	},
	primaryKeys = {"modifier_list_id", "item_id"},
	foreignKeys = {
		@ForeignKey(
			entity = CatalogItemEntity.class, parentColumns = "catalog_object_id", childColumns = "item_id", onDelete = CASCADE, deferred = true
		),
		@ForeignKey(
			entity = CatalogModlistEntity.class, parentColumns = "catalog_object_id", childColumns = "modifier_list_id", onDelete = CASCADE,
			deferred = true
		),
	}
)
@JsonPropertyOrder({"modifier_list_id", "item_id", "modifier_overrides", "min_selected_modifiers", "max_selected_modifiers", "enabled"})
public class CatalogItemModlistInfoEntity {

	@ColumnInfo(name = "item_id") @NonNull public final String itemId;
	@ColumnInfo(name = "modifier_list_id") @NonNull public final String modifierListId;
	@ColumnInfo(name = "modifier_overrides") public final List<CatalogModifierOverride> modifierOverrides;
	@ColumnInfo(name = "min_selected_modifiers", defaultValue = "0") public final Integer minSelectedModifiers;
	@ColumnInfo(name = "max_selected_modifiers", defaultValue = "20") public final Integer maxSelectedModifiers;
	@ColumnInfo(name = "enabled") public final Boolean enabled;


	@JsonCreator
	public CatalogItemModlistInfoEntity(
		@NotNull String itemId,
		@NotNull String modifierListId,
		List<CatalogModifierOverride> modifierOverrides,
		Integer minSelectedModifiers,
		Integer maxSelectedModifiers,
		Boolean enabled
	) {
		this.itemId = itemId;
		this.modifierListId = modifierListId;
		this.modifierOverrides = modifierOverrides;
		this.minSelectedModifiers = minSelectedModifiers;
		this.maxSelectedModifiers = maxSelectedModifiers;
		this.enabled = enabled;
	}


	public CatalogItemModlistInfoEntity(@NotNull CatalogItemModifierListInfo catalogItemModifierListInfo, @NotNull String itemId) {
		this.itemId = itemId;
		this.modifierListId = catalogItemModifierListInfo.getModifierListId();
		this.modifierOverrides = catalogItemModifierListInfo.getModifierOverrides();
		this.minSelectedModifiers = catalogItemModifierListInfo.getMinSelectedModifiers();
		this.maxSelectedModifiers = catalogItemModifierListInfo.getMaxSelectedModifiers();
		this.enabled = catalogItemModifierListInfo.getEnabled();
	}


	public static List<CatalogItemModlistInfoEntity> fromSqrList(List<CatalogItemModifierListInfo> sqrList, String itemId) {
		if (sqrList == null)
			return null;
		return sqrList.stream().map(sqrInfo -> new CatalogItemModlistInfoEntity(sqrInfo, itemId)).collect(Collectors.toList());
	}

}
