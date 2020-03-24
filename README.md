# TOP 100 GAMES
- Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Authors](#authors)

## <a name="purpose"></a>Project purpose

This is a test task before interview which get data from [resource](https://rss.itunes.apple.com/en-us) ,
 keep it in Redis data base and give back on requests .


<hr>

Available functions: 
* get top 100 free iOS games
* get top 100 paid iOS games
* get top 100 grossing iOS games
* all endpoints accept "limit" param allowing to reduce the size of the response
  Example - /ios/games/charts/paid?limit=25 - returns top 25 paid games 
* further instructions are available in [swagger](http://localhost:8082//swagger-ui.html#/game45controller)
<hr>

## <a name="structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* spring boot 2.2.5
* log4j 1.2.17
* maven-checkstyle-plugin 3.1.0
* lombok
* swagger 1.5.0

<hr>

## <a name="developer-start"></a>For developer
Open the project in your IDE.

Change a path in C:/Users/user/Desktop/redis/application.log. It has to reach your logFile.

Run the project.
 
<hr>

## <a name="authors"></a>Authors
[Mykhailo Kramar](https://github.com/Mykhaylo12?tab=repositories)

