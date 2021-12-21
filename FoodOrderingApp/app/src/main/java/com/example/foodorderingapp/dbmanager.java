package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class dbmanager extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "foodapp";

    //Table - Food
    public static final String FOOD_TABLE = "cuisines";
    public static final String FOOD_ID = "id";
    public static final String FOOD_NAME = "name";
    public static final String FOOD_DETAILS = "details";
    public static final String FOOD_IMG = "imgid";
    public static final String FOOD_PRICE = "price";
    public static final String FOOD_TYPE = "type";

    //Table - Users
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_CONTACT = "contact";
    public static final String USER_ADDRESS = "address";
    public static final String USER_DATE = "orderDate";
    public static final String USER_DISH = "dish";

    Context mContext;

    public dbmanager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }
    private ArrayList<Food> retrieve_food(){
        ArrayList<Food> foodlist = new ArrayList<>();

        foodlist.add(new Food("Žrnovski Makaruni (Pasta)", "These homemade tubular pasta, Žrnovski Makaruni, are a traditional recipe from a small town on Korčula, one of Croatia’s southern islands. Hand rolling these noodles takes a bit of time and is a great activity to get the whole family involved in. ", R.drawable.pasta, 249, "veg"));
        foodlist.add(new Food("Authentic Saag Paneer", "Saag paneer is a popular Indian vegetarian dish made of spinach and other leafy greens (called saag) and paneer (Indian cottage cheese). The greens are ground into a paste, and seasoned with spices. Fried paneer is then added on top. The origin of this dish is from the province of Punjab in northern India.", R.drawable.paneer, 149, "veg"));
        foodlist.add(new Food("Vegetable Tehri", "Vegetable tehri (also called tehari and tahari) is a one pot vegetable and rice dish from North India. It is super simple to make and can be made in less than 45 minutes, making it a perfect meal for a quick weeknight dinner or if unexpected guests drop by. This recipe is also vegan, dairy free and gluten free.", R.drawable.tehri, 199, "veg"));
        foodlist.add(new Food("Kimbap (Korean Sushi)", "Kimbap (or gimbap) could be called Korean sushi, and if you are a fan of sushi, you are going to love the light and slightly nutty flavor of these rolls. They’re the perfect recipe for summer picnics and lunches! ", R.drawable.sushi, 299, "veg"));
        foodlist.add(new Food("Baked Falafel", "Baked Falafel gives you all the healthy, nutty falafel goodness without the hassle or added fat of frying, and it’s just as good for stuffing into a pita!", R.drawable.falafal, 249, "veg"));
        foodlist.add(new Food("Authentic Fettuccine Alfredo", "Fettuccine Alfredo is a dish that Americans think of in their list of “classic Italian dishes” right along with Spaghetti and Meatballs and Ravioli. But, the dish that is known to Americans is not quite the authentic Italian version. Fettuccine Alfredo started off as the desperate attempt of Roman restaurant owner, Alfredo di Lelio, to find something that his pregnant wife, who had lost most of her appetite due to pregnancy, would be able to eat. So, he added some Parmesan cheese to an everyday Italian dish, fettuccine al burro (Fedduccine with Butter), and she couldn’t stop eating it!", R.drawable.fetuccin, 499, "veg"));
        foodlist.add(new Food("Vegetable Lo Mein", "Lo mein is a Chinese noodle dish that you have probably seen at your local takeout restaurant. The Cantonese word lo mein translates to stirred noodles. So, at its most basic, this is a dish of noodles that are stirred together with vegetables (and sometimes a protein). Authentic lo mein has only a thin sauce, not the thick, heavy sauce you will find in American takeout versions.", R.drawable.lomein, 199, "veg"));
        foodlist.add(new Food("Vegetable Fried Rice", "Think of fried rice as a leftovers dish. It’s the easiest way we know to transform leftover rice, veggies, and meat (if you so desire) into a completely new creation. The leftover part is actually quite important in this dish. I’m sure you’ve noticed how leftover rice can get a bit dry. Right? It’s that dryness that we want for fried rice. Freshly cooked rice has too much moisture to fry properly, it will just continue to steam. Meaning, you’ll have a tasty rice saute, but not that nice and slightly crisp fried rice feel.", R.drawable.friedrice, 249, "veg"));
        foodlist.add(new Food("Ravioli", "Ravioli is an Italian dumpling that's typically stuffed with ricotta, meat, cheese, and vegetables. The filling is then sealed between two thin layers of egg pasta dough and typically served with a delicious tomato or cream-based pasta sauce.", R.drawable.ravioli, 199, "veg"));
        foodlist.add(new Food("Beet Ravioli", "Slightly sweet and brightly colored, our beet ravioli is filled with an herbed mushroom filling and topped with a sauteed mushroom sauce.", R.drawable.beetravioi, 199, "veg"));
        foodlist.add(new Food("Grilled Chicken Escalope with Fresh Salsa", "Chicken marinated in home-made spice powder and green paste. Grilled to perfection and served with a fresh salsa of grapes, spring onion and cherry tomatoes.", R.drawable.grilledchicken, 1000, "nonveg"));
        foodlist.add(new Food("Mutton Korma", "A flavourful mutton curry, where the meat is stirred with curd, garlic-ginger paste, cloves, cardamom and cinnamon sticks.", R.drawable.muttonkorma, 499, "nonveg"));
        foodlist.add(new Food("Pina Colada Pork Ribs", "The ingredients of the popular rum-based cocktail team up with pork ribs to create a lip-smacking treat. Pork is slow roasted to soak in the flavours, and the kick of ginger gives it an interesting edge.", R.drawable.porkribs, 1000, "nonveg"));
        foodlist.add(new Food("Tandoori Lamb Chops", "Lamb chops marinated in strained yogurt and flavoursome masalas. Cooked till tender, this dish is guaranteed to impress.", R.drawable.lamb, 399, "nonveg"));
        foodlist.add(new Food("Malabar Fish Biryani", "This classic Malabar Fish Biryani can be devoured at all times. Enjoy the delicious taste of this ever-charming dish.", R.drawable.fishbiriyani, 249, "nonveg"));
        foodlist.add(new Food("Keema Samosa with Yoghurt Dip", "Make this ultimate Punjabi snack from scratch. Dough pockets stuffed with keema masala mixture, fried golden and served with a refreshing hung curd dip.", R.drawable.samosa, 149, "nonveg"));
        foodlist.add(new Food("Chicken 65", "Chicken 65 is said to have originated in Madras (Chennai). This delicious, deep-fried recipe of Chicken 65 is from the house of Tamil Nadu. A popular and easy-to-make snack recipe, fried, full of spice with the flavours of ginger, garlic and chillies.", R.drawable.chicken65, 349, "nonveg"));
        foodlist.add(new Food("Goan Prawn Curry With Raw Mango", "Luscious prawns bathed in a burst of flavours to cook up a brilliant Goan delicacy, enjoy! A perfect seafood, winter recipe that you can pair with rice. ", R.drawable.prawns, 299, "nonveg"));
        foodlist.add(new Food("Butter Chicken", "Keeping the classic at the last! Butter chicken is one-of-a-kind recipe that's been passed down from one generation to another and holds a permanent spot on India's menu. This recipe of Butter Chicken from Moti Mahal is easy to follow and makes for an instant conversation-starter!", R.drawable.butterchicken, 249, "nonveg"));
        foodlist.add(new Food("Curried Parmesan Fish Fingers", "Tender fish pieces are cut into pieces, wrapped in batter and fried to perfection. Team this with ketchup, mayo or mustard sauce and you're sorted for the evening.", R.drawable.fishfingers, 149, "nonveg"));
        foodlist.add(new Food("Meat & Mushroom Lasagna", "When u want to keep trying something new but due to all the experimenting, many favourite dishes don’t make a comeback for a long time. It makes such an impressive meal with a balance of sauces with the pasta sheets not overpowering the flavour of the lasagna.", R.drawable.lasagna, 248, "nonveg"));
        foodlist.add(new Food("Lobster Roll", "A lobster roll consists of chunks of tender, sweet, cooked lobster meat barely napped in a thin coating of mayonnaise, all stuffed into a top-split, white-bread hot dog bun lightly toasted in butter.", R.drawable.lobsterroll, 499, "nonveg"));
        foodlist.add(new Food("Tonkotsu Ramen", "Tonkotsu Ramen is a type of Japanese noodle dish known of its collagen rich, milky coloured flavourful pork based broth. Traditionally it is prepared by boiling pork bones for a long period of time which can last up to 12 hours or more. This type of ramen is particularly popular around Kyushu where it also believed to have originated.", R.drawable.ramen, 148, "nonveg"));
        foodlist.add(new Food("BBQ Ribs", "Pork ribs are among the most popular barbecue items to cook and the one that gives many people the most trouble. While barbecue ribs are especially popular in the American south, their appeal for meat-lovers is universal. Succulent, spicy, and smoky, barbecue ribs are surprisingly easy to cook.", R.drawable.bbqribs, 199, "nonveg"));
        foodlist.add(new Food("London Broil Steak","No one knows where the “London” comes from in the name since this is a decidedly American dish. But it has become such a popular cooking method that many butchers simply use the term “London broil” for flank steak since the method has become nearly synonymous with that cut of meat.", R.drawable.steak, 220, "nonveg"));
        foodlist.add(new Food("Chinese Peking Duck", "Peking duck is a dish from Beijing that has been prepared since the imperial era. The meat is characterized by its thin, crisp skin, with authentic versions of the dish serving mostly the skin and little meat, sliced in front of the diners by the cook. Ducks bred specially for the dish are slaughtered after 65 days and seasoned before being roasted in a closed or hung oven. The meat is eaten with spring onion, cucumber and sweet bean sauce with pancakes rolled around the fillings. Sometimes pickled radish is also inside, and other sauces (like hoisin sauce) can be used.", R.drawable.chineseduck, 399, "nonveg"));
        foodlist.add(new Food("Chicken Chow Mein", "Chicken Chow Mein is a popular Indo-Chinese restaurant dish. Packed with flavor and easy to make, this stir-fry dish is ideal for lunch, dinner or weekend parties. A staple of the Cantonese cuisine, this recipe is made with egg noodles, mixed vegetables and tender chicken breast pieces.", R.drawable.chickenchow, 220,"nonveg"));
        foodlist.add(new Food("Chicken Quesadillas", "Chicken Quesadilla is a very popular Mexican dish served as a snack throughout the day. Packed with cheese and shredded chicken, the quesadillas are a versatile dish.", R.drawable.chickenquesedillas, 229, "nonveg"));
        foodlist.add(new Food("Sweet Corn Chicken Soup", "Sweet Corn Chicken Soup is a delicious Indo-Chinese soup dish. A simple and amazing recipe, this is a heartwarming soup that can be made at home with least effort. Loaded with veggies & chunks of chicken, this is a perfect soup to start your meal or can be served warm as a comfort food.", R.drawable.chickensoup, 119, "nonveg"));
        foodlist.add(new Food("Chicken and Cashew Stir-fry", "Chicken and Cashew Stir-Fry is a quick and easy dish to make for a weekday lunch or dinner. This recipe is very handy on days when you feel like eating something nutritious but you are in a hurry. This Thai style Chicken and Cashew stir fry is best served with rice.", R.drawable.chickencashewfry, 349, "nonveg"));
        foodlist.add(new Food("Scalloped Potatoes", "Looking for a variation on French potato classics like Scalloped Potatoes or even Potatoes au Gratin? Today we look to the home of the potato for some inspiration. Peruvians know a thing or two about potatoes so why not prepare them Peruvian-style? Easy to make and hearty. Who doesn’t like oven-baked potatoes, like these Peruvian scalloped potatoes.",R.drawable.potato, 49, "veg"));
        foodlist.add(new Food("Butternut Squash Quinoa soup", "Butternut squash quinoa soup is great comfort food, and is our version of squash (pumpkin) soup, so well-loved around the world. We roast butternut squash with garlic, cumin and honey and then use this as the base for the soup. Topped with a garnish of spiced quinoa, the soup takes on a bit of crunch and a slight heat.", R.drawable.butternut, 199, "veg"));
        foodlist.add(new Food("Penne Puttanesca", "The puttanesca origin goes back centuries in Italy and, as you would expect, there is quite a story behind this dish, which just adds to its mystique. The story of the recipe for puttanesca sauce is so fascinating we share it before we move on to our penne puttanesca recipe.", R.drawable.puttenesa, 149, "veg"));
        foodlist.add(new Food("Spanakopita", "Genuine, Greek spanakopita is a classic, spinach pie, surrounded with filo pastry. Filled with spinach or silver beet, cheeses and mildly spiced, Greek spanakopita is a great way to increase your or your children’s green veggie intake. I can promise you, they will be coming back for second helpings! Let’s be honest, who doesn’t like pie made from filo pastry?", R.drawable.spanakopita, 220, "veg"));
        foodlist.add(new Food("Manti with Yogurt Sauce", "Turkish manti with garlic yogurt sauce is a very common Turkish meal. Often described as Turkish ravioli, you can either make your own Turkish manti or buy it already prepared. Also known as manti dumplings, it is essentially a very small version of ravioli, usually filled with a slightly spicy, meat mixture. But it can be vegetarian as well. Instead of following a Turkish manti recipe, you can also buy fresh or dried manti, even outside of Turkey.", R.drawable.manti, 349, "veg"));


        return foodlist;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT,"
                + USER_CONTACT + " TEXT,"
                + USER_ADDRESS + " TEXT,"
                + USER_DATE + " TEXT,"
                + USER_DISH + " TEXT" + ");";*/
        String CREATE_USER_TABLE = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact TEXT, address TEXT, orderDate TEXT, dish TEXT);";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_FOOD_TABLE = "CREATE TABLE cuisines(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, details TEXT, imgid INTEGER, price INTEGER, type TEXT);";
        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE);

        ArrayList<Food> foods = retrieve_food();

        for(Food food: foods){
            addFood(food, sqLiteDatabase);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;

        sqLiteDatabase.execSQL(DROP_USER_TABLE);

        onCreate(sqLiteDatabase);
    }

    public void addCustomer(Customer customer){

        SQLiteDatabase database = this.getWritableDatabase();

        Log.e("db", customer.name);
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, customer.name);
        contentValues.put(USER_CONTACT, customer.contact);
        contentValues.put(USER_ADDRESS, customer.address);
        contentValues.put(USER_DATE, customer.date);
        contentValues.put(USER_DISH, customer.dish);

        long rowid = database.insert(USER_TABLE, null, contentValues);
        database.close();
    }

    public ArrayList<Customer> getCustomers(){
        SQLiteDatabase database = getWritableDatabase();

        String contact = "dummycontact";
        String address = "dummyaddress";
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT " + USER_NAME + ", "
                + USER_DATE + ", "
                + USER_DISH + " FROM "
                + USER_TABLE;

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(USER_DATE));
            @SuppressLint("Range") String dish = cursor.getString(cursor.getColumnIndex(USER_DISH));
            Customer customer = new Customer(name, dish, date);
            customers.add(customer);
        }
        return customers;
    }

    public void addFood(Food food, SQLiteDatabase database){

        Log.e("db", food.name);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_NAME, food.name);
        contentValues.put(FOOD_DETAILS, food.details);
        contentValues.put(FOOD_IMG, food.imgid);
        contentValues.put(FOOD_PRICE, food.price);
        contentValues.put(FOOD_TYPE, food.type);

        long rowid = database.insert(FOOD_TABLE, null, contentValues);
    }

    public ArrayList<Food> getVeg(){
        SQLiteDatabase database = getWritableDatabase();

        ArrayList<Food> vegfood = new ArrayList<>();
        String query = "SELECT " + FOOD_NAME + ", "
                + FOOD_DETAILS + ", "
                + FOOD_PRICE + ", "
                + FOOD_IMG + " FROM "
                + FOOD_TABLE;

        Cursor cursor = database.query(FOOD_TABLE, new String[]{FOOD_NAME, FOOD_DETAILS, FOOD_PRICE, FOOD_IMG}, FOOD_TYPE+"=?", new String[]{"veg"},null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(FOOD_NAME));
            @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex(FOOD_DETAILS));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(FOOD_PRICE));
            @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex(FOOD_IMG));
            Food food = new Food(name, details, Integer.parseInt(img), Integer.parseInt(price));
            vegfood.add(food);
        }
        return vegfood;
    }

    public ArrayList<Food> getNonVeg(){
        SQLiteDatabase database = getWritableDatabase();

        ArrayList<Food> nonvegfood = new ArrayList<>();
        String query = "SELECT " + FOOD_NAME + ", "
                + FOOD_DETAILS + ", "
                + FOOD_PRICE + ", "
                + FOOD_IMG + " FROM "
                + FOOD_TABLE;

        Cursor cursor = database.query(FOOD_TABLE, new String[]{FOOD_NAME, FOOD_DETAILS, FOOD_PRICE, FOOD_IMG}, FOOD_TYPE+"=?", new String[]{"nonveg"},null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(FOOD_NAME));
            @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex(FOOD_DETAILS));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(FOOD_PRICE));
            @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex(FOOD_IMG));
            Food food = new Food(name, details, Integer.parseInt(img), Integer.parseInt(price));
            nonvegfood.add(food);
        }
        return nonvegfood;
    }

    @SuppressLint("Range")
    public ArrayList<Customer> searchByUser(String name){
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<Customer> customerlist = new ArrayList<>();
        String query = "SELECT * from "+USER_TABLE;
        Cursor cursor = database.query(USER_TABLE, new String[]{USER_NAME, USER_DISH, USER_DATE}, USER_NAME+ "=?",new String[]{name},null, null, null, null);
        while(cursor.moveToNext()){
            String queryname = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String querydish = cursor.getString(cursor.getColumnIndex(USER_DISH));
            String querydate = cursor.getString(cursor.getColumnIndex(USER_DATE));

            Customer customer = new Customer(queryname, querydish, querydate);

            customerlist.add(customer);
        }
        return customerlist;

    }

    public int deleteAllUsers(){
        SQLiteDatabase database = getWritableDatabase();
        int num = database.delete(USER_TABLE, "1", null);
        return num;
    }
}
