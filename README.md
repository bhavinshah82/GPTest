Following are the curl commands.
User email id is bs@gmail.com & password is "password"

Get single note for user(GET)
curl -X GET -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: 2dd7f1a7-347b-1d71-89b5-0c5df08dcbd3" "http://localhost:8080/unotes/rest/user/1/notes/1"

Get all user notes(GET ALL)
curl -X GET -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: 8412795f-9385-52fa-1658-276f687ed3e8" "http://localhost:8080/unotes/rest/user/1/notes"

Create a note for user(POST)
curl -X POST -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: 1e9e7410-88fa-0f9a-c212-75d06d061023" -d '
  {
    "title": "note2",
    "note": "hello world"
  }
' "http://localhost:8080/unotes/rest/user/1/notes/"


Update note for user(PUT)
curl -X PUT -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: 44872e18-40d1-efa4-90e3-4f4a3195a63b" -d '
  {
    "title": "Note1",
    "note": "note 1 text"
  }
' "http://localhost:8080/unotes/rest/user/1/notes/1"


Delete a single note of user(DELETE)
curl -X DELETE -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: c7217365-0b13-83f9-77e7-8eb8351664ec" -d '
  {
    "title": "note1",
    "note": "hellow world"
  }
' "http://localhost:8080/unotes/rest/user/1/notes/1"


Delete all notes of the user(DELETE ALL)
curl -X DELETE -H "Content-Type: application/json" -H "Authorization: Basic YnNAZ21haWwuY29tOnBhc3N3b3Jk" -H "Cache-Control: no-cache" -H "Postman-Token: b178a06b-615f-b7e3-ed72-c84c86f7da99" -d '
  {
    "title": "note1",
    "note": "hellow world"
  }
' "http://localhost:8080/unotes/rest/user/1/notes/"


Use following SQL queries to create the tables & insert a default user

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `emailId` varchar(255) NOT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UK_5h8l578jfu4rpony2b2b1xwre` (`emailId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `notes` (
  `noteId` int(11) NOT NULL AUTO_INCREMENT,
  `creationTime` datetime DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `note` longtext NOT NULL,
  `title` varchar(50) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`noteId`),
  KEY `FK_rm2xcvw06bdic5q56ny1ctaig` (`userId`),
  CONSTRAINT `FK_rm2xcvw06bdic5q56ny1ctaig` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


INSERT INTO `gptestdb`.`user`
(`userId`,
`creationTime`,
`emailId`,
`lastUpdateTime`,
`password`)
VALUES
(1,
"2016-05-27 21:28:07",
"bs@gmail.com",
"2016-05-27 21:28:07",
"password");
