package c4q.nyc.bookstore.model;

/**
 * Created by Wayne Kellman on 12/18/17.
 */

public class BookModel {
    /**
     * {
     "id": "978-0641723445",
     "cat": [
     "book",
     "hardcover"
     ],
     "name": "The Lightning Thief",
     "author": "Rick Riordan",
     "series_t": "Percy Jackson and the Olympians",
     "sequence_i": 1,
     "genre_s": "fantasy",
     "inStock": true,
     "price": 0,
     "pages_i": 384,
     "link": "https://graduateland.com/api/v2/users/jesper/cv"
     }
     */
    private String id;
    private String[] cat;
    private String name;
    private String author;
    private String series_t;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCat() {
        return cat;
    }

    public void setCat(String[] cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeries_t() {
        return series_t;
    }

    public void setSeries_t(String series_t) {
        this.series_t = series_t;
    }

    public long getSequence_i() {
        return sequence_i;
    }

    public void setSequence_i(long sequence_i) {
        this.sequence_i = sequence_i;
    }

    public String getGenre_s() {
        return genre_s;
    }

    public void setGenre_s(String genre_s) {
        this.genre_s = genre_s;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getPages_i() {
        return pages_i;
    }

    public void setPages_i(long pages_i) {
        this.pages_i = pages_i;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private long sequence_i;
    private String genre_s;
    private boolean inStock;
    private float price;
    private long pages_i;
    private String link;
}
