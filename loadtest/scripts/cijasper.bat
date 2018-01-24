
cd "C:\sandbox\dinf\dinf\app\rest-springboot"

call mvn clean install -Pjasper

heroku deploy:jar target/rest-service-jasper.jar --app radiant-lake-31312
REM heroku logs --tail --app radiant-lake-31312

