Project Name
Android Intern Task- ShubhamKumarDubey
Table of Contents
List the major sections of your README file with links to each section.
•	Permissions Required
•	Usage
•	Features
•	Contributing
•	License
Permissions required
The only permission required to use this app is Internet permission.
Which is located in Android Mainfest File.

Usage
Opening the App you will land on Login Screen which will ask you for login details, in case you don’t have the login details or for new user can click on CREATE ACCOUNT option to go to the SIGN UP screen where user can signup and then enter in the app.
Features
After entering in the APP you will find a Dashboard.
Dashboard:
- The dashboard will have two sections:
- Section 1: Add a new car
- Section 2: List of added cars

Section 1: Add a new car
- Provided with two dropdown views using 
com.google.android.material.textfield.TextInputLayout
,  AutoCompleteTextView one for selecting the vehicle manufacturer
and another for the vehicle model.
- for first dropdown view we are Calling API #1’ ( https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json ) to get the list of vehicle makes and
populate the our Coustom spinner for car make. Performing  this operation right after the Dashboard
screen gets loaded.
- After the car make is selected, calling  API #2’ (https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMakeId/<make_id>?format=json ) to get
the list of vehicle models for a particular make and populate it for car
models.
- Tapping the ‘Add Car’ button we are saving the car locally and refresh the list of cars to
display the newly added car in the list. 
All this is done instantely.

- Section 2: List of added cars
- Each cell in this list will have the car image and labels for make & model.
For this I have user recycler view to display I have used the following documentation (https://developer.android.com/develop/ui/views/layout/recyclerview#:~:text=RecyclerView%20is%20the%20ViewGroup%20that,by%20a%20view%20holder%20object. )

- Providing  an ‘Add Car Image’ button in each cell for adding an image for the car from the
phone gallery or camera.

- Provided  a ‘Delete Car’ button in each cell to delete the car details.

- Refresh the list view (ListView) after all update / delete operations on the vehicle list.

User Logout  button  Allow the user to log out.
