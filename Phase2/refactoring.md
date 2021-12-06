Thaksha:

viewEntry was too long. So I refactored some of the code within its body to create the helper method addEntryWithUniqueTitle.

The method entryExist in JournalManager had an ambiguous name so I changed it to doesEntryExist to indicate that the method is checking whether an entry exist or not

The instance variable in JournalUI has an ambiguous name of is an awkward name so I changed it to numOfTitlesEntries as it the number of titleless entries rather than the titless entries themselves

Marton:

Refactored naming conventions in User_Controller to follow camelCase

Divided the GUI pages into separate classes to ease debugging and architecture

Refactored the User_UI file to ‘main_code’ to reduce file naming ambiguity

Nealon:

Realized the methods in EventUI didnt have the camelCase naming convention, so fixed that.

The main function in EventUI was too long, so I split it up among the EventGUI presenter class

Annie:

Changed the instance variable in Database to be more specific

Changed the method name  remove_post to something else because I realized there are other methods with the same name

Oliver:

Several variables under item did not follow CamelCase convention, so I refactored those

Realized Results.present method was too long - split it with helper methods

Claire:

Split User_Controls into two classes required a significant amount of refactoring.

Those of us working on the Marketplace section decided to delete Results, and refactored the MarketplaceController startSearch method to return items to the UI instead of passing them onto Results.
