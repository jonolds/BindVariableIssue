package aaa.cubeup.cube.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import aaa.cubeup.cube.CatalogRoomDatabase;
import aaa.cubeup.cube.data.order.OrderZZZZ;
import aaa.cubeup.cube.data.nonentities.CatalogButtonWZZZZ;
import aaa.cubeup.cube.data.nonentities.OrderButton;
import aaa.cubeup.cube.room.daos.DtoDao;
import aaa.cubeup.cube.room.daos.LineItemDao;
import aaa.cubeup.cube.room.daos.OrderDao;
import aaa.cubeup.cube.room.daos.UiDao;
import aaa.cubeup.cube.room.entities.otherentities.Property;

import static aaa.cubeup.cube.utils.help.Helpers.println;


public class ZZZZRepo {


	private static ZZZZRepo INSTANCE;


	/* Daos */
	public final DtoDao dtoDao;
	public final UiDao uiDao;
	public final OrderDao orderDao;
	public final LineItemDao lineItemDao;



	private LiveData<List<CatalogButtonWZZZZ>> buttonWZZZZsLV;

	private LiveData<List<OrderZZZZ>> ordersLV;

	private LiveData<List<OrderButton>> orderButtonViewsLV;

	public static ZZZZRepo getInstance(Application application) {
		if (INSTANCE == null)
			INSTANCE = new ZZZZRepo(application);
		return INSTANCE;
	}

	private ZZZZRepo(Application application) {
		CatalogRoomDatabase db = CatalogRoomDatabase.getDatabase(application);
		assert db != null;
		this.uiDao = db.uiDao();
		this.lineItemDao = db.lineItemDao();
		this.orderDao = db.orderZZZZDao();
		this.dtoDao = db.dtoDao();

		Property prop = new Property("timestamp", Calendar.getInstance(TimeZone.getDefault()).toString(), null);
		println("Property Row #" + supplyAsync(() -> uiDao.insert(prop)).join());
	}

	public LiveData<List<CatalogButtonWZZZZ>> getButtonWZZZZsLV() {
		return Optional.ofNullable(buttonWZZZZsLV)
			.orElse(buttonWZZZZsLV = uiDao.queryButtonWZZZZsLV());
	}

	public LiveData<List<OrderButton>> getOrderButtonViewsLV() {
		return Optional.ofNullable(orderButtonViewsLV)
			.orElse(orderButtonViewsLV = orderDao.queryOrderButtonViewsLV());
	}

	public LiveData<List<OrderZZZZ>> getOrdersLV() {
		return Optional.ofNullable(ordersLV)
			.orElse(ordersLV = orderDao.queryOrdersLV());
	}


	public CompletableFuture<OrderZZZZ> insertAsync(OrderZZZZ orderZZZZ) {
		return supplyAsync(() -> {
			Optional.ofNullable(orderZZZZ)
				.ifPresent(order -> orderDao.insertOrders(order));
			return orderZZZZ;
		});
	}



	public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
		return CompletableFuture.supplyAsync(supplier);
	}


}