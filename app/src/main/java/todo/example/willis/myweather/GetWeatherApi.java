package todo.example.willis.myweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by willis on 7/13/17.
 */

public interface GetWeatherApi {

    public static final String BASE_URL = "http://knowweather.duapp.com";

    @GET("/v1.0/weather/{id}")
    Call<WeatherEntity> getWeather(@Path("id") String id);
}
