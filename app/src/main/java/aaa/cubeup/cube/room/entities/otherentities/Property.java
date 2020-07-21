package aaa.cubeup.cube.room.entities.otherentities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.jetbrains.annotations.NotNull;

@JsonPropertyOrder({"id", "value_1", "value_2"})
@Entity(tableName = "properties")
public class Property {

	@PrimaryKey
	@NonNull
	public String id;
	@ColumnInfo(name = "value_1")
	public String value1;
	@ColumnInfo(name = "value_2")
	public String value2;

	public Property(@NotNull String id, String value1, String value2) {
		this.id = id;
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	@NotNull
	public String toString() {
		return "Property{" + "id='" + id + '\'' + ", value1='" + value1 + '\'' + ", value2='" + value2 + '\'' + '}';
	}
}
