Lista de Usuarios App
Una aplicación simple para gestionar una lista de usuarios, desarrollada con Kotlin utilizando Jetpack Compose. Este proyecto demuestra el uso de patrones modernos de desarrollo como MVVM y herramientas avanzadas de Android.

📋 Características
📂 Listado de usuarios: Visualiza una lista de usuarios con datos relevantes.
🔍 Búsqueda: Encuentra usuarios rápidamente mediante un campo de búsqueda.
➕ Agregar usuarios: Añade nuevos usuarios al listado.
✏️ Editar usuarios: Modifica la información de los usuarios existentes.
❌ Eliminar usuarios: Elimina usuarios de la lista.
🌐 Diseño moderno: Interfaz desarrollada con Jetpack Compose para una experiencia fluida y moderna.
🚀 Tecnologías utilizadas


Kotlin: Lenguaje principal del proyecto.
MVVM: Arquitectura para una separación clara entre UI y lógica.
Room Database: Persistencia de datos local.
Hilt: Inyección de dependencias.
LiveData/StateFlow: Manejo de datos reactivos.

🛠️ Instalación y uso
Prerrequisitos
Android Studio Arctic Fox o superior.
SDK de Android 21 o superior.
Pasos para clonar y ejecutar el proyecto

Clona este repositorio:
git clone https://github.com/saulhervas/listaUsuariosApp.git
Abre el proyecto en Android Studio.
Sincroniza las dependencias (Gradle se encargará automáticamente).
Ejecuta la app en un dispositivo o emulador configurado.

📦 Estructura del proyecto
El proyecto sigue el patrón MVVM para facilitar la escalabilidad y mantenimiento.


Copiar código
listaUsuariosApp/
├── data/                # Gestión de datos (Room, repositorios, modelos)
├── ui/                  # Componentes visuales y lógica de UI
├── viewmodel/           # Manejo de estado de la aplicación
├── di/                  # Configuración de Hilt
└── utils/               # Utilidades generales

🤝 Contribuciones
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto:

Realiza un fork del repositorio.

Crea una nueva rama:
git checkout -b feature/nueva-funcionalidad
Realiza los cambios y confirma los commits.
Envía un pull request.

🧑‍💻 Autor
Desarrollado por Saúl Hervás.


|                               Pantalla Principal                              |                                   Pantalla añadir usuario                                |
|:------------------------------------------------------------------------------:|:------------------------------------------------------------------------------:|
|   ![pantalla principal](https://github.com/saulhervas/listaUsuariosApp/assets/136034899/df36225e-05fe-4c5a-8279-6d28d4dace47)   |    ![pantalla añadir usuario](https://github.com/saulhervas/listaUsuariosApp/assets/136034899/5a442560-5ee6-41d0-854a-381129528105)    |
|                               Pantalla editar usuario                              |    
|  ![pantalla editar usuario](https://github.com/saulhervas/listaUsuariosApp/assets/136034899/b5bdc71f-4999-44fc-ab29-e9d6987c5930)     |   
