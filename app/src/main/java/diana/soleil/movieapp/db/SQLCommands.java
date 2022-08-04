package diana.soleil.movieapp.db;

public class SQLCommands {

    final public static String DATABASE_NAME       = "moviedb";
    final public static String SCHEMA_VERSION      = "1";

    final public static String TABLE_NAME          = "movietable";
    final public static String COLUMN_ID           = "id";

    final public static String COLUMN_TITLE     = "title";
    final public static String COLUMN_IMAGE_WITH_TITLE         = "image_with_title";
    final public static String COLUMN_IMAGE_WITHOUT_TITLE = "image_without_title";
    final public static String COLUMN_RATING        = "rating";
    final public static String COLUMN_GENRE        = "genre";
    final public static String COLUMN_LANGUAGE        = "language";
    final public static String COLUMN_DESCRIPTION        = "description";
    final public static String COLUMN_RELEASE_DATE        = "release_date";

    final public static String[] TABLE_COLUMNS     = {"id", "title", "image_with_title", "language","description","release_date"};
    final public static String CREATE_TABLE        =
            "CREATE TABLE movietable (id INTEGER, title TEXT, image_with_title TEXT, language TEXT, description TEXT, release_date TEXT);";
}
