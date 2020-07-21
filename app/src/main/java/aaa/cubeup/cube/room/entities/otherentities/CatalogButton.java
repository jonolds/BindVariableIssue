package aaa.cubeup.cube.room.entities.otherentities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonProperty;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.ZZZZButtonable;

@Entity(tableName = "catalog_button")
public class CatalogButton {

	@PrimaryKey
	@ColumnInfo(name = "index")
	@JsonProperty("index")
	public int index;
	@JsonProperty("cattype")
	@ColumnInfo(name = "cattype")
	public Cattype cattype;
	@ColumnInfo(name = "catalog_object_id")
	@JsonProperty("catalog_object_id")
	public String catalogObjectId;

	/* CONSTRUCTORS */
	public CatalogButton() {
	}

	@Ignore
	public CatalogButton(int index, ZZZZButtonable cubeEntitiable) {
		this.index = index;
		this.catalogObjectId = cubeEntitiable.findId();
		this.cattype = cubeEntitiable.cattype();
	}

}


