package aaa.cubeup.cube.utils.serializers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import aaa.cubeup.cube.utils.help.Helpers;
import aaa.cubeup.cube.utils.help.JsonHelp;


@SuppressWarnings({"rawtypes", "unused"})
public class Serializers {

	public static class LiveDataSerializer extends StdSerializer<LiveData> {
		public LiveDataSerializer() {
			this(null);
		}

		public LiveDataSerializer(Class<LiveData> t) {
			super(t);
		}

		@Override
		public void serialize(LiveData value, JsonGenerator jgen, SerializerProvider provider) {
			try {
				jgen.writeStartObject();
				jgen.writeStringField("LiveData value", JsonHelp.toJson(value.getValue()));
				jgen.writeEndObject();
			} catch (Exception e) {
				Helpers.warn("LiveDataSerializer Failed.");
				Helpers.warnjson(e);
			}
		}
	}

	public static class MutableLiveDataSerializer extends StdSerializer<MutableLiveData> {
		public MutableLiveDataSerializer() {
			this(null);
		}

		public MutableLiveDataSerializer(Class<MutableLiveData> t) {
			super(t);
		}

		@Override
		public void serialize(MutableLiveData value, JsonGenerator jgen, SerializerProvider provider) {
			try {
				jgen.writeStartObject();
				jgen.writeStringField("MutableLiveData value", JsonHelp.toJson(value.getValue()));
				jgen.writeEndObject();
			} catch (Exception e) {
				Helpers.warn("MutableLiveDataSerializer Failed.");
				Helpers.warnjson(e);
			}
		}
	}

}
