Thaksha

https://github.com/CSC207-UofT/course-project-ufinders/commit/3667b12ed4edc489db0fadfde7bfd3a06bb91782

The pull request Made a presenter class for pop and implementing delete pop up is a significant pull request I made throughout the term. My other team members used my code within the presenter class for pop-up as a foundation to implement the pop-up for the feature they were implementing, making it easier for them to implement pop-ups. Also, after implementing a pop-up for journals, it inspired us to do a pop-up for other features of Ufinders.


Nealon

https://github.com/CSC207-UofT/course-project-ufinders/pull/36#issue-1064818899 This link is to the pull request that pulled all of my EventGUI code from the EventGUI branch to the main repository. This is important because it is the entirety of how the user interacts with the events section of the program.

Annie

https://github.com/CSC207-UofT/course-project-ufinders/pull/29/commits/055ebba99ee5373e87bcb784629470cb80e57cdb
I was able to figure out the basics of storing our item’s information through this pull request. This is important for Marketplace as without the ability to store, we won’t be able to find items to sell, buy or remove.

Oliver

https://github.com/CSC207-UofT/course-project-ufinders/pull/23
Pull request to implement the factory design pattern for items, where I added item types and a creator which makes them using the chosen design pattern. Also added some additional methods such as edit() for each subclass of item, made Item implement Serializable and added comments for code.

Claire

https://github.com/CSC207-UofT/course-project-ufinders/pull/41 (feature/split_user_controls). With this pull request I split User_Controls into two classes, MarketplaceController and MarketplaceUI. User_Controls both printed information and acted on that information, while MarketplaceController only acts on information passed to it, and MarketplaceUI collects that information from the user. This split allowed us to easily implement a GUI for the marketplace, since all we had to do was change how MarketplaceUI sends off prompts to the user. It also helped us better obey the principles of clean architecture by making a greater distinction between the controller part of our code, the presenter part, and the GUI part.

Marton

https://github.com/CSC207-UofT/course-project-ufinders/pull/59
(feature/user UI GUI interface). With this pull request I transformed the user authentication process from a clunky and annoying command line interface to a fully interactive GUI. This allowed us to keep the program running in case the features were not used as intended and also allowed the user to fully interact with every feature of our program at the same time. For example a user can now take notes in a journal while also editing their events in their calendar. This also helped us obey clean architecture by having separate classes for different sections rather than having a 200 line lone command line main method.
