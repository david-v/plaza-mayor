#Plaza Mayor

Web service and personal project intended to:

1. Learn Java, and its tech stack which is new to me.
2. Have fun.
3. Perhaps build something I can then use as a web service for an Android app...
4. Get motivation to [next step] learn Android!

---

**Tech Stack**

	Java
	Spring
	Hibernate

**Usage**

	[ ./gradlew bootRun ] NO LONGER VALID - I'm using only Maven now, I'll add Gradle support at some point again
	
	mvn compile
	mvn tomcat7:run

---

**Daily logbook**

*17 Feb*

Time to try out some Java development. I love the strongly typed nature of if. It seems the right way to go, after having to deal with bugs in PHP which were very difficult to find due to their edge case nature, which ended up being critical. In the end my PHP code was checking for the value of every single param passed to functions... Java seems so much cleaner, enforcing you to do it right, and finding some of these errors in compile-time. 

*18 Feb*

Well this is nice, much easier than I thought to get a couple of routes working in the same time that I would get a few endpoints for a web service up and running with any PHP framework. Spring's syntax is so similar to Symfony, with annotations and everything (not a fan of this tho). For what I've read, Hibernate will be easy pissy, knowing Doctrine.

*19 Feb*

For now I've managed to move the DB connection to a Singleton. With 'spring' handling the Front Controller, the start point to my requests is the Controller actions themselves, so I had to use the "magic" of [ @Autowired JdbcTemplate jdbcTemplate; ] which I hate. 

I just don't like annotations at this point, coming from PHP where I know what my app does from the first line of index.php to the very end of a request, there's too much XML and hidden configuration that Spring handles for you. I want to avoid it when possible (for now).

*20 Feb*

Okay I got the basics now, time to start using an IDE! I installed the popular Eclipse, and the project wouldn't even build as Maven, it seems like Gradle is smarter from the command line: my packages were all wrong and still run, that's how smart it is. No longer the case when trying to run Maven from Eclipse. I got the dependencies all sorted and finally it's up and running again. Debugger listening. I do not entirely like Eclipse though, it feels... old. PHPStorm is much nicer

*21 Feb*

I got rid of Eclipse, I couldn't stand its white blackground colour. I've installed Intellij (from JetBrains like PHPStorm and Webstorm, which I've used A LOT) and it feels so much natural, as they share interface and keyboard shortcuts. NOW I feel that I could properly start coding in Java. I've moved the entire project to a more Spring-y way of doing it, following the PetClinic guide from Spring.
