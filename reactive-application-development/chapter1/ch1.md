#Ch1:

###CRUD vs. Event Sourcing

Domain model by way of CRUD, only stores current state, the deleted data is lost.

The CRUD model of persisting current state captures the behavior of the system in the form of creating, reading, updating and deleting.

This doesn’t tell us much about our users and compromises the value of our data.

Event sourcing, or persisting a sequence of events (behaviors), (not to be confused with message driven, which instead means reacting to an message), provides a means by which we can capture the real intent of our users.

Events are something that has happened, which is an important distinction when
compared to commands

###Difference between Message, Command, and Event?

abstract message as a blank sheet of paper, a structure to capture a
conversation between two parties. The paper in and of itself is not a conversation until I write
something on it. I write down on the paper a request to borrow a
book from you. The Abstract Message has now become (been implemented as) a Command, a
request "to do" something. In response, you write back to me that you have mailed the book.
At this point, the Abstract Message has become (been implemented as) an Event, the
notification that something "has occurred". In this example, both Command and Event are
forms of Message, in computing lingo they implement the Message Interface

A concrete message is like an envelope. It is a container that has a payload. That payload can
be anything. In the example above, the payload would be either a command or an event. The
distinction here is message is concrete, just like command and event.

