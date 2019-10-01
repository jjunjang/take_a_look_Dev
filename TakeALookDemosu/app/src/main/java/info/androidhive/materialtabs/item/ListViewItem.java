package info.androidhive.materialtabs.item;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable movieImg ;
    private String tconst ;
    private String titleKor ;
    private String averageRating ;
    private String numVotes ;

    public Drawable getMovieImg() {
        return movieImg;
    }

    public String getTconst() {
        return tconst;
    }

    public String getTitleKor() {
        return titleKor;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getNumVotes() {
        return numVotes;
    }

    public void setMovieImg(Drawable movieImg) {
        this.movieImg = movieImg;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public void setTitleKor(String titleKor) {
        this.titleKor = titleKor;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public void setNumVotes(String numVotes) {
        this.numVotes = numVotes;
    }

//    public ListViewItem(Drawable movieImg, String tconst, String titleKor,
//                     String averageRating, String numVotes) {
//        this.movieImg = movieImg;
//        this.tconst = tconst;
//        this.titleKor = titleKor;
//        this.averageRating = averageRating;
//        this.numVotes = numVotes;
//    }
}