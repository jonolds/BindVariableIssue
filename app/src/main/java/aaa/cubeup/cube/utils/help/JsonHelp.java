package aaa.cubeup.cube.utils.help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aaa.cubeup.cube.utils.serializers.Serializers.LiveDataSerializer;
import aaa.cubeup.cube.utils.serializers.Serializers.MutableLiveDataSerializer;

public abstract class JsonHelp {

	public static final ObjectMapper mapperZZZZ = new ObjectMapper() {
		private static final long serialVersionUID = -174113593500315394L;
		{
			setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
			//			configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);
			//			configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
			configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

			SimpleModule module = new SimpleModule()
				.addSerializer(LiveData.class, new LiveDataSerializer())
				.addSerializer(MutableLiveData.class, new MutableLiveDataSerializer());
			registerModule(module);
		}
	};

	public static List<String> addBreaks(String json) {
		List<String> list = new ArrayList<>();
		int index = 0;
		while (index < json.length()) {
			list.add(json.substring(index, Math.min(index + 4050, json.length())));
			index = index + 4050;
		}
		if (list.size() > 0) list.add("\n \n");
		return list;
	}

	@Nullable
	public static String toJson(Object object) {
		try { return mapperZZZZ.writeValueAsString(object); } catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static Object toObject(String json, Class<?> c) {
		try { return mapperZZZZ.readValue(json, c); } catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static <T> List<T> toObjectList(String jsonString, Class<T> innerclass) {
		try {
			return mapperZZZZ.readValue(jsonString, mapperZZZZ.getTypeFactory().constructCollectionType(ArrayList.class, innerclass));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
