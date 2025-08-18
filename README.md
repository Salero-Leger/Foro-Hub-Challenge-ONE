# ForoHub API

Este proyecto es una **API REST** construida con **Spring Boot 3**, **Spring Data JPA** y **MySQL**.  
Su propósito es gestionar un sistema de foros, permitiendo a los usuarios **crear, listar, actualizar y eliminar tópicos**, así como **autenticarse de manera segura**.

---

## 🚀 Tecnologías Utilizadas
- **Lenguaje:** Java 17  
- **Framework:** Spring Boot 3.2.0  
- **Persistencia:** Spring Data JPA  
- **Base de Datos:** MySQL  
- **Seguridad:** Spring Security y JWT (JSON Web Tokens)  
- **Herramientas de construcción:** Maven  
- **Librerías:** Lombok, java.time (fechas) y Auth0-JWT (gestión de tokens)  

---

## 🔐 Funcionalidades de la API

### Autenticación y Seguridad
- **Autenticación de Usuarios:**  
  Utiliza **BCrypt** para encriptar contraseñas y **JWT** para sesiones sin estado.  
- **Endpoint de Login:**  
  `POST /login`  
  - Entrada: correo electrónico y contraseña.  
  - Respuesta: token **JWT** para las siguientes peticiones.  

---

### 📌 Tópicos (CRUD)

- **POST /topicos/registrar**  
  - **Función:** Crea un nuevo tópico.  
  - **Restricción:** Solo usuarios autenticados.  
  - **Campos requeridos:** `titulo`, `mensaje`, `autorId`, `cursoId`.  

- **GET /topicos**  
  - **Función:** Lista todos los tópicos activos.  
  - **Características:** Respuesta paginada (**Pageable**) para mejorar rendimiento.  

- **GET /topicos/{id}**  
  - **Función:** Devuelve un tópico específico por su **ID**.  
  - **Respuesta:** `200 OK` si existe, `404 Not Found` si no se encuentra.  

- **PUT /topicos/{id}**  
  - **Función:** Actualiza un tópico existente.  
  - **Campos actualizables:** `titulo`, `mensaje`, `status`.  

- **DELETE /topicos/{id}**  
  - **Función:** Eliminación lógica del tópico.  
  - **Acción:** Cambia el campo `activo = false` en lugar de borrarlo físicamente.  

---

## 📂 Estructura del Proyecto
Organizado en **paquetes** siguiendo un modelo de capas para arquitectura limpia y mantenible:

- `controller`: Maneja las peticiones HTTP (API REST).  
- `domain`: Entidades (modelos de datos), DTOs y repositorios.  
- `infra`: Configuración de seguridad y servicios de token.  

---
