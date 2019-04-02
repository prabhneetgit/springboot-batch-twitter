# springboot-batch-twitter

<b>Add the mail.properties with configyuration for mail smtp</b>
<br>
/src/main/resources/mail.properties

<b>Add the twitter4j.properties with the secret keys and consumer keys from twitter developer account<br></b>
/src/main/resources/twitter4j.properties 

<b>Reference for twitter configs:<br></b>
http://twitter4j.org/en/configuration.html

<b>Run database using docker on change configuration in application.properties to point to localhost</b>
<br>If using docker. Please follow the below steps:
<br>
1) docker run --name mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:5.7
2) docker exec -it mysql bash
3) mysql -u root -p <Enter the password on prompt>
4) create database pcmDB;
5) create database batchdb;

<b>Create users:-</b><br>
create user 'pcm'@'localhost' IDENTIFIED BY 'pcm_pw';
GRANT ALL PRIVILEGES ON *.* TO 'pcm'@'localhost' WITH GRANT OPTION;
CREATE USER 'batch'@'localhost' IDENTIFIED BY 'batch';
GRANT ALL PRIVILEGES ON *.* TO 'batch'@'localhost' WITH GRANT OPTION;
SELECT user FROM mysql.user GROUP BY user;
FLUSH PRIVILEGES;
<br><br>

Run the application using java -jar batch-0.1.0.jar -jobName -filepath 
