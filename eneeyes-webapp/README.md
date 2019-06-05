Estrutura do Sistema:
	a) Definição do Projeto: 
		i- Server: (Java Rest Services com anotações JPA, Spring JPA e Hibernate);
		ii - Client: Java Server Pages, HTML5, Angular e Jquery / Template AdminLTE e Boostrap;  
	b) Criação do projeto Server: J2EE Web Module / IDE Eclipse utilizando Maven;		 
	c) Criação do projeto Cliente: J2EE Web Module / IDE Eclipse;

 
* COMANDO PARA EXECUCAO DA APLICACAO
mvn jetty:run -Dspring.profiles.active="dev" -P local

--Backup windows command line
C:\Program Files\MySQL\MySQL Server 5.7\bin>mysqldump eneloga > c:\TEMP\eneloga.sql -h localhost -u root -p

--restore windows command line
C:\Program Files\MySQL\MySQL Server 5.7\bin>mysql -h localhost -u root -p enenew < c:\TEMP\eneloga.sql