
cd "C:\sandbox\dinf\dinf\app\rest-springboot"

call mvn clean install -Pitext

heroku deploy:jar target/rest-service-itext.jar --app radiant-lake-31312
REM heroku logs --tail --app radiant-lake-31312

