Single Responsibility Principle:

Each class of the marketplace section has a single responsibility. For example, MarketplaceUI only gets input from the user based on predetermined prompts, MarketplaceWindow only displays those prompts, and MarketplaceController acts on that input. The various use cases also have a single responsibility: ItemManager adds or deletes items in the database, Searcher makes searches, the filters check if an item meets a certain specification, etc. Item and Database are both entities, which store specific information.

Each class of the journal section has a single responsibility. For example, JournalUI’s responsibility is to take input from the user, like what they want to do in the journal, and carry out that action. The JournalWindow’s responsibility is presenting what the user wants to see in a pop-up window. The JournalController’s responsibility is to delegate to JournalManager, which delegates to FileGateway and Journal. FileGateway’s responsibility is creating files, deleting files, and editing files. Lastly, the journal’s responsibility is to store a mapping of the entry title to file with the entry and get information about what it stores.

Also, EventUI focuses on user inputs and nothing else, EventManager decides how those inputs get distributed, MakeDeleteEvent manages the adding and deletion of events, GetEvent reads events from the UofT events webpage, and Event is just an entity containing event info.

Open-Closed Principle:

Filters can easily be extended without needing to modify existing filters or the existing searcher. The same is true for comparators and sorters in the Marketplace.

Item as a superclass will not undergo any more changes, but it can be extended with the addition of more subclasses to represent possibly more categories of items.

An additional section of the app could easily be expanded by adding it to the menu displayed to the user after the sign in.

Liskov Substitution Principle:

Item is a superclass in Marketplace, and all of its subclasses (Animal, Textbook, etc) can replace Item at any point without issue.

Interface Segregation Principle:

The Filter interface has a single method which is necessary for all filters to implement, so there is no need for this interface to be further segregated.

The interface segregation principle is also used within the FileGateway Interface. This interface only has three methods that class implementing has to implement. FileGateway is an interface that is used to interact with files such as create files, delete files and get information from files.

Dependency Inversion Principle:

The dependency inversion principle is used in the Marketplace section. None of the high-level classes like Item depend on lower-level classes like MarketplaceController or ItemManager. For example, MarketplaceManager knows nothing about how searches are made, only the information it needs to pass down to Searcher. Similarly, Searcher does not depend on filters or sorters beyond their output when executed.

The dependency inversion principle is also used in the journal section. For example, JournalFileGateway is a gateway class, and a use case, JournalManager wants to delegate to JournalFileGateway, which will result in a violation of clean architecture. So I used an interface, FileGateway, to invert the dependency and allow JournalManager to delegate to FileGateway indirectly.


The higher level class EventUI knows nothing about how Events are implemented, only that a few basic bits of information are required to be passed down. The same goes for the Controller layer EventManager. The Use Case MakeDeleteEvent knows how events work, because it creates them. None of these classes know how GetEvent works as well.
