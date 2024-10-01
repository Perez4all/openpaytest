# openpaytest
Application to make calls against Marvel API and record them in the binnacle

Application runs on http://localhost:8080

Sample calls (CURL)

Get Queries Binnacle

curl --location 'http://localhost:8080/marvelqueriesinfo' \
--header 'Authorization: Basic YW5kcmVzOm9wZW5wYXk=' \
--header 'Cookie: JSESSIONID=A3FF6049F04F78F62F2D67385BD693CC'

Get Character By Id

curl --location 'http://localhost:8080/character/1011334' \
--header 'Authorization: Basic YW5kcmVzOm9wZW5wYXk=' \
--header 'Cookie: JSESSIONID=A3FF6049F04F78F62F2D67385BD693CC'

Get Characters

curl --location 'http://localhost:8080/character' \
--header 'Authorization: Basic YW5kcmVzOm9wZW5wYXk=' \
--header 'Cookie: JSESSIONID=92D7EF367B744FC10D2ABC279203954C'

Just for this test default basic auth user/password is andres/openpay CURLs already contain basic auth
