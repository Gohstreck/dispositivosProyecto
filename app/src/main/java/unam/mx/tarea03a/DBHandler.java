package unam.mx.tarea03a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "appdb";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "user";
    private static final String ID_USER = "id";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String GENRE = "genre";
    private static final String SQUATS = "squats";
    private static final String PUSH_UPS = "push_ups";
    private static final String CRUNCHES ="crunches";
    private static final String IMC = "imc";
    private static final String HR = "hr";
    private static final String[] COLS = new String[]{
            ID_USER, NAME, PASSWORD, GENRE, SQUATS, PUSH_UPS, CRUNCHES, IMC, HR};

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PASSWORD + " TEXT, "
                + GENRE + " TINYINT, "
                + SQUATS + " TINYINT, "
                + PUSH_UPS + " TINYINT, "
                + CRUNCHES +  " TINYINT, "
                + IMC + " REAL, "
                + HR + " TINYINT); ";
        sqLiteDatabase.execSQL(query);
    }

    public void addNewUser(String name, String password, int genre, int squats,
                              int pushUps, int crunches, float imc,
                           int hr){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PASSWORD, Encoder.encode(password));
        values.put(GENRE, genre);
        values.put(SQUATS, squats);
        values.put(PUSH_UPS, pushUps);
        values.put(CRUNCHES, crunches);
        values.put(IMC, imc);
        values.put(HR, hr);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public int updateUser(String name, String password, int genre, int squats, int pushUps,
                           int crunches, float imc, int hr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name );
        values.put(PASSWORD, password);
        values.put(GENRE, genre);
        values.put(SQUATS, squats);
        values.put(PUSH_UPS, pushUps);
        values.put(CRUNCHES, crunches);
        values.put(IMC, imc);
        values.put(HR, hr);

        String whereClause = NAME + " =?" + " AND " + PASSWORD + " =?";
        String[] whereClauseParams = new String[]{ name, Encoder.encode(password) };
        return db.update(TABLE_NAME, values, whereClause, whereClauseParams);
    }
    public int updateUser(String name, int genre, int squats, int pushUps,
                          int crunches, float imc, int hr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name );

        values.put(GENRE, genre);
        values.put(SQUATS, squats);
        values.put(PUSH_UPS, pushUps);
        values.put(CRUNCHES, crunches);
        values.put(IMC, imc);
        values.put(HR, hr);

        String whereClause = NAME + " =?" ;
        String[] whereClauseParams = new String[]{ name};
        return db.update(TABLE_NAME, values, whereClause, whereClauseParams);
    }

    public User getUser(String name, String password){
        Cursor c = null;
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String selection = NAME + " =? AND " + PASSWORD + " = ?";
            String[] sargs = new String[]{name, Encoder.encode(password)};

            c = db.query( TABLE_NAME, COLS,selection, sargs, null, null, null);
            c.moveToFirst();

            if (c.getCount() > 0){

                user.setId(c.getInt(0));
                user.setName(c.getString(1));
                user.setPassword(c.getString(2));
                user.setGenre(c.getInt(3));
                user.setSquats(c.getInt(4));
                user.setPushUps(c.getInt(5));
                user.setCrunches(c.getInt(6));
                user.setImc(c.getFloat(7));
                user.setHearRate(c.getInt(8));
                return user;
            }else{
                return null;
            }
        }catch (SQLException e) {
            Log.d("SQLERROR", "Error al obtener el usuario" + name);
        }finally {
            if (c!=null && !c.isClosed()) c.close();

        }

        return null;
    }

    public boolean userExists(String name){
        Cursor c = null;

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String selection = NAME + " =? ";
            String[] sargs = new String[]{name};
            c = db.query( TABLE_NAME, COLS,selection, sargs, null, null, null);

            Log.d("USER_COUNT", String.valueOf(c.getCount()));
            return !(c.getCount() == 0);

        }catch (SQLException e) {
            Log.d("SQLERROR", "Error al obtener el usuario" + name);
        }finally {
            if (c!=null && !c.isClosed()) c.close();
        }
        return true;

    }

    public void clean(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME + "=?", new String[]{name});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
