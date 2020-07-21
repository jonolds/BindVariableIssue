package aaa.cubeup.cube.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.ZZZZButtonable;
import aaa.cubeup.cube.room.entities.catalog.CatalogCategoryEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogZZZZ;
import aaa.cubeup.cube.room.entities.catalog.CatalogDiscountEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogItemEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogItemModlistInfoEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogModEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogModlistEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogTaxEntity;
import aaa.cubeup.cube.room.entities.catalog.CatalogVariEntity;
import aaa.cubeup.cube.room.entities.otherentities.CatalogButton;
import aaa.cubeup.cube.utils.ButtonComparator;
//import aaa.cubeup.cube.utils.Comparators.ButtonComparator;


@Dao
public abstract class CatalogDao {

	@Transaction
	public void insert(List<CatalogZZZZ> cubes) {
		if (cubes == null) return;

		List<CatalogCategoryEntity> categories = new ArrayList<>();
		List<CatalogDiscountEntity> discountEntities = new ArrayList<>();
		List<CatalogItemEntity> items = new ArrayList<>();
		List<CatalogVariEntity> varis = new ArrayList<>();
		List<CatalogModlistEntity> modlists = new ArrayList<>();
		List<CatalogModEntity> mods = new ArrayList<>();
		List<CatalogTaxEntity> taxes = new ArrayList<>();
		List<CatalogItemModlistInfoEntity> infos = new ArrayList<>();
		List<ZZZZButtonable> buttonsList = new ArrayList<>();

		for (int i = 0; i < cubes.size(); i++) {
			if (cubes.get(i).cattype() == Cattype.CATEGORY)
				categories.add((CatalogCategoryEntity) addToButtonables(buttonsList, cubes.get(i)));
			else if (cubes.get(i).cattype() == Cattype.DISCOUNT)
				discountEntities.add((CatalogDiscountEntity) addToButtonables(buttonsList, cubes.get(i)));
			else if (cubes.get(i).cattype() == Cattype.TAX) taxes.add((CatalogTaxEntity) cubes.get(i));
			else if (cubes.get(i).cattype() == Cattype.ITEM) {
				items.add((CatalogItemEntity) addToButtonables(buttonsList, cubes.get(i)));
				if(((CatalogItemEntity) cubes.get(i)).modifierListInfo != null)
					infos.addAll(((CatalogItemEntity) cubes.get(i)).modifierListInfo);
			} else if (cubes.get(i).cattype() == Cattype.VARI) {
				varis.add((CatalogVariEntity) cubes.get(i));
			} else if (cubes.get(i).cattype() == Cattype.MODLIST) modlists.add((CatalogModlistEntity) cubes.get(i));
			else if (cubes.get(i).cattype() == Cattype.MOD) mods.add((CatalogModEntity) cubes.get(i));
		}

		_insert(categories, discountEntities, items, mods, modlists, taxes, varis, infos, sort(buttonsList));
	}
	public static CatalogZZZZ addToButtonables(List<ZZZZButtonable> list, CatalogZZZZ catalogZZZZ) {
		list.add(catalogZZZZ);
		return catalogZZZZ;
	}

	@Transaction @Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract void _insert(
		List<CatalogCategoryEntity> categories,
		List<CatalogDiscountEntity> discounts,
		List<CatalogItemEntity> items,
		List<CatalogModEntity> mods,
		List<CatalogModlistEntity> modlists,
		List<CatalogTaxEntity> taxes,
		List<CatalogVariEntity> varis,
		List<CatalogItemModlistInfoEntity> infos,
		List<CatalogButton> buttonables
	);

	public static List<CatalogButton> sort(List<ZZZZButtonable> buttonables) {
		buttonables.sort(new ButtonComparator());
		return IntStream.range(0, Math.min(buttonables.size(), 25)).mapToObj(i -> new CatalogButton(i, buttonables.get(i))).collect(Collectors.toList());
	}
}
