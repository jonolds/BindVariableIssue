package aaa.cubeup.cube.room.daos

import aaa.cubeup.cube.room.dtos.TempRocket
import aaa.cubeup.cube.room.views.ItemBV
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


@Dao
abstract class DtoDao {


	@Query("""
			INSERT INTO temp_ids(item_id, item_name, reference_id, line_item_uid, quantity, version, rocket_type) 
			VALUES (
				:itemId, 
				:itemName, 
				IFNULL(:referenceId, hex(randomblob(4))), 
				IFNULL(:lineItemUid, substr(hex(randomblob(4)), 1, 7)), 
				IFNULL(:quantity, '1'), 
				:version, 
				:rocketType
			)
		""")
	abstract fun _insertTemp(itemId: String, itemName: String?, referenceId: String?, lineItemUid: String?, quantity: String?, version: Int?,
		rocketType: String?)

	@Query("DELETE FROM temp_ids WHERE line_item_uid IS :lineItemUid")
	abstract fun deleteTempIdsByLiUid(lineItemUid: String)

	@Query("DELETE FROM temp_ids")
	abstract fun deleteAllTempIds()

	@Query("DELETE FROM temp_ids WHERE reference_id IS :referenceId")
	abstract fun deleteTempIdsByRef(referenceId: String)


	@Transaction
	@Query("""SELECT * FROM temp_ids WHERE line_item_uid IS :lineItemUid""")
	abstract fun getTempRocket(lineItemUid: String): TempRocket


	@Transaction
	@Query("""SELECT * FROM temp_ids WHERE line_item_uid IS :lineItemUid""")
	abstract fun _getTempRockets(lineItemUid: String): MutableList<TempRocket>

	@Query("""
		INSERT INTO temp_ids(item_id, item_name, reference_id, line_item_uid, quantity, version, rocket_type) 
		SELECT 
			catalog_object_id item_id, 
			name item_name, 
			hex(randomblob(4)), 
			:lineItemUid, 
			'1', 
			NULL, 
			'ITEM_ROCKET_TEMP'
		FROM entity_item 
	""")
	abstract fun _insertAllTemps(lineItemUid: String)

	@Query("SELECT * from bv_item")
	abstract fun getAllItemBVs(): List<ItemBV>
}