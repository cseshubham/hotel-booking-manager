Prerequisite
Java version 11

Instructions to Compile and Run (on Windows):

1. Go inside root dir of project <hotel-booking-manager>

2. Compile Java code:
   if not exist classes mkdir classes
   javac -cp "lib/*" -d classes src/com/hotel/constant/*.java src/com/hotel/util/*.java src/com/hotel/model/*.java src/com/hotel/service/*.java src/com/hotel/*.java

3. Run the application:
   java -cp "lib/*;classes" com.hotel.HotelBookingApp --hotels "C:\Users\MZ754BD\IdeaProjects\hotel-booking-manager\hotels.json" --bookings "C:\Users\MZ754BD\IdeaProjects\hotel-booking-manager\bookings.json"


Example commands after running:
   Availability(H1, 20250419, SGL)
   Availability(H1, 20240901-20240903, DBL)
   Search(H1, 30, SGL)

Press ENTER on a blank line to exit.
