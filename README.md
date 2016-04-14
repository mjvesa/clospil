Project halted once again.

While Clojure has more features than Scheme, it does not align with the authors preferences regarding Lisp implementations.

Reasons for not continuing with Clospil:

* Compared to Scheme, Clojure is a huge language for "real life" coding with all the bells and whistles and blinking lights that it implies
* No small, easy to understand core
* ClojureScript seems rather limited compared to BiwaScheme (macros in different namespace, the situation regarding eval and where's clojurescript.min.js?)

In general Scheme feels more pure, possibly since it is based on manipulation of lists at its core. Clojure has the Seq abstraction, much better support for data structures and a great amount of libraries. Even with those benefits, it just does not feel like it's as fun to code in as Scheme.

Maybe I'll try again some day.

    



Clospil (working name)
======================

A small web app for writing Vaadin UIs in Clojure. Currently just a fork of Spil.

Technologies used:

    * JVM
    * Vaadin
    * Clojure
	* ClojureScript/Wisp (pending decision)

Goals
=====
* Become the best framework for writing Vaadin apps using Clojure


