package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;

public class JokeAdapter extends TypeAdapter<Joke>{

	private final Gson gson;

	public JokeAdapter() {
		gson = new Gson();
	}

	@Override
	public Joke read(JsonReader reader) {
		Joke result = gson.fromJson(reader, Joke.class);

		return result;
	}

	@Override
	public void write(JsonWriter writer, Joke inst) {

	}
}
