# MovieApp

MovieApp is an online movie application that allows users to explore movies, view ratings and reviews, <br/>
and manage their movie preferences. This backend service is built using the Spring Framework, <br/>
Spring Security for authentication and authorization,<br/>
and MySQL for data persistence. <br/>

## Features

* User Authentication & Authorization: Users can register, login, and manage their accounts securely with role-based<br/>
access control using Spring Security.<br/>
* Movie Management: Users can view movies, their genres, languages, and their ratings.<br/>
* Ratings & Reviews: Users can rate and review movies.<br/>
* Role-Based Access Control: Only admins can manage movies, genres, languages, and user roles.<br/>
* Genres & Languages: Movies can be categorized into different genres and languages for better browsing.<br/>

## Key Entities

* **Movie**: Represents a movie with details like title, description, release date, etc.<br/>
* **Ratings**: Represents user ratings for movies.<br/>
* **Review**: Represents user reviews for movies, including text feedback.<br/>
* **Role**: Defines user roles (e.g., ADMIN, USER).<br/>
* **User**: Represents the user profile with details like username, email, password, and roles.<br/>
* **ERole**: Represents predefined roles for users, like USER and ADMIN.<br/>
* **Genres**: Represents different movie genres (e.g., Action, Drama, Comedy).<br/>
* **Languages**: Represents the languages in which movies are available (e.g., English, Spanish, French).<br/>

## Technologies Used

* **Spring Boot**: For building the backend RESTful APIs.<br/>
* **Spring Security**: For handling authentication and authorization using JWT.<br/>
* **MySQL**: Relational database for storing movies, users, ratings, reviews, and more.<br/>
* **JPA (Java Persistence API)**: For object-relational mapping between Java objects and database tables.<br/>
* **ModelMapper**: To map between entity objects and DTOs (Data Transfer Objects).<br/>
* **Lombok**: To reduce boilerplate code (getters, setters, constructors).<br/>

## Prerequisites

- Java 17<br/>
- MySQL Database<br/>
- GRADLE (for dependency management)<br/>
- Postman or any API testing tool (for testing API endpoints)<br/>