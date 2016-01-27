#Actor Model

##What is?
Actors are small capsules of programming logic that
contain behavior and state, and communicate via message passing

The primary purpose behind an actor is to provide two concepts:
* A safe, efficient way to reason about computations in a concurrent environment, and
* A common means to communicate in a local, parallel, and distributed environment.

Components:
* State
* Actor reference
* Asynchronous message passing
* Mailbox
* Behavior and the Receive loop
* Supervision

##State
Akka isolates each actor on a light-weight thread that protects it from the rest
of the system.
The actors themselves (housed inside a light-weight thread) run on a real set of
threads, where a single thread may house many actors with subsequent invocations for a
given actor occurring on a different thread

##Actor reference
Akka's approach is to designate instaces of actors known as **ActorRef**. Using **ActorRef** results in a single API for both local and remote.
 * immutable
 * handle to it is serializable
 * maybe local or on a remote system
 * Acts as a proxy for the actor it represents

 The proxy behavior makes it possible to send it over the wire on behalf of its actor.
 When an actor recieves an ActorRef the location of the real actor is transparent.

###Async Message Passing
Using asynchronous message passing semantics, actors interact with one another by sending a messages to each other’s ActorRef. 

###Support for two operators:
 * **!** or *tell*: send a message asynchronously. *fire-and-forget*
 * **?** ir *ask*: send a message asynchronously and expect a reply. *Future*

###Akka message passing semantics:
 * *at-most-once* delivery
 * *message ordering per sender-receiver pair*

*At-most-once*: For each message handled, it will arrive at the recipient zero or one times. (Inference: During transfer the message can get lost)

*At-least-once*: For each mesage handled, multiple attempts may occur until delivery is successful. (Inference: recipient may receive duplica messages)

*Exactly-once*: For each message handled, the recipient will recieve 1-and-only-1 copy. (Inference: The message will not be lost or duplicated)

![](/scala-playground/reactive-application-development/message-delivery-method-comp.png)



 pag 46