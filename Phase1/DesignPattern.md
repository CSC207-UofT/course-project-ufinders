1. We have a Factory Method for the Item class which allows us to divide the items into different types: Electronics, Animals, Miscellaneous, Clothings, and Textbook.

2. We implemented a Strategy Design Pattern for Filter. The Filter interface requires all filters to have an apply method, which returns a list of Items that meet the specific filters requirements. Every filter has a different strategy to determine what Items qualify as meeting the requirements. The searcher is the context class--depending on what filters are added to the searcher, a search will return different results. 
