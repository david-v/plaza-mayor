#Daily logbook


####I'm gonna learn Java

######17 Feb 2016

Time to try out some **Java** development. I love its **strongly typed** nature. It seems the right way to go, after having to deal with bugs in PHP which were very difficult to track due to their edge case nature, which ended up being critical. In the end my PHP code was checking for the value of every single param passed to functions... Java seems so much cleaner, enforcing you to do it right, and finding some of these errors in compile-time.

I've started a small project, 'Plaza Mayor', which will offer a web service to find towns in Spain and scrape some information of them from Wikipedia. Maybe I can use it to learn some Angular or Android development later on.

> Java, Plaza Mayor

---

####So far so good with Spring

######18 Feb 2016

Java is nice, and the framework I'm learning, Spring, is much easier than I thought to get a couple of routes working. It took me about the same time than with PHP, and considering I've been developing in PHP for 4 years that's quite something! I find the syntax so similar to Symfony, with the annotations (not a fan of this though). For what I've read, Hibernate will be easy pissy, knowing Doctrine.

> Spring, Java, Plaza Mayor
 
---

####Starting to hate annotations

######19 Feb 2016

For now I've managed to move the DB connection to a Singleton. With Spring handling the Front Controller, the start point to my requests is the Controller actions themselves, so I had to use the "magic" of [ @Autowired JdbcTemplate jdbcTemplate; ] which I hate.

I just don't like annotations at this point, coming from PHP where I know what my app does from the first line of index.php to the very end of a request, there's too much XML and hidden configuration that Spring handles for you. I want to avoid it when possible (for now).

> Plaza Mayor, Spring, Hibernate, Singleton, DB, Jdbc

---

####Eclipse feels old :(

######20 Feb 2016

I believe I got the basics now of Java and Spring development. For now I've tried to do things my way, mapping the coding standards I know to the Java stack rather than relying on the way Spring community seems to overcome issues.

Now it's time to start using an IDE! I installed the popular Eclipse, and the project wouldn't even build with Maven. It seems like Gradle is smarter from the command line: my packages were all wrong and still run, that's how smart it is. No longer the case when trying to run Maven from Eclipse. 

I got the dependencies all sorted and finally it's up and running again, and with the **debugger listening**. I do not entirely like Eclipse though, it feels... old. PHPStorm is much nicer

> Plaza Mayor, Eclipse, IDE, Java, Debugger

---

####Intellij to the rescue

######21 Feb 2016

I got rid of Eclipse, I couldn't stand its white blackground colour. After 4h my eyes burn. I've installed **Intellij** -from **JetBrains**, like PHPStorm and Webstorm, which I've used A LOT. It feels so natural, as they share interface and keyboard shortcuts. The debugger is just the same too. 

NOW I feel that I could properly start coding in Java! I've moved the entire project to a more Spring-y way of doing it, following the PetClinic guide from Spring. I very much like the fact that Spring enforces keeping DAO's and persistance adapters separate. I still don't like Annotations and XML configuration, but quite frankly the code I'm ending up with is really clean!

> Intellij, Plaza Mayor, JetBrains, PHPStorm, WebStorm

---

####Wikiscraper in Python

######22 Feb 2016

With my IDE sorted and the skeleton of my project finally ready, I feel that I can start properly coding at last! I need some real data populating my DB. Remember that this project, Plaza Mayor, would be about searching for Spanish towns in an API endpoint? Wikipedia has all the information I need.

I just have a script to parse it. I could have written it in PHP, like I did when I parsed all Argos' products and categories for a project in the past. But then again: I'm trying new things here, so let's see what a different scripting language can do for me: Python! 

Python's syntax is really clean, it took me only a few hours to get used to the beauty of enforced indentation, and how easy it is to install packages via 'pip' My script is ready, and all towns and regions in Spain are now safe in MySQL. I compared the data obtained with the official source from INE (Instituto Nacional de Estadistica) website and it's all good!

> Scraper, Python, Plaza Mayor

---

####Adapters to DAOs and Hibernate

######23 Feb 2016

All 3 adapters ['Jdbc', 'Jpa', 'SpringDataJpa'] up and running. I started with Jdbc, doing it the hard way just to learn. Just the way I like. I had to write the actual SQL string queries, to then find that when moving onto Hibernate the ORM handles pretty much everything for you. Just like Doctrine does. It feels sooo much like it. Even annotations and XML seem less hostile now, thanks to Intellij I can just Alt+Spacebar and see what they do internally anyways, so it doesn't seem "black magic" anymore.

Also, just noticed that my town **Segura de los Baños** happened to be id. **6666** of all towns in Spain scraped with my Python script. HOW COOL IS THAT.

> Hibernate, Java, Doctrine, Plaza Mayor

---

####Google App Engine

######24 Feb 2016

With my web service fully implemented, it was time to deploy it. I wanted to try GAE for a while, and it is just spectacular. Such a fantastic platform. 

In less than an hour I had an autoscaling, load-balanced environment that doesn't cost anything if nobody uses it, with a centralised logging system. New versions are deployed smoothly, and you can make your traffic transition gently to the new app version rather than a kill switch. You have any idea how long it takes to setup infrastructure to accomplish the same using other providers??

My service is now live in: [https://plaza-mayor.appspot.com/towns/Segura](https://plaza-mayor.appspot.com/towns/Segura) You see how Segura de Baños ID is 6666? :D

It was a bit difficult to make my app run, I found all sorts of issues even though the result is well worth the effort. A lot harder than Google's 5 min tutorial to deploy a Hello World Servlet with no framework. I had to downgrade to JDK 7. Then use Jetty instead of Tomcat, so I had to learn it too, that and configure Intellij. It also required changes in the codebase: cache, logging system. But probably the worst was a [BUG](https://jira.spring.io/browse/SPR-13829) in my version of Spring: 4.2.4 that makes GAE not run. I had to downgrade to 4.1.3

At least it was nice that Google's implementation of a SQL database is MySQL (could have been anything else!) so it still works with the JDBC Dialects that I had configured with Hibernate.

 I can now go to bed knowing that I just gave birth to a proper web service to find towns in Spain. I'll think of what to do with it tomorrow.

> Google, App Engine, GAE, Plaza Mayor, Java