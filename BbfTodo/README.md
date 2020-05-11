Todo Back-end.

Inledning
Simpelt API för TodoMVC-operationer. Se nedan för API

TodoController innehåller flertalet metoder för att jobba mot MySql servern. Använder sig av Crud hibernate interface för dess vanliga grundläggande funktioner mot databaser. Dess interface "extends" i TodoRepositoryImpl för att få utökad funktionalitet för vissa queries.


		Use Postman to access the api, or a browser.

		Show all elements   http://localhost:8080/api/all
		Delete element      http://localhost:8080/api/deletepost - (@RequestParam long id)
		Add element         http://localhost:8080/api/add - (@RequestParam String text)
		Show total number   http://localhost:8080/api/totalnumber
		Show active         http://localhost:8080/api/active
		Show done           http://localhost:8080/api/done
		Toggle todo         http://localhost:8080/api/toggleone - (@RequestParam long id)
		Toggle all todo     http://localhost:8080/api/toggleall - flyttat viss logik utanför
    Toggle all todo     http://localhost:8080/api/toggleallv2 - En modifierad variant men utför samma sak.
		Update an element   http://localhost:8080/api/update2 (@RequestParam long id, @RequestParam String text,@RequestParam boolean completed)
		
