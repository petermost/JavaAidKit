JavaAidkit
==========
A Java library which contains various aid classes. The documentation is still a work in progress, but some notable highlights are:

- [CyclicList](https://github.com/petermost/JavaAidKit/blob/master/src/com/pera_software/aidkit/collection/CyclicList.java): A List<> which wraps index access such that -1 accesses the last element and -2 the element before that etc.
- [FilePath](https://github.com/petermost/JavaAidKit/blob/master/src/com/pera_software/aidkit/io/FilePath.java): Allows to manipulate file paths.
- [Ref](https://github.com/petermost/JavaAidKit/blob/master/src/com/pera_software/aidkit/lang/Ref.java): A class which can be used to return a value from a method via a kind of reference parameter.
- [Signal/Slots](https://github.com/petermost/JavaAidKit/tree/master/src/com/pera_software/aidkit/signal): A signal/slot implementation similar to the Qt signal and slots mechanism.
- [DataMutex](https://github.com/petermost/JavaAidKit/blob/master/src/com/pera_software/aidkit/concurrent/DataMutex.java): A generic mutex class which guarantees compile time safe thread synchronization. 
- [LockLocker](https://github.com/petermost/JavaAidKit/blob/master/src/com/pera_software/aidkit/concurrent/LockLocker.java): A helper class to lock Locks within a `try-with-resources` statement. 
