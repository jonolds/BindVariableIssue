package aaa.cubeup.cube;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import aaa.cubeup.cube.data.order.LineItemZZZZ;
import aaa.cubeup.cube.data.order.LineItemDiscount;
import aaa.cubeup.cube.data.order.LineItemTax;
import aaa.cubeup.cube.room.Converters;
import aaa.cubeup.cube.room.Converters.CatalogItemOptionForItemConverter;
import aaa.cubeup.cube.room.Converters.CatalogItemOptionValueForItemVariationConverter;
import aaa.cubeup.cube.room.Converters.CatalogModifierOverrideConverter;
import aaa.cubeup.cube.room.Converters.CatalogV1IdConverter;
import aaa.cubeup.cube.room.Converters.ItemVariationLocationOverridesConverter;
import aaa.cubeup.cube.room.Converters.OrderFulfillmentConverter;
import aaa.cubeup.cube.room.Converters.OrderReturnConverter;
import aaa.cubeup.cube.room.Converters.OrderServiceChargeConverter;
import aaa.cubeup.cube.room.Converters.RefundConverter;
import aaa.cubeup.cube.room.Converters.StringConverter;
import aaa.cubeup.cube.room.Converters.TenderConverter;
import aaa.cubeup.cube.room.daos.CatalogDao;
import aaa.cubeup.cube.room.daos.DtoDao;
import aaa.cubeup.cube.room.daos.LineItemDao;
import aaa.cubeup.cube.room.daos.OrderDao;
import aaa.cubeup.cube.room.daos.UiDao;
import aaa.cubeup.cube.room.dtos.TempDiscountDTO;
import aaa.cubeup.cube.room.dtos.TempModDTO;
import aaa.cubeup.cube.room.dtos.TempTaxDTO;
import aaa.cubeup.cube.room.dtos.TempVariDTO;
import aaa.cubeup.cube.room.entities.catalog.CatalogCategoryEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogDiscountEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogItemEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogItemModlistInfoEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogModEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogModlistEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogTaxEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogVariEntity;
import aaa.cubeup.cube.room.entities.catalog.TempModlistInfo;
import aaa.cubeup.cube.room.entities.order.AppliedDiscountEntity;
import aaa.cubeup.cube.room.entities.order.AppliedTaxEntity;
import aaa.cubeup.cube.room.entities.order.LineItemDiscountEntity;
import aaa.cubeup.cube.room.entities.order.LineItemEntity;
import aaa.cubeup.cube.room.entities.order.LineItemModEntity;
import aaa.cubeup.cube.room.entities.order.LineItemTaxEntity;
import aaa.cubeup.cube.room.entities.order.OrderObjectEntity;
import aaa.cubeup.cube.room.entities.otherentities.CatalogButton;
import aaa.cubeup.cube.room.entities.otherentities.Property;
import aaa.cubeup.cube.room.entities.otherentities.TempId;
import aaa.cubeup.cube.room.views.AppliedDiscount;
import aaa.cubeup.cube.room.views.AppliedTax;
import aaa.cubeup.cube.room.views.DiscountOrderData;
import aaa.cubeup.cube.room.views.ItemBV;
import aaa.cubeup.cube.room.views.ModOrderData;
import aaa.cubeup.cube.room.views.TaxOrderData;
import aaa.cubeup.cube.room.views.VariBV;

import static aaa.cubeup.cube.utils.help.Helpers.println;


@Database(
	entities = {
		AppliedDiscountEntity.class,
		AppliedTaxEntity.class,
		CatalogButton.class,
		CatalogCategoryEntity.class,
		CatalogDiscountEntity.class,
		CatalogItemEntity.class,
		CatalogItemModlistInfoEntity.class,
		CatalogModEntity.class,
		CatalogModlistEntity.class,
		CatalogTaxEntity.class,
		CatalogVariEntity.class,
		LineItemDiscountEntity.class,
		LineItemEntity.class,
		LineItemModEntity.class,
		LineItemTaxEntity.class,
		OrderObjectEntity.class,
		Property.class,
		TempId.class,
	},
	views = {
		AppliedDiscount.class,
		AppliedTax.class,
		DiscountOrderData.class,
		ItemBV.class,
		LineItemZZZZ.class,
		LineItemDiscount.class,
		LineItemTax.class,
		ModOrderData.class,
		TaxOrderData.class,
		TempDiscountDTO.class,
		TempModDTO.class,
		TempModlistInfo.class,
		TempTaxDTO.class,
		TempVariDTO.class,
		VariBV.class,
	},
	version = 1,
	exportSchema = false
)
@TypeConverters({
					CatalogItemOptionForItemConverter.class,
					CatalogItemOptionValueForItemVariationConverter.class,
					CatalogModifierOverrideConverter.class,
					CatalogV1IdConverter.class,
					Converters.class,
					ItemVariationLocationOverridesConverter.class,
					OrderFulfillmentConverter.class,
					OrderReturnConverter.class,
					OrderServiceChargeConverter.class,
					RefundConverter.class,
					StringConverter.class,
					TenderConverter.class,
				})
public abstract class CatalogRoomDatabase extends RoomDatabase {

	public abstract UiDao uiDao();
	public abstract CatalogDao catalogDao();
	public abstract LineItemDao lineItemDao();
	public abstract OrderDao orderZZZZDao();
	public abstract DtoDao dtoDao();

	private static volatile CatalogRoomDatabase INSTANCE;



	public static CatalogRoomDatabase getDatabase(final Context context) {

		println("CHANGE THIS LINE kjlkasdfasdfafasdfafasdfjgj");

		if (INSTANCE == null) {
			synchronized (CatalogRoomDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CatalogRoomDatabase.class, "catalog_database")
						.fallbackToDestructiveMigration()
//						.addCallback(sRoomDatabaseCallback)
						.build();
				}
			}
		}
		return INSTANCE;
	}

}
