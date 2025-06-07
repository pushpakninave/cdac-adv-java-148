# 06-06-25

## Driver configuration:

1. T2 -> jdbc:oracle:oci:@serviceid
2. T4 -> jdbc:oracle:thin:@IP:port:serviceid

## MISC

- List<User> ls = new ArrayList<>(); -> type inference.

> Why are we returning `Iterator` instead of `ArrayList` in `getAllUsers()`?
security reasons
not really exposing what client dosen't need.

- JEE(Java persistence api)

1. servlet
2. JSP
3. JSF
4. JNDI
5. JPA
6. JMS
7. EJB

## Markup language

- ### Why HTML?

parsed language -> parsed by a parser
to present data
predefined tags
typically static

- ### Why XML?

to create custom markup language
to transfer and present data

- ### Servlet -> `let serve`

Part of JEE arch that enable devs to create server side application excuted by java enabled server to serve the client in the form of xml/html
JSP, JSF, EJB, JNDI, JMS, JPA etc.

- ### Why servlet?

since we client is only specific to information but not the code/business logic. Here servlet serves the html as an output therefore client dosen't need JRE/JVM/JDK or any stuff making no sense to the it. 

- ### On server (context: `servlets`)

1. no main class.
2. default call backs defined on servers.

- ### WAR

1. ROOT
    a. *.HTML
    b. *.JSP

2. WEB-INF
    a. classes -> packages -> *.custom_classes.
    b. lib (dependencies like `*.jar`).
    c. web.xml (deployment descriptor, specifications of invoking procedures).

sax parse exception -> 

### Steps

1. create a java project to its build path add the `servlet-api` located in `tomcat/lib` folder.
2. create a java class that extends `jakarta.servlet.http.HttpServlet`.
3. Override the method `doGet(request, response)` defining the business logic as required.
4. create a folder `WEB-INF`, create a child folder `classes` within `WEB-INF`
5. create an `web.xml` under `WEB-INF` based on web_app_3xsd
6. for every servlet in my web application I need to make an entry for servlet and servlet mapping in the deployment descriptor.
7. Using the file explorer go to your project folder. Copy the contents of the bin folder and paste it under `WEB-INF`'s classes folder.
8. On the cmd - `jar cf filename.war WEB-INF *.html`

### Deploying it in tomcat

1. Launch the tomcat server.
2. In browser type `Localhost` select manager app
3. enter username password
4. scroll to the section that says war file to deploy
5. choose the war file created and deploy.

### Invoking your servlet

1. In the browser type `http://Localhost/filename/url-pattern` as provided in deployment descriptor.