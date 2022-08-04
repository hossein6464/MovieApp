Step 1: Choose Your Api
	// Example : "https://api.themoviedb.org/3/";
	Proceed to the Web Server and register to get a Private Key for accessing API JSON or make a call
	 API_KEY = "Something";
	// Make Sure to read all the documentation for getting familiar with tha API calls
Step 2: Create a Package called Utilities and inside the package create a JAVA file Call Constants
	Make sure to define all the Keys, API urls iside this file

Step3 : Creating a Async Task to Download API JSONS
	Create another Package called Dowload and iside it create a Java File
	Try to Extend Async Task and download all the information passed by the API

Step 4: Create a Java File Called JsonParser
	This Class will take information downloaded by Download class and turn it all into JSON Object
	later we can use this information to add to our own Object

Step 5: Create a Model Class for all API calls
	Now that we have a JSON object we need to customize it to our own object
	JsonParser will take this responsibility 

Step 6: Recycler View Adapter
	Now that we have an arrayList of our Object in this case Movies, we need to create a custom layout
	and attach that custom layout. After that we need to have a custom Adapter that can bind our objects
	to our custom layout.
	Make sure to create a sepertate adapter for each custom layout. 

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
	Now that we have arrayOfObject with the information we need to save our favorite objects into database and retreive it later.
	We need to create a package to seperate DB from other packages.
	Now we need to have a JAVA class for all the SQL commands.
	We also need to have a helper class which will use the SQL commands to create a Database and Table.
	Now Create a DBManager to handel transforming arrayList to ContentValues because we can only add information to DB by ContentValues.
		Also inside DBManager Make sure to have a function that takes a curser which is from DB and turn it into the object which in our
		case is movie.
	Have another Class Database Demo where you define Read Write and Delete functions for DB.

Step 10: Proceed to your Detail Activity and attach a button that can read and write from DB.

Step 11: Use the same functions for as many Activity needed based on the API

