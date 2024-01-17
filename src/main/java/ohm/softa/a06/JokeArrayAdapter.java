package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;

import java.io.IOException;

public class JokeArrayAdapter extends TypeAdapter<Joke[]> {

	private final Gson gson;

	public JokeArrayAdapter(){
		gson = new Gson();
	}

	@Override
	public void write(JsonWriter jsonWriter, Joke[] jokes) throws IOException {

	}

	@Override
	public Joke[] read(JsonReader jsonReader) throws IOException {
		Joke[] result = null;

		jsonReader.beginObject();
		while (jsonReader.hasNext()){
			switch (jsonReader.nextName()){
				case "total":
					jsonReader.skipValue();
					break;

				case "result":
					// result Json objekt wird in Array aus joke objekten deserialisiert
					// joke[].class ist Typ Class<Joke[]> also ein Joke-Array
					result = gson.fromJson(jsonReader, Joke[].class);
					break;
			}
		}

		jsonReader.endObject();

		return result;
	}
}
