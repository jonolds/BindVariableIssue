package aaa.cubeup.cube.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import aaa.cubeup.cube.data.nonentities.CatalogButtonWZZZZ;
import aaa.cubeup.cube.room.ZZZZRepo;


public class ZZZZVM extends AndroidViewModel {

	public ZZZZRepo repo;

	public ZZZZVM(Application application) {
		super(application);
		this.repo = ZZZZRepo.getInstance(application);
	}


	public static int columnCount;
	public static List<CatalogButtonWZZZZ> getRightSizedCatalogButtonsList(List<CatalogButtonWZZZZ> buttonWZZZZs) {
		return buttonWZZZZs.subList(0, Math.min(columnCount == 4 ? 20 : 25, buttonWZZZZs.size()));
	}


	public LiveData<List<CatalogButtonWZZZZ>> getButtonWZZZZsLV() {
		return repo.getButtonWZZZZsLV();
	}



}