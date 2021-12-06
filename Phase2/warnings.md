Event

You’ll see some warnings regarding typos, those are unimportant. There are also warnings regarding Calendar months, but that is just a stylistic warning, and makes no difference in the execution of the code. In fact it is easier to have it as an integer rather than as a specific month. There is also the ignoring of the deletion and renaming of files in EventManager’s removeSingleEvent method, but after extensive testing it was determined that the files will always close and be renamed, so there is no need to keep track of the boolean returned.

Journal

JournalFileGateway
Result of File.createdNewFile is ignored - createNewFile returns whether the file was created as a file and false if a file with that name already exists so the file was not created. But that result is not necessary because before even calling createNewFile JournalManager when calling createFile check that prior to calling it.
Result of File.delete() is ignored  - delete() returns true if file was deleted and false otherwise. But this method should always work because the user can only choose to delete files that are in the journal folder.

Marketplace

There are a few classes where the error is to generify a line of code. However, it wouldn’t let us do so.
