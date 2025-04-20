Prerequisite
    install Java version 11
    Set JAVA_HOME Environment Variable
    Set the JAVA_HOME to point to your JDK installation directory.

    Example (on Windows):
    Go to System Properties → Environment Variables
    Add a new System Variable:
        JAVA_HOME = C:\Program Files\Java\jdk-11.0.x
      Also, add %JAVA_HOME%\bin to your PATH variable:
        PATH = ...;%JAVA_HOME%\bin

    Verify setup:
        open cmd
        echo %JAVA_HOME%
        java -version

Clone project using the web URL
    https://github.com/cseshubham/hotel-booking-manager.git

Instructions to Compile and Run (on Windows):

1. Go inside root dir of project <hotel-booking-manager>

2. Compile Java code using (Windows classic shell):
   if not exist classes mkdir classes
   javac -cp "lib/*" -d classes src/com/hotel/constant/*.java src/com/hotel/util/*.java src/com/hotel/model/*.java src/com/hotel/service/*.java src/com/hotel/*.java

3. Run the application:
   java -cp "lib/*;classes" com.hotel.HotelBookingApp --hotels "C:\Users\hotels.json" --bookings "C:\Users\bookings.json"


Example commands after running:
   Availability(H1, 20250419, SGL)
   Availability(H1, 20240901-20240903, DBL)
   Search(H1, 30, SGL)

Press ENTER on a blank line to exit.
