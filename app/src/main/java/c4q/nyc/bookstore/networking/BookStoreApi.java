package c4q.nyc.bookstore.networking;

import c4q.nyc.bookstore.model.BookModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Wayne Kellman on 12/18/17.
 */

public interface BookStoreApi {

    @GET("a7d373399a5e146104e9de3ee7987680/raw/c93dc5c11a8b8e7eddb63d269cc21cc37ad59006/book_store_data")
    Call<BookModel[]> getModel();
}
