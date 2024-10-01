# openpaytest
Application to make calls against Marvel API and record them in the binnacle

Application runs on http://localhost:8080

Sample calls (CURL)

Get Queries Binnacle

curl --location 'http://localhost:8080/marvelqueriesinfo' \
--header 'Cookie: JSESSIONID=A3FF6049F04F78F62F2D67385BD693CC'

Get Character By Id

curl --location 'http://localhost:8080/character/1011334' \
--header 'Cookie: JSESSIONID=A3FF6049F04F78F62F2D67385BD693CC'

Get Characters

curl --location 'http://localhost:8080/character' \
--header 'Cookie: JSESSIONID=A3FF6049F04F78F62F2D67385BD693CC'
