#Ch2:

###Akka Microkernel
A kernel in computer science is the central part of the operating system that manages tasks of the
computer and hardware, and is the most fundamental part of the system. Kernels come in two
forms:
● Microkernel: contains only core functionality
● Monolithic kernel: contains core functionality and many drivers also.

In Akka, the microkernel is a bundling
mechanism that allows you to deploy and run your application without the need of an application
server or launch script.

###The Actor System
The core Akka library akka-actor provides all the semantics for the message passing, serialization, 
dispatch and routing.

Akka adopts a Distributed by Default. Local and remote scenarios use the same API and are
distinguished by configuration, allowing a more succinct way to code Message-driven
applications.

####Akka Cluster
A loosely coupled group of systems that present themselves as a single system.
* decentralized 
* peer-to-peer
* no single point of failure or single point of bottleneck

####Akka's Cluster Aware Routers 
Automatically re-balance load whenever a new member joins the cluster.

####Cluster Singleton
Used for exactly one instance of a process running in a cluster

####Cluster Sharding
Allows you to interact with actors that are distributed across several nodes in a cluster by a logical identifier

####Akka Persistance
Persistent state management is a staple for most applications.
* automatic recovery when a system restarts due to failure or migration.
* foundation for building CQRS and Event Sourced based system

####FUTURES AND AGENTS
Seamless integration with Futures (data structure used to retrieve the result of some
concurrent operation) and Agents (allow safe asynchronous change at a single location).

####Akka Streams
govern the exchange of stream data across asynchronous boundaries by managing backpressure

####Akka Http
HTTP request/response model. Akka Streams under the covers, backpressure is implicitly managed.

####Akka Data Replication
distributed cache

###Concurrency and parallelism
Are sometimes confused to mean the same thing
Example
e.g. Concurrent toll - Two lanes one toll
		 Parallel toll - Two lanes two tolls

###Asynchronous and Synchronous
Synchronous - wait for a return
Asynchronous - does not require its caller to wait, then a form of signal completion is required, such as a
callback, future, or message passing		

###Blocking, Registered Callback, Future, and Message Passing
Blocking - Stops all processing of not just the sender, but the entire
thread which most likely has other processes running. 

Registered callbacks are a construct whereby the caller gives the asynchronous method
with the means by which to signal completion. When the asynchronous method is complete, it will
use the callback to “push” the signal the caller. Akka uses callbacks with its futures construct.

A future is a data structure that is used to capture the result of a concurrent operation and
provides a callback for its access. In asynchronous methods, the future is usually
used in one of two ways. The caller wraps the asynchronous method in the future, or the
asynchronous method returns one. In either case, this allows the caller to continue processing
and “pull” the signal when the asynchronous method is complete. Great for composing a sequence of events that are not synchronous in nature.  Akka has built-in support for futures

###Contention
* resource contention - conflict that arises over access to shared resources such as random access memory, cache memory, disk access and
the like. Contention at this level is primarily the responsibility underlying operating
system, therefore, we usually don't have to deal with it. 
 
* Application level contention - contention almost always results from mismanagement of shared state across multiple
threads (deadlocks, livelock, starvation, race condition)

###Share nothing
Do not have state in your app

###Isolation by a single source of truth
Isolation by single source of truth is a design pattern where we isolate a state model within a
single authority, and that authority is solely responsible for all mutations including creation.
This pattern guarantees that there is always only one record of truth and minimizes contention
through encapsulation.

###Encapsulation by definition
Defensive copy is the notion that any time the authority receives a request; it first makes
an immutable copy of its state model. In the case of mutation, this is done by creating a new
instance by applying the mutation through constructor semantics that replaces the original.

###Immutable state transferance
Immutability transference is really the only safe way share state. If a state structure is
immutable, then it's impossible for anyone to alter it, and we are free to share without worry
of contention. In message passing, this is how we guarantee that the message we send is the
same message our recipient receives. By making the message immutable, we assure its
accuracy and eliminate the possibility of contention

##Actor Model

###What is?
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

###State
Akka isolates each actor on a light-weight thread that protects it from the rest
of the system.
The actors themselves (housed inside a light-weight thread) run on a real set of
threads, where a single thread may house many actors with subsequent invocations for a
given actor occurring on a different thread

pag 46
