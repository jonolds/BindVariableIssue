package aaa.cubeup.cube.utils.help;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Helpers {



	public static <T> List<T> emptyIfNull(List<T> list) {
		return list == null ? new ArrayList<>() : list;
	}

	public static void println(String str) {
		JsonHelp.addBreaks(str).forEach(System.out::println);
	}

	public static void warn(String string) {
		Logger.getAnonymousLogger().log(Level.WARNING, string);
	}

	public static void warnjson(@NotNull Object obj) {
		JsonHelp.addBreaks(Optional.ofNullable(JsonHelp.toJson(obj)).orElse("")).forEach(x -> Logger.getAnonymousLogger().log(Level.WARNING, x));
	}

}