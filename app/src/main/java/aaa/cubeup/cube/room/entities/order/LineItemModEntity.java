package aaa.cubeup.cube.room.entities.order;

import androidx.annotation.NonNull;
import androidx.room.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemModifier;

import org.jetbrains.annotations.NotNull;

import static androidx.room.ForeignKey.CASCADE;


@JsonPropertyOrder({"applied_uid", "catalog_object_id", "name", "base_price_money", "total_price_money", "line_item_uid"})
@Entity(tableName = "order_line_item_mod", indices = {@Index("line_item_uid"),
	@Index("catalog_object_id")}, foreignKeys = @ForeignKey(entity = LineItemEntity.class, parentColumns = "line_item_uid", childColumns = "line_item_uid", onDelete = CASCADE, deferred = true))
public class LineItemModEntity {

	@PrimaryKey @NonNull @ColumnInfo(name = "applied_uid") @JsonProperty("applied_uid")
	public String appliedUid;
	@ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id")
	public String catalogObjectId;
	public String name;
	@Embedded(prefix = "base_price_") @JsonProperty("base_price_money")
	public Money basePriceMoney;
	@Embedded(prefix = "total_price_") @JsonProperty("total_price_money")
	public Money totalPriceMoney;
	@NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid;

	@JsonCreator
	public LineItemModEntity(@NotNull @JsonProperty("applied_uid") String appliedUid,
		@JsonProperty("catalog_object_id") String catalogObjectId,
		@JsonProperty("name") String name,
		@JsonProperty("base_price_money") Money basePriceMoney,
		@JsonProperty("total_price_money") Money totalPriceMoney,
		@NotNull @JsonProperty("line_item_uid") String lineItemUid) {
		this.appliedUid = appliedUid;
		this.lineItemUid = lineItemUid;
		this.catalogObjectId = catalogObjectId;
		this.totalPriceMoney = totalPriceMoney;
		this.name = name;
		this.basePriceMoney = basePriceMoney;
	}

	public LineItemModEntity(@NotNull OrderLineItemModifier from, @NotNull String lineItemUid) {
		this.appliedUid = from.getUid();
		this.lineItemUid = lineItemUid;
		this.catalogObjectId = from.getCatalogObjectId();
		this.totalPriceMoney = from.getTotalPriceMoney();
		this.name = from.getName();
		this.basePriceMoney = from.getBasePriceMoney();
	}

}