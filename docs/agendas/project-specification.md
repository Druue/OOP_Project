# Introduction
The client has asked us to make a desktop application for managing the buildings around the TU Delft campus. This application will focus specifically on room (and bike) reservations that are within the campus, and make sure that different people can use the application to reserve certain rooms at certain times.


# Requirements

The client has provided a list of requirements that are necessary for the application.

## Tools

The client has stated that the application must be written in Java, and that there is no requirement for how the interface should look.
Furthermore, the usage of Websockets is **not** allowed.

## Functionality

The main features that this campus software should provide are:

- The ability for someone to create an account.
    This should be done via an authentication system, which is described in the 'Authentication and Signup' section.
- A list of buildings that have rooms, opening and closing times, and bikes to rent.
    The attributes of these entities are described later in the document.


### Users
Once authenticated, a user should be able to:
- View all rooms and timeslots per building, and see the availibility for each room.
- Reserve a timeslot for a room, in a specific building.
- Cancel an ongoing reservation. This requires password verification.
- See a list of all timeslots currently reserved by the user.
- See a list of all past timeslots reserved by the user.
- Reserve a bike from a specific building.


### Administration
The application should include an administrative side, in which an administrator is able to perform the following tasks:

- Add, edit or remove buildings.
- Add, edit or remove rooms from buildings.
- Add or remove bikes from buildings.
- Change the opening and closing times for a building.
- Cancel a reservation for a certain timeslot.
- See an overview of all current reservations. 
- Change the availability of a building for a specific date.

## Buildings
The application should contain a database of all availible buildings. The main attributes of a building that are stored in the application are:

- The name.
- (Optional) A short description.
- It's location.
- The opening and closing times and dates. (This also includes holidays)
- A list of rooms that are located in the building.
- The amount of bikes that are assigned to the building.
- A check stating whether or not the building contains a cafeteria.


### Rooms
Each room in the application has the following attributes:

- A name.
- (Optional) A short description.
- A maximum capacity (or: the amount of seats availible).
- A permission level: The room should list who can reserve it. This can be employees only, students only, or both.
- A check stating whether or not the room contains a projector.

### Timeslots
As described above, each building has an opening and closing time. When a building is open, fixed timeslots are availible for reservation. Each timeslot is 30 minutes long, and cannot be moved. Example: A timeslot will always start at 9:00, and cannot be moved to start at 9:02.

Furthermore, a timeslot can only be reserved by one user at a time, and only 2 weeks in advance. Lastly, a user cannot reserve two different rooms during the same timeslot.

A timeslot can have three different statuses: Available, unavailable, and reserved.
- Available: Any user is able to reserve this timeslot.
- Unavailable: No user is able to reserve this timeslot.
- Reserved: A user has reserved this timeslot.

The key difference between an unavailable and reserved timeslot is that a reserved timeslot can be made availible once the user who reserved the timeslot cancels his/her reservation. An unavailible timeslot means that that room most likely will never be availible at that time, or that a holiday is occuring.

### Bikes
Each bike has a unique ID. The ending digits of each bike are that of their assigned building.
A user is able to reserve a bike from a building, if that building is open and has bikes availible.
Since a building will (most likely) contain multiple bikes, it is possible that two (or more) users reserve a bike from a building during the same timeslot. 

Furthermore, it is assumed that a user returns his/her reserved bike to the building he/she got it from. 

# Additional Features

These are features that do not necessarily have to be implemented, but would be considered a good addition to the application.

## Food reservation

Some buildings on campus have a food court in them, in which a user could order some food.

The food can be
- Ordered to be picked up at the it's food court.
- Ordered for a certain reservation.




## Filter search
Users could have the ability to search for a particular room with the use of filters. An Example: A user could specify the minumum amount of seats they would like to have in a room.

## Calendar view
Users could have the capability to look at their ongoing reservations in a calendar view. This would give a clear overview of when a user has reserved timeslots.

## Email verification
During the registration process it could be possible to verify a user's email address by sending them an email.

# Users
Most interaction with the application will be done via it's users. These require an account, and depending on their permission level are able to interact with different parts of the application.

## Authentication & Signup
A person is able to create an account. This can only be done using a TU Delft mail address, as the application is intended for internal use only. When signing up, a person must provide the following information:

- First and Last name
- Their TU Delft email address
- Their NetID
- A password   

## Roles
There are three types a user can have, each with its own level of permissions.
Users that are either Students or Employees have no interaction with each other, as only an administrator can see who has reserved what.

### Student
A student is only able to reserve timeslots in rooms that are open for student booking and bikes. 

### Employee
An employee is able to reserve timeslots in rooms that are open for employees only, and rooms that are open to both students and employees. An employee is also able to reserve a bike.

### Admin
An admin has all permissions, and can do anything within the application. These accounts should only be given to the administrators of the application, and not to anyone else.
Furthermore, *Admins will be hardcoded.* A user cannot register as an admin.