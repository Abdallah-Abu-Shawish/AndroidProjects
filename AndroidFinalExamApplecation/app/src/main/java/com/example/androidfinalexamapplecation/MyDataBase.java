package com.example.androidfinalexamapplecation;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.androidfinalexamapplecation.noteapp.Note;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MyDataBase extends SQLiteOpenHelper {


    public final static String DB_NAME = "UserData";
    public final static int DB_VERSION = 1;
    public final static String TABLE_USER = "user";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_EMAIL = "email";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_SPECIALIZE = "specialize";
    public final static String COLUMN_GENDER = "gender";
    public final static String COLUMN_IMAGE = "image";


    public final static String TABLE_NOTE = "noteTable";
    public final static String COLUMN_NOTE_ID = "noteID";
    public final static String COLUMN_NOTE_TITLE = "noteTitle";
    public final static String COLUMN_NOTE_TEXT = "noteText";
    public final static String COLUMN_NOTE_DATE = "noteDate";
    public final static String COLUMN_NOTE_IMAGE = "noteImage";
    public final static String COLUMN_NOTE_TIME = "noteTime";


    //سجل دخول وعمل حساب وتمام
    public final static String TABLE_PRODUCT = "product";
    public final static String COLUMN_ID_PRODUCT = "idProduct";
    public final static String COLUMN_NAME_PRODUCT = "nameProduct";
    public final static String COLUMN_PRICE = "priceProduct";
    public final static String COLUMN_TYPE_PRODUCT = "typeProduct";
    public final static String COLUMN_QUNTITY= "quntityProduct";
    public final static String COLUMN_PRODUCT_IMAGE = "imageProduct";
    public final static String COLUMN_DESCRIPTION = "description";




    public final static String TABLE_PRODUCT2 = "product2";
    public final static String COLUMN_ID_PRODUCT2 = "idProduct2";
    public final static String COLUMN_NAME_PRODUCT2 = "nameProduct2";
    public final static String COLUMN_PRICE2 = "priceProduct2";
    public final static String COLUMN_TYPE_PRODUCT2 = "typeProduct2";
    public final static String COLUMN_QUNTITY2= "quntityProduct2";
    public final static String COLUMN_PRODUCT_IMAGE2 = "imageProduct2";
    public final static String COLUMN_DESCRIPTION2 = "description2";
    public final static String COLUMN_DESCRIPTION2User = "description2User";


    public final static String TABLE_SHOW_USER = "showUser";
    public final static String COLUMN_ID_SHOW = "idShow";
    public final static String COLUMN_USERNAME_SHOW = "showUsername";
    public final static String COLUMN_PASSWORD_SHOW = "showPassword";

    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table_user = " create table "+ TABLE_USER + " ( "+
                COLUMN_ID+" integer primary key autoincrement ,"+
                COLUMN_NAME +" text not null , "+
                COLUMN_EMAIL +" text not null , "+
                COLUMN_PASSWORD +" text not null , "+
                COLUMN_IMAGE + " blob , "+
                COLUMN_SPECIALIZE+" text not null, "+
                COLUMN_GENDER +" text not null " + " ) ";

        String table_note = "create table "+TABLE_NOTE+" ( "+
                COLUMN_NOTE_ID+" integer primary key autoincrement ,"+
                COLUMN_NOTE_TITLE+" text not null ,"+
                COLUMN_NOTE_TEXT+" text not null ,"+
                COLUMN_NOTE_DATE+" text not null ,"+
                COLUMN_NOTE_IMAGE + " blob , "+
                COLUMN_NOTE_TIME+" text not null "+" ) ";


        sqLiteDatabase.execSQL(table_user);
        sqLiteDatabase.execSQL(table_note);
    }









    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabaseUpgrade, int oldVersion, int newVersion) {

        sqLiteDatabaseUpgrade.execSQL("drop table if exists "+TABLE_USER );
        sqLiteDatabaseUpgrade.execSQL("drop table if exists "+TABLE_NOTE);
        onCreate(sqLiteDatabaseUpgrade);
    }

    public boolean insertUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_SPECIALIZE,user.getSpecalize());
        values.put(COLUMN_GENDER,user.getGender());
        values.put(COLUMN_IMAGE,getByteArray(user.getImage()));
        long result = database.insert(TABLE_USER,null,values);
        return result != 0;
    }



    public Cursor getUsers(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("select * from "+TABLE_USER,null);
    }

    public boolean updateUser(User user,int id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_SPECIALIZE,user.getSpecalize());
        values.put(COLUMN_GENDER,user.getGender());
        values.put(COLUMN_IMAGE,getByteArray(user.getImage()));
        String[] args = {id+""};
        int result = database.update(TABLE_USER,values,COLUMN_ID+" =? ",args);
        return result != 0;
    }

    public boolean updateUserPassword(String newPassword,int id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD,newPassword);
        String[] args = {id+""};
        int result = database.update(TABLE_USER,values,COLUMN_ID+" =? ",args);
        return result != 0;
    }


    public boolean deleteUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] args = {user.getId()+""};
        int result = database.delete(TABLE_USER,COLUMN_ID+" =? ",args);
        return result > 0;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_USER,null);
        ArrayList<User> userList = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            user.setSpecalize(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALIZE)));
            user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(MyDataBase.COLUMN_IMAGE));
            user.setImage(getBitmap(bytes));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }


    @SuppressLint("Range")
    public User getUserByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        Cursor cursor = db.rawQuery("select * from "+TABLE_USER +" where "+COLUMN_ID +" = "+id,null);
        while (cursor.moveToNext()){
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
        }

        cursor.close();
        return user;
    }


    @SuppressLint("Range")
    public int getUserID(String email,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = 0;
        Cursor cursor = db.rawQuery("select "+COLUMN_ID+" from "+TABLE_USER +" where "+COLUMN_EMAIL +" = "+email+" and "+COLUMN_PASSWORD+" = "+password,null);
        while (cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }
        cursor.close();
        return id;
    }



    public boolean insertNote(Note note){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE,note.getTitle());
        values.put(COLUMN_NOTE_TEXT,note.getText());
        values.put(COLUMN_NOTE_DATE,note.getDate());
        values.put(COLUMN_NOTE_TIME,note.getTime());
        values.put(COLUMN_IMAGE,getByteArray(note.getBitmap()));
        long result = database.insert(TABLE_NOTE,null,values);
        return result != 0;
    }



    public Cursor getNotes(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("select * from "+TABLE_NOTE,null);
    }

    public boolean updateNote(Note note){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE,note.getTitle());
        values.put(COLUMN_NOTE_TEXT,note.getText());
        values.put(COLUMN_NOTE_DATE,note.getDate());
        values.put(COLUMN_NOTE_TIME,note.getTime());
        String[] args = {note.getId()+""};
        int result = database.update(TABLE_NOTE,values,COLUMN_NOTE_ID+" =? ",args);
        return result != 0;
    }


    public boolean deleteNote(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] args = {id+""};
        int result = database.delete(TABLE_NOTE,COLUMN_NOTE_ID+" =? ",args);
        return result > 0;
    }

    @SuppressLint("Range")
    public ArrayList<Note> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NOTE,null);
        ArrayList<Note> notesList = new ArrayList<>();
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_NOTE_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
            note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DATE)));
            note.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIME)));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(MyDataBase.COLUMN_NOTE_IMAGE));
            note.setBitmap(getBitmap(bytes));
            notesList.add(note);
        }
        cursor.close();
        return notesList;
    }




    @SuppressLint("Range")
    public ArrayList<Note> searchForNote(String title){
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();
        String[] args = {title};
        Cursor cursor = database.rawQuery("select * from "+TABLE_NOTE + " where "+COLUMN_NOTE_TITLE + "=?",args);
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
            note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_DATE)));
            note.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TIME)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    @SuppressLint("Range")
    public Note getNoteByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Note note = new Note();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NOTE +" where "+COLUMN_NOTE_ID +" = "+id,null);
        while (cursor.moveToNext()){
            note.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TITLE)));
            note.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_TEXT)));
        }

        cursor.close();
        return note;
    }




