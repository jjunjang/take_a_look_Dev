package info.androidhive.materialtabs.db;


import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{
        public static final String TCONST = "tconst";
        public static final String GENRES = "genres";
        public static final String EMONAME = "emoName";
        public static final String EMOSCORE = "emoScore";

        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +TCONST+" text not null , "
                +GENRES+" text not null , "
                +EMONAME+" integer not null , "
                +EMOSCORE+" text not null );";
    }
}