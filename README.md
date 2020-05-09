
# San Jose State University <br />
## Enterprise Software -CMPE172/Srping 2020 <br />
## Team members: Hansol Kim, Kevin Prakash, Tyler Ashton Yue
-----------------------------------------------------------------------------------------------------------------
### Application Description

#### Covid19 Application is to show the impact of coronavirus in the United States, and it shows the death and hospitalized number caused by Coronavirus by states. AWS, Spring Boot, DynamoDB, Docker, Maven, React.js are the programs used in our project, and the data is from covidtracking.com where they collect it from national news.
-----------------------------------------------------------------------------------------------------------------

![Screen Shot 2020-05-08 at 4 05 50 PM](https://user-images.githubusercontent.com/60766152/81456684-286b1280-9148-11ea-8322-d1e41847e52a.png)

------------------------------------------------------------------------------------------------------------------
### Pre-requisites for setup

- Docker
- AWS
- DynamoDB
- Spring Boot
- Maven 

-------------------------------------------------------------------------------------------------------------------
### How to run the project locally, <br />

1) Go to the project directory in your terminal  <br />
2) Run <br />
      mvn spring-boot:run <br />
   in your terminal <br />
 
3) Open another new terminal, and go do the frontend directory <br />
   (ex, go to the project directory, and to cd frontend ) <br />
 
4) npm i -S react-scripts <br />
 
5) npm start <br />

6) wait little bit for the sprin-boot app to bring out the data <br />
---------------------------------------------------------------------------------------------------------------------
### Diagram for our system
![Screen Shot 2020-05-08 at 4 11 00 PM](https://user-images.githubusercontent.com/60766152/81456895-02923d80-9149-11ea-930d-d33e21bc348a.png)

----------------------------------------------------------------------------------------------------------------------
### For our Schema and Queries,
#### We used DynamoDB to make a table (we used AWS console --> dynamoDB service), and instead of queries, we used methods for requesting items from DynamoDB. 
#### Please find our backend folder under src/main/java/covidapp/backend for reference <br />(The folder have files that have methods to our dynamoDB).
-----------------------------------------------------------------------------------------------------------------------

### UI Data Transport <br />
We used Json, and you can find the json files in our project under frontend folder <br />
(package.json and package-lock.json)




