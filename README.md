# CardGame_Backend - Reactive API 

In this project the concepts of Mono and Flux, the Stream and the investigative capacity oriented to the use of Angular an Java SE, springboot also the architecture design should be layer oriented with clean architecture, Unit tests should be developed for the most critical functionalities in the backend.


---

<!-- ABOUT THE PROJECT -->
## About The Project

This game consists of having a deck of Marvel superhero cards, you will find 108 characters with different styles and shapes, some of these cards are individual characters and others are cards with a group of characters. The cards do not have characteristics and/or powers, for this you must create a system that allows you to store these cards and assign them a description, powers (XP) and characteristics.

- Key frontend and backend concepts
- Angular project in frontend
- Consumption of data API
- Images and container in docker
- Instant mesagging with RabbitMQ
- Deployment with Netlify

## [🖥  Presentation Video](https://youtu.be/aloeQ0n2hBg)

---

# PROJECT REVIEW:


## LOGIN:

### Login with google

Login with firebase and google (or with any authentication system).

![image](https://res.cloudinary.com/adev48/image/upload/v1663394306/Deployments/Application%20Enterprise%20%28Final%20Project%29/login_liikkk.png)

---

## VIEW INFORMATION:

### Event new window

Event in each button to react to the **click** and redirect to another page with information.

![image](https://res.cloudinary.com/adev48/image/upload/v1663394306/Deployments/Application%20Enterprise%20%28Final%20Project%29/informationGame_kiwtmp.png)

---

## LIST GAMES:

### Details of each created game

Detail of each game, making use of **MongoDB** to save the information associated with the game.

![image](https://res.cloudinary.com/adev48/image/upload/v1663394306/Deployments/Application%20Enterprise%20%28Final%20Project%29/gameList_h3aadh.png)

---

## BOARD GAME:

### Round system: board game, card bet and logical decision making

Board to put bet cards (lid closed), validation of bets of the board cards (open cover), card bet and logical decision making.

![image](https://res.cloudinary.com/adev48/image/upload/v1663394306/Deployments/Application%20Enterprise%20%28Final%20Project%29/boardGame_i5f6hl.png)

---

## SCORE DATA:

### Score and accumulated: history of points

Detail of accumulated score , making use of **Firebase DataBase** to save the information associated with the scores.

![image](https://res.cloudinary.com/adev48/image/upload/v1663394306/Deployments/Application%20Enterprise%20%28Final%20Project%29/scoreView_ztg8cb.png)

---

## Management Docker:

### Container creation for MongoDb and RabbitMQ

![image](https://res.cloudinary.com/adev48/image/upload/v1663394995/Deployments/Application%20Enterprise%20%28Final%20Project%29/Docker_cevvqq.png)

---

## Domain Model:

### Layer oriented with clean architecture

![image](https://res.cloudinary.com/adev48/image/upload/v1663394988/Deployments/Application%20Enterprise%20%28Final%20Project%29/views_g3h4ls.png)


![image](https://res.cloudinary.com/adev48/image/upload/v1663394990/Deployments/Application%20Enterprise%20%28Final%20Project%29/domainModel_a8sq1p.png)

---

## Built With

This section contains the platforms that were used for the project.


### Frontend
* [JavaScript](https://developer.mozilla.org/es/docs/Web/JavaScript)
* [Local Storage](https://developer.mozilla.org/es/docs/Web/API/Window/localStorage)
* [Tailwind CSS](https://tailwindui.com/)
* [Angular Framework](https://angular.io/)


### Backend
* [IntelliJ Idea](https://www.jetbrains.com/es-es/idea/)
* [Git](https://git-scm.com/)
* [Java JDK 17 Version ](https://www.oracle.com/java/technologies/downloads/)
* [Mongo DB -Image Docker](https://hub.docker.com/_/mongo)
* [Rabbit MQ -Image Docker](https://hub.docker.com/_/rabbitmq)

---

### Installation for **FrontEnd Project**

Install each one the pieces of software previously mentioned (Git).


1. Clone the repo

- HTTPS 

   ```
   $ git clone https://github.com/nqs48/CardGame_Frontend-FinalProject.git
   ```
   
 - SSH
 
   ```
   $ git clone https://github.com/nqs48/CardGame_Frontend-FinalProject.git
   ```
 
 
2. Open the project with VisualStudio Code (In the root proyect directory)

   ```
   $ code .
   ```
   
3. Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`

   ```
   ng serve -o
   ```
   
---

### Installation for **Backend Project**

Install each one the pieces of software previously mentioned (Git).


1. Clone the repo

- HTTPS 

   ```
   $ git clone https://github.com/nqs48/CardGame_Backend-FinalProject.git
   ```
   
 - SSH
 
   ```
   $ git clone git@github.com:nqs48/CardGame_Backend-FinalProject.git
   ```
 
 
2. Open the project with IntelliJ Idea (In the root proyect directory)

   ```
   $ idea .
   ```
   
3. Run Application Service

   ```
   $ run AppService
   
   ```
   
4. Run Application Socket

   ```
   $ run AppSocket
   
   ```

---

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

---

<!-- CONTACT -->
## Collaborators
```
Nestor Quiroga Suarez
Jr. Software Developer

Raul Alzate
Technical Coach
```
- LinkedIn => [Nestor Quiroga Suárez](https://www.linkedin.com/in/nqs48/)


Project Link: []()

<p align="right">(<a href="#top">back to top</a>)</p>
