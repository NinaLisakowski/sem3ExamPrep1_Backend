# StartCode_CA3
#### Asignment: [CA3 QuickStart Project](https://github.com/Gold-ish/StartCode_CA3_Backend/blob/master/CA3-QuickStart-project.pdf)

## Frontend
This is the frontend that is supposed to be used with this backend.
https://github.com/Gold-ish/StartCode_CA3_Frontend
  
#### Members:
- Allan Bo Simonses
- Nina Lizakowski
- Caroline Lærke Høeg-Iversen
- Tobias Anker Boldt-Jørgensen
  
## Getting Started with the start code.  
- Make a new repository on GitHub.  
- Clone the repository to your computer.  
- Download this repository as a `.zip` file.  
- Move the project files from the `.zip` to your local repository, that you made in step 2.  
***
- In the pom.xml file edit the artifact id and name to something that makes sense for the project.  
- Create a new schematic on your vagrant db.  
- Find the `config.properties` file in the `Other Sources/src/main/resources/<default package>/config.property` file.  
- Change the `db.database` to the same database name we created on our vagrant db.  
- In the SetupTestUsers.java class edit the passwords to something other than "test", and run the file once.  
- Confirm that the roles and users have been added to the schematic.  
- Add the file to the gitignore, before the initial commit and push to GitHub.(Remove the `#`)  
`**/SetupTestUsers.java`  
- Navigate to the root of the project folder with `git bash`, and run `mvn clean test`.
- If there occur any problems then fix them else go to next step.
- In the local repo add, commit and push to GitHub.  
***
## Setup Travis-CI controll (Maven script deploys when travis has full green test)  
- Go to [Travis-ci.com](https://www.travis-ci.com) and activate the new repo.  
- Select the repository from the dashboard menu.  
- Click `More Options` and select `settings`.  
- Create 2 new `Enviorment Variables`;  
`REMOTE_PW` : (Password for script_user)  
`REMOTE_USER` : `script_user`  
- In the pom.xml file edit the; <remote.server>*(You'r deployment serverURL)*/manager/text</remote.server>  
#### Test The pipeline   
- Make a small change in the program, and run "mvn clean test" to make sure it won't break the pipeline.  
- If it is a succes then try pushing the change to github and see travis turn green.  
- Last check is to make sure that it was deployed properly to the remote server.  
- Try in the browser to reach the url.  
***  
## Deployed server database setup.  
- Create a remote schematic on the deployment server, with the same name as the vagrant db.  
- SSH into the deployment server. `ssh (USER)@(URL)`  
- Add 3 enviorment variables to the setenv.sh folder;  
(sudo nano /opt/tomcat/bin/setenv.sh)  
`export USER_(ProjectName)=(YOUR_DB_USER)`  
`export PW_(ProjectName)=(YOUR_DB_USERS_PASSWORD)`  
`export CONNECTION_STR="jdbc:mysql://localhost:3306/(DATABASE NAME)")`  
- Save the file and run this command to restart tomcat "sudo systemctl restart tomcat"  
***
- If you rename the 3 system.getenv from the "/utils/EMF_Creator.java"(Line 118-122), you also have to remember to rename the 3 variables form the sudo nano /opt/tomcat/bin/setenv.sh  

## Start making your project.
You can use the `RenameMe` as templates for coding the projects.

## Extra:
Force deployment without travis:  
Remember to edit the RemoteServer in the pom.xml file befroe using the mvn command.  
Remember to use the right password.  
mvn clean test -Dremote.user=script_user -Dremote.password=RiGhTpAsSwOrDgOeShErE tomcat7:deploy  
  
## Travis
[![Build Status](https://travis-ci.org/Gold-ish/StartCode_CA3_Backend.svg?branch=master)](https://travis-ci.org/Gold-ish/StartCode_CA3_Backend)


