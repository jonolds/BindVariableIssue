package aaa.cubeup.cube.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import aaa.cubeup.cube.data.nonentities.CatalogButtonWZZZZ;
import aaa.cubeup.cube.room.entities.otherentities.Property;

@Dao
public abstract class UiDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public abstract Long insert(Property property);

	@Transaction
	@Query("SELECT * from catalog_button ORDER BY `index` ASC")
	public abstract LiveData<List<CatalogButtonWZZZZ>> queryButtonWZZZZsLV();

}
