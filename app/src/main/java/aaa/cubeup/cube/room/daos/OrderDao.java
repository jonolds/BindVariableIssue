package aaa.cubeup.cube.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aaa.cubeup.cube.data.order.LineItemMod;
import aaa.cubeup.cube.data.order.OrderZZZZ;
import aaa.cubeup.cube.data.nonentities.OrderButton;
import aaa.cubeup.cube.room.entities.order.AppliedDiscountEntity;
import aaa.cubeup.cube.room.entities.order.AppliedTaxEntity;
import aaa.cubeup.cube.room.entities.order.LineItemDiscountEntity;
import aaa.cubeup.cube.room.entities.order.LineItemEntity;
import aaa.cubeup.cube.room.entities.order.LineItemModEntity;
import aaa.cubeup.cube.room.entities.order.LineItemTaxEntity;
import aaa.cubeup.cube.room.entities.order.OrderObjectEntity;
import aaa.cubeup.cube.room.views.AppliedDiscount;
import aaa.cubeup.cube.room.views.AppliedTax;

import static aaa.cubeup.cube.utils.help.Helpers.emptyIfNull;


@SuppressWarnings({"UnusedReturnValue"})
@Dao
public abstract class OrderDao {

	/* Insert */
	public void insertOrders(OrderZZZZ orderZZZZ) {
		if(orderZZZZ == null)
			return;
		List<AppliedTax> appliedTaxes = new ArrayList<>();
		List<AppliedDiscount> appliedDiscs = new ArrayList<>();
		List<LineItemMod> mods = new ArrayList<>();

		batchApplied(orderZZZZ, appliedTaxes, appliedDiscs, mods);
		_insert(Collections.singletonList(orderZZZZ), new ArrayList<>(orderZZZZ.lineItems), new ArrayList<>(orderZZZZ.taxes), new ArrayList<>(orderZZZZ.discounts), appliedTaxes, appliedDiscs, mods);
	}



	@Transaction @Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract void _insert(
		List<? extends OrderObjectEntity> orderObjectEntity,
		List<? extends LineItemEntity> lineItems,
		List<? extends LineItemTaxEntity> liTaxes,
		List<? extends LineItemDiscountEntity> liDiscs,
		List<? extends AppliedTaxEntity> appliedTaxes,
		List<? extends AppliedDiscountEntity> appliedDiscs,
		List<? extends LineItemModEntity> mods
	);

	private static void batchApplied(OrderZZZZ orderZZZZ, List<AppliedTax> appliedTaxes, List<AppliedDiscount> appliedDiscs, List<LineItemMod> mods) {
		emptyIfNull(orderZZZZ.lineItems).forEach(li -> {
			appliedTaxes.addAll(emptyIfNull(li.appliedTaxes));
			appliedDiscs.addAll(emptyIfNull(li.appliedDiscounts));
			mods.addAll(emptyIfNull(li.modifiers));
		});
	}



	@Transaction
	@Update
	abstract void _update(
		List<? extends OrderObjectEntity> orderObjectEntity,
		List<? extends LineItemEntity> lineItems,
		List<? extends LineItemTaxEntity> liTaxes,
		List<? extends LineItemDiscountEntity> liDiscs,
		List<? extends AppliedTaxEntity> appliedTaxes,
		List<? extends AppliedDiscountEntity> appliedDiscs,
		List<? extends LineItemModEntity> mods
	);

	/* Query */
	@Transaction
	@Query("SELECT * FROM order_object WHERE reference_id = :referenceId")
	public abstract OrderZZZZ queryOrder(String referenceId);


	/* Query Live */
	@Transaction
	@Query("SELECT * from order_object ORDER BY order_id ASC")
	public abstract LiveData<List<OrderZZZZ>> queryOrdersLV();

	@Transaction
	@Query("SELECT * FROM order_object WHERE reference_id = :referenceId")
	public abstract LiveData<OrderZZZZ> queryOrderLV(String referenceId);


	@Query("SELECT name, reference_id, total_amount, total_currency, total_discount_amount, total_discount_currency, total_tax_amount, total_tax_currency, version from order_object ORDER BY updated_at DESC")
	public abstract LiveData<List<OrderButton>> queryOrderButtonViewsLV();

}