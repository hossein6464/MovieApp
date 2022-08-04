// Preparation ------- dimens - color - string
// Inside your res/values create a resource layout called dimen
// decide on all the colors font sizes that you think you will need inside these resource files
// The reason is that this will make your project more dynamic

Step 1: Choose Your Api
	// Example : "https://api.themoviedb.org/3/";
	Proceed to the Web Server and register to get a Private Key for accessing API JSON or make a call
	 API_KEY = "Something";
	// Make Sure to read all the documentation for getting familiar with tha API calls

Step 2: Create a Package called Utilities and inside the package create a JAVA file Call Constants
	Make sure to define all the Keys, API urls inside this file

Step 3 : Creating a Async Task to Download API JSONS
	Create another Package called Request and inside it create a Java File
	Try to Extend Async Task and download all the information passed by the API
     // Note Make Sure you have set permission to allow app using Internet in Manifest
     // <uses-permission android:name="android.permission.INTERNET" />

Step 4: Create a Java File Called JsonParser
	This Class will take information downloaded by Download class and turn it all into JSON Object
	later we can use this information to add to our own Object

	// Alternative
	// You can also use Retrofit or Volley to do the same exact thing as step 3 and 4
	// These libraries will take care of Downloading and Mapping Downloaded data to the Model
    // Read More here https://square.github.io/retrofit/

Step 5: Create a Model Class for all API calls
	Now that we have a JSON object we need to customize it to our own object
	JsonParser will take this responsibility 

Step 6: Recycler View Adapter
	Now that we have an arrayList of our Object in this case Movies, we need to create a custom layout
	and attach that custom layout. After that we need to have a custom Adapter that can bind our objects
	to our custom layout.
	Make sure to create a separate adapter for each custom layout.
	// Note For recycler Adapter View if you need ClickListener or LongClickLister in the ViewHolder you need to create a
	// Two interfaces for each Click and pass the position of the view to viewHolder and later create a them inside the Activity
	// Also make sure to pass those click listeners to the Recycler View Adapter as an argument in the constructor

Step 7: Picasso For Images
	For Easy Access to images and download and load it into the image view use Picasso Library.
	To use Picasso u need first enter the latest implementation into the gradle and then sync the file.
	    implementation 'com.squareup.picasso:picasso:2.71828'
 		Picasso.get().load(url).into(movieImageView);
	That is all and with two line you can load any URL image into the imageView that's how simple.

Step 8: Attaching Recycler View to Adapter
	Now we need to move to our Activity and inside activity we need to initialize our recycler view.
	Then we need attach the adapter to our recycler view. Also you can use LayoutManager to customize the recycler view.

Step 9: Adding information to DB
	Now that we have arrayOfObject with the information we need to save our favorite objects into database and retrieve it later.
	We need to create a package to separate DB from other packages.
	Now we need to have a JAVA class for all the SQL commands.
	We also need to have a helper class which will use the SQL commands to create a Database and Table.
	Now Create a DBManager to handle transforming arrayList to ContentValues because we can only add information to DB by ContentValues.
		Also inside DBManager Make sure to have a function that takes a curser which is from DB and turn it into the object which in our
		case is movie.
	Have another Class Database Demo where you define Read Write and Delete functions for DB.
    // Note if your DB extends application it has to be defined inside the manifest in the <application> tag
        <application
            android:name=".db.DBManager"
        />
Step 10: Proceed to your Detail Activity and attach a button that can read and write from DB.

Step 11: Use the same functions for as many Activity needed based on the API

