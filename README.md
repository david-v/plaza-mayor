#Plaza Mayor

Web service and personal project intended to:

1. Learn Java, and its tech stack which is new to me.
2. Have fun.
3. Perhaps build something I can then use as a web service for an Android app...
4. Get motivation to [next step] learn Android!

---

**Tech Stack**

[On Feb 16th I had never touched any of the following]

	Java 7
	Spring
	Hibernate
	Python
	MySQL

	Tomcat
	Jetty

	Google App Engine
	Google Cloud SQL

**Usage**

[ ./gradlew bootRun ] NO LONGER VALID - I'm using only Maven now, I'll add Gradle support at some point again
	
	mvn clean install

AND THEN

    mvn tomcat7:run

        OR

    mvn jetty:run

---

**Daily logbook**

*17 Feb*

Time to try out some **Java** development. I love the **strongly typed** nature of if. It seems the right way to go, after having to deal with bugs in PHP which were very difficult to find due to their edge case nature, which ended up being critical. In the end my PHP code was checking for the value of every single param passed to functions... Java seems so much cleaner, enforcing you to do it right, and finding some of these errors in compile-time.

*18 Feb*

Well this is nice, much easier than I thought to get a couple of routes working in the same time that I would get a few endpoints for a web service up and running with any PHP framework. Spring's syntax is so similar to Symfony, with annotations and everything (not a fan of this tho). For what I've read, Hibernate will be easy pissy, knowing Doctrine.

*19 Feb*

For now I've managed to move the DB connection to a Singleton. With Spring handling the Front Controller, the start point to my requests is the Controller actions themselves, so I had to use the "magic" of [ @Autowired JdbcTemplate jdbcTemplate; ] which I hate.

I just don't like annotations at this point, coming from PHP where I know what my app does from the first line of index.php to the very end of a request, there's too much XML and hidden configuration that Spring handles for you. I want to avoid it when possible (for now).

*20 Feb*

Okay I got the basics now, time to start using an IDE! I installed the popular Eclipse, and the project wouldn't even build with Maven. It seems like Gradle is smarter from the command line: my packages were all wrong and still run, that's how smart it is. No longer the case when trying to run Maven from Eclipse. I got the dependencies all sorted and finally it's up and running again, and with the **debugger listening**. I do not entirely like Eclipse though, it feels... old. PHPStorm is much nicer

*21 Feb*

I got rid of Eclipse, I couldn't stand its white blackground colour. I've installed **Intellij -from JetBrains**, like PHPStorm and Webstorm, which I've used A LOT. It feels so natural, as they share interface and keyboard shortcuts. NOW I feel that I could properly start coding in Java. I've moved the entire project to a more Spring-y way of doing it, following the PetClinic guide from Spring.

*22 Feb*

Okay now that my IDE is sorted, I feel that I can start properly coding at last!

I've written a **Python script to scrape Wikipedia** for data about all towns and regions in Spain, and dump it to MySQL. I compared the data obtained with the official source from INE (Instituto Nacional de Estadistica) website and it's all good!

*23 Feb*

All 3 adapters ['Jdbc', 'Jpa', 'SpringDataJpa'] up and running. I started with Jdbc, doing it the hard way to learn. Just the way I like. Then moved onto Hibernate, which is actually pretty nice. It feels sooo much like Doctrine. With Intellij I can just Alt+Spacebar and see what Annotations do anyways, so it doesn't seem "black magic" anymore.

Also, just noticed that my town **Segura de los Baños** happened to be id. **6666** of all towns in Spain scraped with my Python script. HOW COOL IS THAT.

*24 Feb*

Google App Engine running! I've deployed the app to: [https://plaza-mayor.appspot.com/towns/Segura](https://plaza-mayor.appspot.com/towns/Segura) You see how Segura de Baños is 6666? :D

It was quite difficult to make this run. A lot harder than Google's 5 min tutorial to deploy a Hello World Servlet with no framework. I had to downgrade to JDK 7. Then use Jetty instead of Tomcat, so I had to learn it too, that and configure Intellij. It also required changes in the codebase: cache, logging system. But probably the worst was a [BUG](https://jira.spring.io/browse/SPR-13829) in my version of Spring: 4.2.4 that makes GAE not run. I had to downgrade to 4.1.3

It's pretty nice that Google's implementation of a SQL database is MySQL (could have been anything else!) so it still works with the JDBC Dialects that I had configured, at least I didn't have to change that... I can now go to bed knowing that I just gave birth to a proper web service to find towns in Spain. I'll think of what to do with it tomorrow.
