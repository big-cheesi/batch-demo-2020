# batch-demo-2021
dumb demo for the lunch n learn... for some reason the scenario revolves around shrek

run using the VM argumment '-Dspring.batch.job.names=userRegistrationJob'

you will also need to install mysql, set up a local dev environment, tweak the properties in application.yml and run the following mysql script.
the rest of the data will be created automatically using hibernate.

--------------------------------------------------------

**Scenario**
-Quizzed around 1000 people: if we like their answers they are allowed to come and invite their friends. If not they can’t come.

--For example if Shrek is their favourite film they can come and bring friends

--If the Bee movie is their favourite film, I’m sorry they’re banned.

--Also have some other options, for example if chicken run is their fav film they can come and invite some friends, but not as many friends as the shrek crew

--All the dumb business logic is in UserRegistrationProcessor.java

-This batch job reads user responses and their friend invites from one massive json file

-Then processes the data to see if they can come

-Then writes user and friend invitations to mysql