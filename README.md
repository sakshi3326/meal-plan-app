# meal-plan-app
A Kotlin Android application for meal planning

## What I Learned

  ### Structuring the App

  Data
  - Interact with local data using Dao interfaces
  - Fetch cloud (API) data using Service interfaces
  - Utilize Dao interfaces with Repository classes
  - Model local and cloud data with data classes

  Connecting Data and UI
  - Use Adapters to populate and change Recycler Views
  - Connect Repositories to Data with View Model classes
  - Use Fragment classes to Pull together UI and Data resources and attach functionality to events

  User Interface
  - Build a main layout and fill it in with varying fragments
  - Utilize icons and other images
  - Create a menu
  - Define navigation (including passing data) between fragments
  - Preset string and color values

  ### Android Dev in Kotlin
  - Room database
  - Moshi JSON parsing
  - RecyclerViews
  - MutableLiveData, LiveData, Lists, MutableLists
  - Observing asynchronous changes in data with Observers
  - Jetpack Navigation: Fragmentation
  
## How to Use

Begin by cloning the repository and installing Android Studio. Instructions for Android Studio set up can be found online: https://developer.android.com/studio/intro/

Once that's done, set up an Emulator to run the application within Android Studio. Again, documentation can be found online. The Emulator will hike up the fans, so make sure you've some computing power before you try to start it up!

The app should be running on the emulator and at a green-themed calendar screen.

Navigate about the app by picking a date on which you would like to plan a meal for. Create a new meal, or fill in one of the regular daily meals (Breakfast, Lunch, or Dinner). Name the meal, select ingredients for the meal, even add a URL for the recipe you're using or add some instructional notes, or even notes on how delicious (hopefully) the food was. After ingredients are added, ooh and ahh at the nutritional information provided about your meal.

Navigate to the home screen and click in the top right corner to view all of your saved recipes. Recipes can be used later as references for meal creation! Edit and add to recipes in the recipe view.

## Other Notes
  - For reverse populating a meal with a recipe, the navigational components are built, but assigning the ingredients to the meal within the database is incomplete. The name of the recipe, however, will be assigned to your meal. 
  - The same can be said for saving a recipe from an existing meal.
  - ingredients have buttons set up for them but the feature was never implemented. The idea was that ideally, the app would allow user input to modify how much of said ingredient was being used in the meal/recipe (for example, an 8 lb pack of chicken seems unreasonable.
