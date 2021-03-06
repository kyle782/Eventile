# Eventile - CS 2212 Team 18

We are currently developing an **event finder web application** that allows users to specify their interests and present nearby upcoming/on-going events that matches their interests.

[Video Demonstration of Eventile](https://www.youtube.com/watch?v=3WWt2063-3U)

## Software

We will be using Grails for our web application framework. Our back-end database will be using SQLite3 (built-in with Grails) and our front-end uses the React Javascript library.

Our software stack includes the following:

* Grails 
* Spring Security Rest plugin
* Webpack
* React
* React Router
* Bootstrap

## Additional Information

### Repository Organization

Each team member has their own branch, where we each implemented our own assigned features. The implemented features are then merged into the dev branch. After unanimous approval, the features are merged into the master branch. 

### Installation/Run Instructions

#### Dependencies

Ideally, the most recent update of the following pieces of software are suggested.
- Git (ver 2.10.1)
- JVM (ver 1.8.0)
- Grails (ver 3.2.6)
- Groovy (ver 2.4.7)
- Gradle (ver 3.4)

Once the JVM is obtained, Grails can be downloaded and installed through terminal using the following commanmds:

> curl -s get.sdkman.io | bash <br/>
> source "$HOME/.sdkman/bin/sdkman-init.sh" <br/>
> sdk install grails <br/>

#### Building and Running the Project

##### 1. Clone the repository

- Create a new directory for the project

- Open a Terminal/Command Prompt window inside the new directory

- Execute the following command: 

> git clone https://github.com/jlee2967/Eventile.git 

##### 2. Build and run the project

- Navigate to /Eventile/EventileReact/ in the Terminal/cmd window:

> cd Eventile/EventileReact

- Build and run the project:

> grails run-app

- Navigate to [http://localhost:8080](http://localhost:8080) in your favourite web browser and enjoy.

### Directories

[Team Webpage](https://jlee2967.github.io/Eventile/)

[Team Roster Page](GitHubPages/TEAMROSTER.md)

[Software Design Page](GitHubPages/SOFTWAREDESIGN.md)

[UI Design Page](GitHubPages/UIDESIGN.md)

[Project Plan Page](GitHubPages/PROJECTPLAN.md)

[Project Retrospective Page](GitHubPages/PROJECTRETROSPECTIVE.md)
