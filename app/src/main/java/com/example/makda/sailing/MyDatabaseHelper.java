package com.example.makda.sailing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Database";

    // Table name: Note.
    private static final String TABLE_NOTE = "Note";

    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_TITLE ="Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_NOTE_TITLE + " TEXT,"
                + COLUMN_NOTE_CONTENT + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);

        // Create tables again
        onCreate(db);
    }


//    // If Note table has no data
//    // default, Insert 2 records.
//    public void createDefaultNotesIfNeed()  {
//        int count = this.getNotesCount();
//        if(count ==0 ) {
//            Note note1 = new Note("Firstly see Android ListView",
//                    "See Android ListView Example in o7planning.org");
//            Note note2 = new Note("Learning Android SQLite",
//                    "See Android SQLite Example in o7planning.org");
//            this.addNote(note1);
//            this.addNote(note2);
//        }
//    }

//
//    public void addNote(Note note) {
//        Log.i(TAG, "MyDatabaseHelper.addNote ... " + note.getNoteTitle());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
//        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
//
//        // Inserting Row
//        db.insert(TABLE_NOTE, null, values);
//
//        // Closing database connection
//        db.close();
//    }

    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

//
//    public int updateNote(Note note) {
//        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
//        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
//
//        // updating row
//        return db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
//                new String[]{String.valueOf(note.getNoteId())});
//    }
//
//    public void deleteNote(Note note) {
//        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle() );
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NOTE, COLUMN_NOTE_ID + " = ?",
//                new String[] { String.valueOf(note.getNoteId()) });
//        db.close();
//    }

}