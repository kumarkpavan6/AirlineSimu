Run Project
---------MainActivity----------
Immediately push all files(flight.csv, client.csvo, passwords.txt) to data/data/g0392.phaseiii.csc207project/files
passwords.txt must be in format of a csv file (username,password) followed by "\n".
Admins must have a username/email that end with "@admin.com"
Client could have any username/email but must be in email format.

--------Adding/editing Information (booking Flights, uploading information, signing up) in any Activity---------
Uploading Information through file, you need to push that file that you are about to add. Then you need to pull back the edited file that you added all the information.
Booking Flights after done booking, pull back that file
Signing up, after signed up you need to pull back the passwords.txt file
Edit Client info, once edited pull back the client.csv
Edit Flight info, once edited pull back the flight.csv

So basically everytime you add information/signup to any file you need to push the file that you want to add through and pull the file that information has been added to(by hand or by file).

When booking a flight, you need to click on the flight you want to book and then click the book button. The flight you selected will not be highlighted, but when the book button is clicked the application will book the flight that was last clicked from the list.