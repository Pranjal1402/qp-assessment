# qp-assessment

This is the QuestionPro assesment. The code is written in Java and Spring Boot and uses the MSSQL database. The application runs on port 3000. It also has a dockerfile for running in docker. The swagger link for testing the api is :

http://localhost:3000/swagger-ui/index.html# The docker command for running an MSSQL container is:

docker run -e "ACCEPT_EULA=1" -e "MSSQL_SA_PASSWORD=Pranjal123" -e "MSSQL_PID=Developer" -e "MSSQL_USER=SA" -p 1433:1433 -d --name=pranjalsqldb mcr.microsoft.com/azure-sql-edge


** This code is pushed from my brother's laptop as I dont have a laptop with me right now.
