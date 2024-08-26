# ms_usuario
El objetivo de este servicio es el de disponibilizar las gestiones solicitadas de los usuarios. Se va a poder solicitar un usuario e indicar si un usuario comienza a seguir a otro.

### Dominio
![Domain.png](doc%2FImagenes%20Readme%2FDomain.png)

### Diagrama de servicio
![Diagrama de clases.png](doc%2FImagenes%20Readme%2FDiagrama%20de%20clases.png)

## API Reference

Se exponen los siguientes servicios:

#### Seguir usuario
```http
POST /api/v1/usuario/{seguidorId}/seguido/{seguidoId} HTTP/1.1
```
| Parameter | Type     | Description                                    |
| :-------- | :------- |:-----------------------------------------------|
| `seguidorId` | `Long` | **Requerido**. ID del usuario que sigue a otro |
| `seguidoId` | `Long` | **Requerido**. ID del usuario que van a seguir |
#### Respuesta
```json
{
    "id": 2,
    "nombre": "mamateo",
    "seguidos": [
        1,
        4
    ]
}
```

#### Buscar por ID
```http
GET /api/v1/usuario/{id} HTTP/1.1
```
| Parameter | Type     | Description                            |
| :-------- | :------- |:---------------------------------------|
| `id`      | `Long` | **Requerido**. id del usuario a buscar |
#### Respuesta
```json
{
    "id": 1,
    "nombre": "ezortega",
    "seguidos": [
        2,
        3,
        4,
        5
    ]
}
```
## Correr aplicación
```bash
mvn spring-boot:run
```
## Correr los test
```bash
mvn clean test
```
## Tech stack
Java 17, Spring 3.3.3, MySQL
