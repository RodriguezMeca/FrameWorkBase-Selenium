# FRAMEWORK BASE DE AUTOMATIZACIÓN CON SELENIUM

Este repositorio contiene un framework base diseñado para simplificar el desarrollo de pruebas de automatización con Selenium.

## Características

- **Estructura Sólida:** Proporciona una estructura organizada para mejorar la legibilidad y el mantenimiento del código.

- **Bibliotecas Útiles:** Incluye bibliotecas y utilidades predefinidas para acelerar el desarrollo de pruebas.

- **Configuraciones Predefinidas:** Ofrece configuraciones iniciales optimizadas para la automatización con Selenium.

- **Interacción con Elementos Web:** Funciones incorporadas para interactuar eficazmente con elementos web.

- **Gestión Centralizada de Navegadores:** Facilita la inicialización y el cierre de navegadores, simplificando la gestión de múltiples navegadores.

## Objetivo

Optimizar el proceso de automatización para permitir a los equipos centrarse en crear pruebas de alta calidad y mantener un código limpio y mantenible.


## Uso
Los pasos que se muestran a continuación se realizan bajo el patrón de diseño de POM y con la herramienta de Cucumber.

- En la carpeta **main** existe un archivo **.java** llamado **BasePages** que contiene los métodos de **Selenium** y demás para ser usados.
- Crea los **Feature** en la carpeta que se llama del mismo nombre ubicada en la carpeta **test**.
- Defina los pasos en la carpeta **steps** ubicada en la carpeta **java** de la carpeta **test**.
- Modele las pantallas que implementa la automatización en la carpeta **pages** ubicada en la carpeta **java** de la carpeta **main**.
- Al momento de crear una clase en la carpeta **pages** esta debe ser una extensión de la clase **BasePages**.
- Al ser una extensión de la carpeta **BasePages** debe incluir en el constructor el comando de **super(driver)**.
- Y por último, al momento de utilizar los métodos de las clases no se olviden de instanciar estos mismos al utilizarlos en los **steps**.


## Contribución

¡Contribuciones y mejoras son bienvenidas!.

## Contacto
- Omar Rodriguez Ropero
- omarrodriguezropero@gmail.com

---
