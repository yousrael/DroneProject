package objects;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by yousra on 15/02/17.
 */

public interface DroneService {
    public static final String ENDPOINT = "http://148.60.11.238:4000";

    @Headers("Cache-Control: max-age=640000")
    @GET("/position")
   PositionDrone position();

    @GET("/position")
   void getPosition(Callback<PositionDrone> callback);

   /* @GET("/users/{user}/repos")
    void listReposAsync(@Path("user") String user, Callback<List<Repo>> callback);

    @GET("/search/repositories")
    List<Repo> searchRepos(@Query("q") String query);*/
}
