package com.example.animalpet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "AnimalPet.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id INTEGER primary key autoincrement, username TEXT, password TEXT )");
        MyDB.execSQL("create Table pets(pet_id INTEGER primary key autoincrement, petname TEXT, petcolor TEXT, petorigin TEXT, pettype TEXT, id INTEGER, foreign key(id) references users(id))");
        MyDB.execSQL("create Table pethealth(health_id INTEGER primary key autoincrement, vaccinationstatus TEXT, rabiesdue TEXT, healthscheme TEXT, pet_id INTEGER, foreign key(pet_id) references pets(pet_id))");
        MyDB.execSQL("create Table vet(vet_id INTEGER primary key autoincrement, vetname TEXT, address TEXT, treatments TEXT, vetrecomendation TEXT, health_id INTEGER, foreign key(health_id) references pethealth(health_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists pets");
        MyDB.execSQL("drop Table if exists pethealth");
        MyDB.execSQL("drop Table if exists vet");
    }

    //inserting into users table
    public Boolean insertUserData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("username", username);
        values1.put("password", password);
        long results = MyDB.insert("users", null, values1);
        return true;
    }


    //inserting into pet table
    public Boolean insertPetData(String petname, String petcolor, String pettype, String petorigin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("petname", petname);
        values2.put("petcolor", petcolor);
        values2.put("petorigin", petorigin);
        values2.put("pettype", pettype);
        long results2 = MyDB.insert("pets", null, values2);
        return true;
    }

    //inserting into pethealth table
    public Boolean insertHealthData(String vaccinationstatus, String rabiesdue, String healthscheme) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        values3.put("vaccinationstatus", vaccinationstatus);
        values3.put("rabiesdue", rabiesdue);
        values3.put("healthscheme", healthscheme);
        long results3 = MyDB.insert("pethealth", null, values3);
        return true;
    }

    //inserting into vets table
    public Boolean insertVetData(String petname, String vetname, String address, String treatments, String vetrecomendation) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values4 = new ContentValues();
        values4.put("vetname", vetname);
        values4.put("address", address);
        values4.put("treatments", treatments);
        values4.put("vetrecomendation", vetrecomendation);
        Cursor cursor = MyDB.rawQuery("Select vetname,address,treatments,vetrecomendation from vet,pets where petname = ? ", new String[]{petname});
        if (cursor.getCount() > 0){
            long r = MyDB.update("pets", values4, "petname= ?", new String[]{petname});
            if (r == -1) {
                return false;
            } else
                return true;
        }
        else
            return false;
    }



    public boolean deletePetInfo(String petname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from pets where petname = ? ", new String[]{petname});
        if (cursor.getCount() > 0){
            long r = MyDB.delete("pets", "petname = ?", new String[]{petname});
            if(r == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }
    /**
     public boolean DeletepetData(String petname){
     SQLiteDatabase MyDB = this.getWritableDatabase();
     Cursor cursor = MyDB.rawQuery("Select * " +
     "from pets, pethealth, vet " +
     "where pets.pet_id = pethealth.health_id " +
     "and pethealth.health_id = vet.vet_id",null);
     if (cursor.getCount() > 0){
     long r = MyDB.delete("pets,pethealth,vet", "petname = ?", new String[]{petname});
     if (r == -1) return false;
     else
     return true;
     }
     else
     return false;
     }
     */

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkpetname(String petname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from pets where petname = ?", new String[]{petname});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor ViewPetData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * " +
                "from pets " ,null);
        return cursor;
    }

    public Cursor ViewUsername(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select username " +
                "from users " ,null);
        return cursor;
    }

    public Boolean updatePetData(String petname, String petcolor, String petorigin, String pettype, String petdate) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("petname", petname);
        values2.put("petcolor", petcolor);
        values2.put("petorigin", petorigin);
        values2.put("pettype", pettype);
        Cursor cursor = MyDB.rawQuery("Select * from pets where petname = ? ", new String[]{petname});
        if (cursor.getCount() > 0){
            long r = MyDB.update("pets", values2, "petname= ?", new String[]{petname});
            if (r == -1) {
                return false;
            } else
                return true;
        }
        else
            return false;
    }

    public Boolean updateVetData(String vetname, String vetaddress, String vettreatments, String vetrecomendation) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values4 = new ContentValues();
        values4.put("vetname", vetname);
        values4.put("address", vetaddress);
        values4.put("treatments", vettreatments);
        values4.put("vetrecomendation", vetrecomendation);
        Cursor cursor = MyDB.rawQuery("Select * from vet where vetname = ? ", new String[]{vetname});
        if (cursor.getCount() > 0){
            long r = MyDB.update("vet", values4, "vetname= ?", new String[]{vetname});
            if (r == -1) {
                return false;
            } else
                    return true;
            }
                else
                    return false;
        }
    }



    /*
     //query for changing or modifying all entries with the same id affecting all tables
     public Cursor ViewIndividualPetData(){
     SQLiteDatabase MyDB = this.getReadableDatabase();
     Cursor cursor = MyDB.rawQuery("Select * " +
     "from pets, pethealth, vet " +
     "where pets.pet_id = pethealth.health_id " +
     "and pethealth.health_id = vet.vet_id",null);
     return cursor;
     }
     */
