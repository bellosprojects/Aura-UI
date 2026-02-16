# 🌌 Aura UI Engine
**A modern, declarative, and animated UI Framework for Java Swing.**

Aura es un motor de interfaz de usuario ligero construido sobre Java 2D (AWT/Swing) que elimina la rigidez de los layouts tradicionales de Java, permitiendo crear interfaces fluidas, con sombras dinámicas, transformaciones espaciales y un sistema de animaciones basado en easing.

## ✨ Características Principales
- Declarative Layout System: Olvida el GridBagLayout. Aura usa SRow, SColumn y SGrid con soporte nativo para Weights (proporciones) y Spans (celdas múltiples).

- Advanced Rendering:  Sombras reales con desenfoque simulado.

- Bordes redondeados independientes por esquina.

- Control de trazos (Strokes) por lado.

- Physics-Ready Transformations: Rotación y escalado con Anchor Points configurables (pivotes dinámicos).

- Aura Animation Engine: Sistema de transiciones suaves con soporte para Linear, EaseIn, EaseOut y encadenamiento de animaciones (Wobble effects).

## 🛠 Arquitectura Técnica
1. El "Clip-Free" Rendering
A diferencia de los componentes estándar de Swing que recortan la pintura en sus fronteras, Aura utiliza una técnica de liberación de Clip para permitir que las sombras y rotaciones invadan el espacio del contenedor padre, logrando una estética orgánica sin recortes visuales.

2. Layouts Inteligentes
SGrid: Implementa una matriz de ocupación booleana para calcular automáticamente la posición de elementos con colSpan y rowSpan.

3. Flex-Weights: SRow y SColumn calculan el espacio disponible en tiempo de ejecución para repartir dimensiones basadas en pesos, similar a Flexbox o Jetpack Compose.

## 🚀 Ejemplo de Uso
Crear una barra de búsqueda moderna con un campo flexible es tan sencillo como esto:

```Java
SRow searchBar = new SRow().gap(15).padding(20).content(row -> {
    // Icono fijo
    row.add(new SContainer().size(40, 40).background(Color.GRAY).radius(10));
    
    // Campo expandible (Weight 1)
    row.add(new SContainer().weight(1).background(Color.WHITE).radius(10));
    
    // Botón con sombra
    row.add(new SContainer().size(100, 40).background(Color.BLUE).shadow(10, Color.BLACK));
});
```

## 🏗 Instalación y Requisitos
Java 17 o superior (LTS recomendado).

Sin dependencias externas.

Bash
git clone https://github.com/bellosprojects/Aura.git