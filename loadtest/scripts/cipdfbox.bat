
cd "C:\sandbox\dinf\dinf\app\rest-springboot" 


ECHO "============================================================" >>filename.txt 2>&1
ECHO "============================================================" >>filename.txt 2>&1
ECHO "======== START PDF BOX =====================================" >>filename.txt 2>&1
ECHO "============================================================" >>filename.txt 2>&1
ECHO "============================================================" >>filename.txt 2>&1

call mvn clean install -Ppdfbox >>filename.txt 2>&1

heroku deploy:jar target/rest-service-pdfbox.jar --app radiant-lake-31312 >>filename.txt 2>&1
REM heroku logs --tail --app radiant-lake-31312

