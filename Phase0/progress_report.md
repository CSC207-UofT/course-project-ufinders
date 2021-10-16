Specification Summary:

There are two main features in the program, a self-help section and a marketplace section. In the self-help section, the user can make a journal entry and store it in their journal, delete an entry and view the entries they made. The user can also access a calendar that includes suggested events and user-created events, and set alarms that will alert them when events occur. In the marketplace section, the user can search through items that are being sold and filter and sort their searches. The user can also make and delete items that they wish to sell. 

------

Walkthrough Summary:

In our walkthrough, the user first goes to the marketplace and posts an item they want to sell, using MenuController, User_Controls, ItemManager, Database, and Item in the process. The user then navigates to the selfcare section using MenuController and User_UI. The user accesses the Journal and adds a journal entry using the JournalUI, JournalController, Journal Manager and Journal. Finally, the user accesses their events and adds an event using EventUI, EventManager, MakeDeleteEvent, and Event. 

------

CRC Summary:

We have 10 Controllers/Presenters:
  - JournalController
  - MainController
  - User_Controls
  - Calendar
  - Event_UI
  - JournalUI
  - JournalEntryUI
  - Result
  - User_Controller
  - User_UI


We have 11 Use Cases:
  - JournalManager
  - ItemManager
  - Searching
  - Sorting
  - Alarm_Maker
  - Create_User
  - Database_Manager
  - Event_Manager
  - Get_Event
  - ItemManager
  - Make_Delete_Event

We have 8 Entities:
  - JournalEntry
  - Journal
  - Database
  - Item
  - Alarm
  - Calendar_Database
  - Event
  - User

------

Skeleton Program Summary:

The skeleton calendar program allows a user to enter the calendar and make an event; the skeleton journal program allows you to add an entry to the journal; the skeleton marketplace program allows a user to enter the marketplace and make and store an item to sell. Our skeleton program can be run by running Main.MainController. 

------

What has worked well so far:

Separating out the controllers and the use cases was more intuitive and helpful than we expected. Working on controllers and UI allowed us to focus on what information was gathered from the user, without worrying about what exactly was being done with that information. Working on use cases, we could easily access information we needed without worrying about how the class acquires it.

Using the scenario walkthrough as a way to verify our program works the way we thought it was supposed to work was also helpful. Figuring out which part of the clean architecture each class was prevented us from having high level code depending on low level code. The CRC cards helped us identify what methods we should implement for each class based on the responsibilities of each class.

By clarifying what classes go in which layer, we were able to figure out the collaborations between classes and their responsibilities more easily and efficiently which then made coding quicker.

------

Questions:

  - How can we implement a database so that information isn’t lost each time our program is re-run? (For marketplace and journal) 
  - How should we decide what to implement interfaces for? 
  - How should we write unit tests for controllers when they mostly just take input? 

------

What we have each worked on and plan to work on:

Everyone:

Thinking up domain, writing and revising CRC cards. 
Helping each other with skeleton code
Writing the specification, walkthrough, and progress report

Claire:

Have worked on: Skeleton code for User_Controls and Main.MainController.
Plan to work on: Marketplace section, particularly the classes related to searching and sorting search results. 

Annie:

Have worked on: Skeleton code for Database and ItemManager and unit tests for Item.
Plan to work on: Database_Manager, fix up ItemManager, figure out how to store more than one item in Database and the rest of Marketplace implementations (ie. the presenter and more test cases).

Thaksha:

Have worked on: Skeleton code for the Journal section of the self help section and writing the unit test for adding an entry.
Plan to work on: Implementing exceptions to deal with invalid input from the user, and implementing a presenter to format how the journal entry would be viewed by the user instead of getting the use case to do that. Also implementing an interface so the journal(entity) can pass the entry that has been added to it to the presenter.

Nealon:

Have worked on: Skeleton code for the events section, integration with the alarm and user sections. 
Plan to work on: Front End development and further integrating UI elements into our program.

Marton:

Have worked on: Skeleton code of the alarm clock and the user sections of the program along with front end development.
Plan to work on: Front End development and further integrating UI elements into our program.

Oliver: 

Have worked on: Skeleton code for Item, revised changes to Item_Manager and User_Controls, made sure code was working in different stages of development, modified unit tests for Item to make it more concise
