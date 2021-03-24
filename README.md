# School project
# 1. Description
This project implements a simple CRUD backend logic for a school management system. 
It is implemented in a REST architecture, and contains 4 enities (Class, Grade, Student and Teacher). 
Dependencies between each entities are shown below on a UML class diagram section. Application use H2 database.
# 2. Startup
Project can be launched locally by executing SchoolApplication class. By default it runs on localhost:8080.
# 3. Endpoints table
| endpoint |  GET  | POST | PUT | DELETE | PATCH |
|---|---|---|---|---|---|
| /students  | list of students | add new student | X | X | X |
| /students/{studentId} |  student info |  X |  update student |  delete student |  partial update student |
| /students/{studentId}/grades |  student grade list |  add grade |  X |  X |  X |
| /students/{studentId}/grades/{gradeId} | grade info  |  X | grade update  |  delete grade |  partial update grade |
| /teachers | teachers list  |  add new teacher | X  |  X |  X |
| /teachers/{teacherId} | teacher info  |  X | update teacher  |  delete teacher |  partial update teacher |
| /classes | class list  |  add new class | X  |  X |  X |
| /classes/{className} | class info  |  X | update class  |  delete class |  partial update class |
| /classes/{className}/students | list of class students  |  X | X  |  X |  X |
| /classes/{className}/students/{studentId} | class student info  |  X | X  |  X |  X |
# 4.UML
![School uml](https://github.com/SzymonBabinski/School/blob/master/school_uml.jpg)
# 5. Technologies
Technologies used in this project: Spring boot, Java, SQL, Hibernate, Maven, Git, database H2
# 6.TODO
- Add frontend using Angular
- Add lesson entity and corresponding endpoints
- Add tests
