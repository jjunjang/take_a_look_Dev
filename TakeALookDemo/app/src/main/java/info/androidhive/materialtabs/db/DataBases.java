package info.androidhive.materialtabs.db;


import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{
        public static final String _ID = "id";
        public static final String TCONST = "tconst";
        public static final String TITLEKOR = "titleKor";
        public static final String GENRES = "genres";
        public static final String AVERRATINGS = "averRatings";
        public static final String NUMVOTES = "numVotes";
        public static final String EMONAME = "emoName";
        public static final String EMOSCORE = "emoScore";
        public static final String STARTYEAR = "startYear";
        public static final String RUNTIMEMIN = "runtimeMin";

        public static final String LISTTYPE = "listType";

        public static final String _TABLENAME0 = "basic_titles";
        public static final String _TABLENAME1 = "mylist";

        public static final String _CREATE0 = "CREATE TABLE IF NOT EXISTS "+_TABLENAME0+" ("
                +_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TCONST+" VARCHAR(10) not null , "
                +TITLEKOR+" VARCHAR(415) not null , "
                +GENRES+" VARCHAR(50) not null , "
                +AVERRATINGS+" FLOAT not null , "
                +NUMVOTES+" INTEGER not null , "
                +EMONAME+" VARCHAR(50) not null , "
                +EMOSCORE+" FLOAT not null , "
                +STARTYEAR+" INTEGER not null ,"
                +RUNTIMEMIN+" INTEGER not null );";

        public static final String _CREATE1 = "CREATE TABLE IF NOT EXISTS "+_TABLENAME1+" ("
                +_ID+" INTEGER primary key autoincrement, "
                +TCONST+" VARCHAR(10) not null , "
                +TITLEKOR+" VARCHAR(415) not null , "
                +LISTTYPE+" VARCHAR(8) not null );";
    }
}