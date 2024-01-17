package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder()
			//.registerTypeAdapter(Joke.class, new JokeAdapter())
			.registerTypeAdapter(Joke[].class, new JokeArrayAdapter())
			.create();

		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("https://api.chucknorris.io")
			.build();

		CNJDBApi api = retrofit.create(CNJDBApi.class);

		Call<Joke> call = api.getRandomJoke();
        Response<Joke> resp = null;
        try {
            resp = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!resp.isSuccessful())
            try {
                throw new IOException("Request failed: " + resp.code());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        Joke j = resp.body();

		System.out.println(j.toString());
	}

}
