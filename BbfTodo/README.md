Projektet

Inledning

Använd Postman eller liknade för att jobba mot api. 
TodoController innehåller flertalet metoder för att jobba mot MySql servern.
@Config används inte för det enda som @autowire är ett interface som extendar CrudRepository
@Transaction används ej




Kursens projekt bygger på projektet från front-end kursen. Ni får gärna jobba i grupp,
men tänk då på att det ska gå att tydligt se i GitHub vem som gjort vad, genom att man
kan se från vems konto kommiten är gjord.
Det som ska göras är att ta fram en back-end till er mvcTODO. Till den ska ni använda
Spring som ramverk och en MySQL databas för att spara data. Databasen ska bygga på
den modellen ni tog fram i inlämningsuppgiften, eventuella avsteg från den ska motiveras.
För att kommunicera med front-end delen ska ni använda ett RESTful API. Om ni väljer
att göra front-end delen med vue.js, ren JS och HTML spelar ingen roll, då detta inte ingår
i kursen. Koden ska vara väl dokumenterad och lätt att läsa.
Redovisning sker via GitHub, motiveringar till de olika val ni gjort skriver ni i readme.md
i projektets rotkatalog.

Bedömning
Godkänt (G):
En fungerande back-end som går att anropa via ett RESTful API i t.ex. Postman. Samt
någon form av kommunikation med den underliggande databasen. Att det går att följa
arbetet med projektet via flera kommits som gjorts under arbetets gång.