//    public boolean insertProduct(Product product){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_PRODUCT,product.getName());
//        values.put(COLUMN_PRICE,product.getPrice());
//        values.put(COLUMN_TYPE_PRODUCT,product.getType());
//        values.put(COLUMN_QUNTITY,product.getQuntity());
//        values.put(COLUMN_DESCRIPTION,product.getDescription());
//        values.put(COLUMN_PRODUCT_IMAGE,getByteArray(product.getProductImage()));
//        long result = database.insert(TABLE_PRODUCT,null,values);
//        return result != -1;
//    }
//
//    public Cursor getProduct(){
//        SQLiteDatabase database = this.getReadableDatabase();
//        return database.rawQuery("select * from "+TABLE_PRODUCT,null);
//    }
//
//    public boolean updateProduct(Product product,int id){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_PRODUCT,product.getName());
//        values.put(COLUMN_PRICE,product.getPrice());
//        values.put(COLUMN_TYPE_PRODUCT,product.getType());
//        values.put(COLUMN_QUNTITY,product.getQuntity());
//        values.put(COLUMN_DESCRIPTION,product.getDescription());
//        values.put(COLUMN_PRODUCT_IMAGE,getByteArray(product.getProductImage()));
//        String[] args = {id+""};
//        int result = database.update(TABLE_PRODUCT,values,COLUMN_ID_PRODUCT+" =? ",args);
//        return result > 0;
//    }
//
//
//    public boolean deleteProduct(int id){
//        SQLiteDatabase database = this.getWritableDatabase();
//        String[] args = {id+""};
//        int result = database.delete(TABLE_PRODUCT,COLUMN_ID_PRODUCT+" =? ",args);
//        return result > 0;
//    }
//
//
//    @SuppressLint("Range")
//    public ArrayList<Product> getAllProducts(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+TABLE_PRODUCT,null);
//        ArrayList<Product> productList = new ArrayList<>();
//        while (cursor.moveToNext()){
//            Product product = new Product();
//            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT)));
//            product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_PRODUCT)));
//            product.setQuntity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUNTITY)));
//            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
//            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE));
//            product.setProductImage(getBitmap(bytes));
//            productList.add(product);
//        }
//        cursor.close();
//        return productList;
//    }
//
//    public Product getProductByID(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Product product = new Product();
//        Cursor cursor = db.rawQuery("select * from "+TABLE_PRODUCT +" where "+COLUMN_ID_PRODUCT +" = "+id,null);
//        while (cursor.moveToNext()){
//
//            product.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRODUCT)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
//            product.setType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE_PRODUCT)));
//            product.setQuntity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUNTITY)));
//            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
//            byte[] bytes = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
//            product.setProductImage(getBitmap(bytes));
//        }
//
//        cursor.close();
//        return product;
//    }
//
//    public boolean deleteProductCart(int id,String user){
//        SQLiteDatabase database = this.getWritableDatabase();
//        int result = database.delete(TABLE_PRODUCT2,COLUMN_ID_PRODUCT2+" = "+ id +" and "+COLUMN_DESCRIPTION2User +" = "+user,null);
//        return result != 0;
//    }
//    @SuppressLint("Range")
//    public ArrayList<Product> searchProduct(String pName){
//        SQLiteDatabase database = this.getReadableDatabase();
//        ArrayList<Product> products = new ArrayList<>();
//        String[] args = {pName};
//        Cursor cursor = database.rawQuery("select * from "+TABLE_PRODUCT + " where "+COLUMN_NAME_PRODUCT + "=?",args);
//        while (cursor.moveToNext()){
//            Product product = new Product();
//            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_PRODUCT)));
//            product.setProductImage(getBitmap(cursor.getBlob(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE))));
//            product.setQuntity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUNTITY)));
//            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
//            products.add(product);
//        }
//        cursor.close();
//        return products;
//    }
//
//
//
//    public boolean insertProductCart(Product2 product){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_PRODUCT2,product.getName());
//        values.put(COLUMN_PRICE2,product.getPrice());
//        values.put(COLUMN_TYPE_PRODUCT2,product.getType());
//        values.put(COLUMN_QUNTITY2,product.getQuntity());
//        values.put(COLUMN_DESCRIPTION2,product.getDescription());
//        values.put(COLUMN_DESCRIPTION2User,product.getDescription());
//        values.put(COLUMN_PRODUCT_IMAGE2,getByteArray(product.getProductImage()));
//        long result = database.insert(TABLE_PRODUCT2,null,values);
//        return result != -1;
//    }
//
//    @SuppressLint("Range")
//    public ArrayList<Product2> getAllProductsCART(String username){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] condtin={username+""};
//        Cursor cursor = db.rawQuery("select * from "+TABLE_PRODUCT2 +" where "+COLUMN_DESCRIPTION2User +" =? ",condtin);
//        ArrayList<Product2> productList = new ArrayList<>();
//        while (cursor.moveToNext()){
//            Product2 product = new Product2();
//            product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT2)));
//            product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT2)));
//            product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE2)));
//            product.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_PRODUCT2)));
//            product.setQuntity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUNTITY2)));
//            product.setDescriptionUser(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION2User)));
//            product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION2)));
//            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE2));
//            product.setProductImage(getBitmap(bytes));
//            productList.add(product);
//        }
//        cursor.close();
//        return productList;
//    }
//
//
//
//
//    public boolean AddUserTemp(String username,String password){
//
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(COLUMN_USERNAME_SHOW,username);
//        values.put(COLUMN_PASSWORD_SHOW,password);
//        values.put(COLUMN_ID_SHOW,"1");
//
//        long tempId = db.insert(TABLE_SHOW_USER, null, values);
//
//        db.close();
//        return tempId!=0;
//
//
//    }
//
//
//    public Cursor getUserTempContent(String username , String password){
//        SQLiteDatabase database = this.getReadableDatabase();
//        return database.rawQuery("select * from "+TABLE_PRODUCT+" where "+
//                COLUMN_USERNAME_SHOW+" = "+username +" and "+COLUMN_PASSWORD_SHOW+" = "+password,null);
//    }
//
//
//    @SuppressLint("Range")
//    public String getUserTemp() {
//        SQLiteDatabase db = getReadableDatabase();
//        String username="";
//        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * from " + TABLE_SHOW_USER,null);
//
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//
//                username=cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME_SHOW));
//
//
//                cursor.moveToNext();
//
//            }
//        }
//        cursor.close();
//        return username ;
//    }
//
//
//
//    public boolean updateUserTemp(String newUsername,String newPassword) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USERNAME_SHOW,newUsername);
//        values.put(COLUMN_PASSWORD_SHOW,newPassword);
//        String[] array = { 1+""};
//        long result = sqLiteDatabase.update(TABLE_SHOW_USER,values, " " +COLUMN_ID_SHOW + "=? ",array);
//
//        return result>0;
//
//    }

//    public boolean insertShowUser(User user){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PASSWORD_SHOW,user.getPassword());
//        values.put(COLUMN_USERNAME_SHOW,user.getUserName());
//        long result = database.insert(TABLE_SHOW_USER,null,values);
//        return result != -1;
//    }
//
//    public Cursor getShowUsers(){
//        SQLiteDatabase database = this.getReadableDatabase();
//        return database.rawQuery("select * from "+TABLE_SHOW_USER,null);
//    }
//
//    public boolean updateShowUser(User user){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_ID_SHOW,user.getId());
//        values.put(COLUMN_PASSWORD_SHOW,user.getPassword());
//        values.put(COLUMN_USERNAME_SHOW,user.getUserName());
//        String[] args = {user.getId()+""};
//        int result = database.update(TABLE_SHOW_USER,values,"id=?",args);
//        return result > 0;
//    }



    private byte[] getByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    private Bitmap getBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}