Real-World Problem
--------
DDoS Attacks can pose a serious problem in our internet-driven society today.  exploiters can use massive internet traffic coming from multiple compromised sources to cripple networks at their will.  Whether the victim is the financial, commercial, or national security, cyber warfare is the war of the future and as a Computer Scientist, it is essential to understand such DDoS attacks so we can even attempt to counter them.

Abstract
--------
In this assignment, we modeled a small-scale attack using Amazon EC2 Instances.  We were able to implement such DDoS on two websites while simultaneously monitoring the site’s performance using JMeter.  In our attempt to flood our first target’s website (85c bakery), we observed the load time was dramatically affected.  It literally took forever to load until the attacks were terminated.  The site is hosted by GoDaddy so we expect some moderate security measures in place, yet, the effect was noticeable.  Although during demo the server was not brought down completely, an earlier trial suggests that it can be easily achieved.

![](/85c-a.png)

![](/85c-b.png)

In our attempt to flood our own Cal Poly Pomona’s servers, it was more successful.  We were able to momentarily stop service completely while the webpage becomes unresponsive.  Perhaps it was an automatic defense mechanism to self-shut down to prevent the servers from being crashed completely, nonetheless, the result was obvious.

![](/cpp.png)

Through these brief, under one minute attacks, I have gained more understanding on what DDoS attacks is about.  There will be no future work to this without proper research justification.



Overview
--------

![](/model.jpg)

Using Dr. Sun’s CS480 Spring WebService as the skeleton, we implemented DDoS code into it to send Http GET Requests with Multi-Threading.  The zombies (attackers) are actual Amazon EC2 Instances initiated by an AMI that is created by a deployed CS480 WebService .jar file.  It is setup to run the .jar at boot so each zombie EC2 Instance is ready-to-attack upon initiation.
 
Since each zombie EC2 Instance’s IP Address changes each time they are started, we implemented a Controller (another EC2 Instance) that acts like an IP Server (that is always running) to keep track to all the zombies location.  Each zombie instances are coded to send a Http POST Request to the Controller instance every minute which the Controller keeps track of a list of current zombie IPs.  This is to model the possible dynamic IPs of infected computers in real world scenarios).

The attack is triggered by our mastermind Dr. Sun by sending a Http POST Request with an “trigger” parameter to the always-listening Controller (whose IP is static).  The Controller then sends out a POST Request with athe target (victim)’s IP address and the number of attacking threads as parameters to every zombie instance.  Finally, upon receiving such “attack” parameter, each zombie starts DDoS with the implemented code.

Environment Setup
-----------------

1. Run Maven Package on each of the Controller/Zombie/Target 's directory to generate the .jar file
```sh
mvn package
```
2. Upload the appreciate .jar files (for Controller/Zombie/Target) on the corresponding EC2 Instances.
3. Log-in EC2 instance through SSH and start the program by the following command:
```sh
java -jar CORRESPONDING_JAR_FILE_NAME.jar
```

Batch EC2 Zombies Initialization
---------------------------------------
1. Start an EC2 instance and upload the zombie type .jar file.
2. Log-in EC2 instance through SSH and add the .jar execution line to the rc.local file to run at startup.
```sh
sudo nano /etc/rc.local
```
![](/ec2config-a.png)

Add the line to the file and save.
```sh
java -jar CORRESPONDING_JAR_FILE_NAME.jar
```
![](/ec2config-b.png)

3. Create an AMI from this EC2 instance.
4. Now you can start new EC2 instances in bulk from this generated AMI.


More information: [http://cs585ddos.com](http://cs585ddos.com).
