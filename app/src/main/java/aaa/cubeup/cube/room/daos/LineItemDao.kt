package aaa.cubeup.cube.room.daos

import aaa.cubeup.cube.data.order.LineItemZZZZ
import aaa.cubeup.cube.room.entities.order.AppliedDiscountEntity
import aaa.cubeup.cube.room.entities.order.AppliedTaxEntity
import aaa.cubeup.cube.room.entities.order.LineItemEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*
import java.util.function.Consumer

@Dao
abstract class LineItemDao {

	fun insert(lineItem: LineItemZZZZ) {
		_insert(lineItem, ArrayList<AppliedTaxEntity>(lineItem.appliedTaxes), ArrayList<AppliedDiscountEntity>(lineItem.appliedDiscounts))
	}


	fun insert(lineItems: List<LineItemZZZZ>?) {
		if(lineItems == null) return

		val appliedTaxes: MutableList<AppliedTaxEntity> = ArrayList()
		val appliedDiscs: MutableList<AppliedDiscountEntity> = ArrayList()

		lineItems.forEach(Consumer { li: LineItemZZZZ ->
			appliedTaxes.addAll(li.appliedTaxes)
			appliedDiscs.addAll(li.appliedDiscounts)
		})
		_insert(ArrayList<LineItemEntity>(lineItems), appliedTaxes, appliedDiscs)
	}

	@Transaction @Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun _insert(lineItem: LineItemEntity?, appliedTaxes: List<AppliedTaxEntity>?, appliedDiscountEntities: List<AppliedDiscountEntity>?)

	@Transaction @Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun _insert(lineItems: List<LineItemEntity>?, appliedTaxes: List<AppliedTaxEntity>?, appliedDiscounts: List<AppliedDiscountEntity>?)

	@Transaction @Query("SELECT * FROM order_line_item WHERE reference_id IS :referenceId")
	abstract fun queryLineItemsOfOrderLV(referenceId: String?): LiveData<List<LineItemZZZZ?>?>?

	@Transaction @Query("SELECT * FROM order_line_item WHERE reference_id IS :referenceId")
	abstract fun queryLineItemsOfOrder(referenceId: String?): List<LineItemZZZZ?>?
}